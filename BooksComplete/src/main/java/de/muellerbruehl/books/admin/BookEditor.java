package de.muellerbruehl.books.admin;

import de.muellerbruehl.books.entities.Book;
import de.muellerbruehl.books.entities.Category;
import de.muellerbruehl.books.entities.Review;
import de.muellerbruehl.books.entities.ReviewLink;
import de.muellerbruehl.books.enums.Page;
import de.muellerbruehl.books.helper.SessionTools;
import de.muellerbruehl.books.helper.Topic;
import de.muellerbruehl.books.helper.Topics;
import de.muellerbruehl.books.helper.Utilities;
import de.muellerbruehl.books.services.BookService;
import de.muellerbruehl.books.services.CategoryService;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@ConversationScoped
public class BookEditor implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger _logger = Logger.getLogger("BookEditor");
  @Inject private CategoryService _categoryService;
  @Inject private BookService _bookService;
  @Inject Conversation _conversation;
  @Inject SessionTools _sessionTools;

  private List<Category> _categories;
  private List<String> _selCategories;

  private Book _book;
  private Topics _topics;
  private Topics _languages;
  private String _info;

  @PostConstruct
  private void init() {
    _logger.log(Level.INFO, "init in BookEditor");
    _categories = _categoryService.findAll();
    if (_conversation.isTransient()) {
      _conversation.begin();
    }
    loadOrCreateBook();
    initLanguages();
    initTopics();
  }

  private void initLanguages() {
    _languages = new Topics();
    addlanguage(Utilities.getDefaultLanguage());
    for (String lang : Utilities.getSupportedLanguages(Utilities.HandleDefault.Exclude)) {
      addlanguage(lang);
    }
    _languages.setActive(Utilities.getDefaultLanguage());
  }

  private void addlanguage(String lang) {
    Locale locale = new Locale(lang);
    Topic topic = Topic.TopicBuilder
            .createBuilder(lang)
            .setTitle(locale.getDisplayLanguage())
            .build();
    _languages.addTopic(topic);
  }

  public Topics getLanguages() {
    return _languages;
  }

  private void initTopics() {
    _topics = new Topics();
    Topic topic = Topic.TopicBuilder
            .createBuilder("tabBook")
            .setTitle(Utilities.getMessage("tabBook"))
            .setOutcome(Page.AdminBookEditor.getUrl())
            .setEnabled(true)
            .build();
    _topics.addTopic(topic);
    for (String lang : Utilities.getSupportedLanguages(Utilities.HandleDefault.Include)) {
      String key = "tabReview" + lang.toUpperCase();
      topic = Topic.TopicBuilder
              .createBuilder(lang)
              .setTitle(lang)
              .setOutcome(Page.AdminReviewEditor.getUrl())
              .setImageEnabled(lang + "_small.png")
              .setImageDisabled(lang + "_small_missing.png")
              //              .setEnabled(getBook().hasReview(lang))
              .build();
      _topics.addTopic(topic);
    }
    _topics.setActive("tabBook");
  }

  public String changeTab(String newTopicKey) {
    if (_topics.getActiveTopic().get().getKey().equals(newTopicKey)) {
      return "";
    }
    _topics.setActive(newTopicKey);
    return _topics.getActiveTopic().get().getOutcome();
  }

  public String changeShortText(String newTopicKey) {
    if (_languages.getActiveTopic().get().getKey().equals(newTopicKey)) {
      return "";
    }
    _languages.setActive(newTopicKey);
    return "";
  }

  private void loadOrCreateBook() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
    try {
      int bookId = Integer.parseInt(paramMap.get("bookId"));
      _book = _bookService.find(bookId);
      if (_book == null) {
        _book = new Book();
      }
    } catch (NumberFormatException e) {
      _book = new Book();
    }
    addEmptyReviewLink();
  }

  public Book getBook() {
    return _book;
  }

  public String getReview() {
    String lang = getTopics().getActiveTopic().get().getKey();
    Optional<Review> optReview = _book.getReviews().stream().filter(r -> r.getLanguage().equals(lang)).findAny();
    if (optReview.isPresent()) {
      return optReview.get().getText();
    }
    return "";
  }

  public void setReview(String text) {
    String lang = getTopics().getActiveTopic().get().getKey();
    Optional<Review> optReview = _book.getReviews().stream().filter(r -> r.getLanguage().equals(lang)).findAny();
    if (optReview.isPresent()) {
      optReview.get().setText(text);
    } else {
      Review review = new Review();
      review.setLanguage(lang);
      review.setText(text);
      review.setBookId(_book.getId());
      _book.getReviews().add(review);
    }
  }

  public String getLocalShortText() {
    String defLang = Utilities.getDefaultLanguage();
    String activeLang = _languages.getActiveTopic().get().getKey();
    if (defLang.equals(activeLang)) {
      return _book.getShortText();
    }
    return _book.getShortText(activeLang);
  }

  public void setLocalShortText(String text) {
    String defLang = Utilities.getDefaultLanguage();
    String activeLang = _languages.getActiveTopic().get().getKey();
    if (defLang.equals(activeLang)) {
      _book.setShortText(text);
    } else {
      _book.setShortText(activeLang, text);
    }
  }

  public String getInfo() {
    return _info;
  }

  public void setInfo(String info) {
    _info = info;
  }

  public List<Category> getCategories() {
    return _categories;
  }

  public Topics getTopics() {
    return _topics;
  }

  public String save() {
    if (!_sessionTools.isInternalClient()) {
      return Page.AdminNoSave.getRedirectUrl();
    }
    removeEmptyReviewLink();
    _book = _bookService.save(_book);
    _bookService.clearCache();
    return "";
  }

  public List<String> getSelectedCategories() {
    return _selCategories;
  }

  public void setSelectedCategories(List<String> selCategories) {
    _selCategories = selCategories;
  }

  public void changeReviewLink(ValueChangeEvent e) {
    if (e.getOldValue() == null && e.getNewValue() != null && !e.getNewValue().toString().isEmpty()) {
      addNewReviewLink();
    }
  }

  private void addNewReviewLink() {
    ReviewLink reviewLink = new ReviewLink();
    reviewLink.setBookId(_book.getId());
    reviewLink.setUrl("");
//    _book.getReviewLinks().add(reviewLink);
  }

  private void addEmptyReviewLink() {
    List<ReviewLink> reviewLinks = _book.getReviewLinks();
    ReviewLink reviewLink = new ReviewLink();
    reviewLink.setBookId(_book.getId());
    reviewLinks.add(reviewLink);
  }

  private void removeEmptyReviewLink() {
    List<ReviewLink> reviewLinks = _book.getReviewLinks();
    Iterator<ReviewLink> iterator = reviewLinks.iterator();
    while (iterator.hasNext()) {
      ReviewLink reviewLink = iterator.next();
      if (reviewLink.getUrl().isEmpty()) {
        iterator.remove();
      }
    }

  }

}
