/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.books.helper;

import de.muellerbruehl.books.enums.Mode;
import de.muellerbruehl.books.enums.Page;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.el.ELContextEvent;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author mmueller
 */
@Named
@SessionScoped
public class SessionTools implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger _logger = Logger.getLogger("SessionController");

  Mode _mode = Mode.User;

  public SessionTools() {
    System.out.println("SessionTools started");
  }

  public Mode getMode() {
    return _mode;
  }

  public void preRenderView(Mode mode) {
    _mode = mode;
  }

  public String getTemplate() {
    return _mode == Mode.User ? Page.UserTemplate.getUrl() : Page.AdminTemplate.getUrl();
  }

  public String getBookListTarget() {
    return _mode == Mode.Admin ? Page.AdminBookEditor.getUrl() : Page.UserBookPresenter.getUrl();
  }

  @PostConstruct
  public void init() {
    FacesContext.getCurrentInstance().getApplication().addELContextListener((ELContextEvent event) -> {
      event.getELContext().getImportHandler().importPackage("de.muellerbruehl.books.enums.*");
    });
    FacesContext.getCurrentInstance().getApplication().addELContextListener((ELContextEvent event) -> {
      event.getELContext().getImportHandler().importClass("de.muellerbruehl.books.enums.Page");
    });
  }
  
  public Map<String, String> getPages() {
    return Page.getPages();
  }

  public String navigate(Page page) {
    _logger.log(Level.WARNING, "Navigate to {0}", page.getUrl());
    endAllConversations();
    return page.getRedirectUrl();
  }

  public String beginConversation(Conversation conversation) {
    if (conversation.isTransient()) {
      int minutes = 15;
      conversation.setTimeout(minutes * 60000);
      conversation.begin(UUID.randomUUID().toString());
      _logger.log(Level.WARNING, "Conversation started: {0}", conversation.getId());
      return conversation.getId();
    } else {
      _logger.log(Level.WARNING, "Conversation still running: {0}", conversation.getId());
      return conversation.getId();
    }
  }

  public void endConversation(Conversation conversation) {
    if (!conversation.isTransient()) {
      _logger.log(Level.WARNING, "Conversation stopping: {0}", conversation.getId());
      conversation.end();
    }
  }

  public void endAllConversations() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Map<String, Object> map = facesContext.getExternalContext().getSessionMap();
    Map<String, Conversation> conversations = (Map<String, Conversation>) map.get("org.jboss.weld.context.ConversationContext.conversations");
    for (Conversation conversation : conversations.values()) {
      endConversation(conversation);
    }
  }

  public String getAdInfo(String productid, String isbn) {
    if (productid.isEmpty()) {
      return "";
    }
    if (isbn.isEmpty()) {
      isbn = productid;
    }
    isbn = isbn.trim().replace("-", "");

    String adInfo = Utilities.getMessage(_language, "adInfo");
    return adInfo.replace("{productid}", productid).replace("{isbn}", isbn);

  }

  public boolean isInternalClient() {
    String ip = getClientIP();
    return ip.equals("127.0.0.1");
  }

  public String getClientIP() {
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String forwardInfo = request.getHeader("X-FORWARDED-FOR");
    if (forwardInfo == null) {
      return request.getRemoteAddr();
    }
    return forwardInfo.split(",")[0];
  }

  public String getUserAgent() {
    String userAgent = "unknown";
    try {
      Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();

      if (request instanceof HttpServletRequestWrapper) {
        userAgent = ((HttpServletRequestWrapper) request).getHeader("user-agent");
      } else if (request instanceof HttpServletRequest) {
        userAgent = ((HttpServletRequest) request).getHeader("user-agent");
      } else {
        userAgent = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("user-agent");
      }
    } catch (Exception ex) {
    }
    return userAgent;
  }

  String _language = Utilities.getDefaultLanguage();

  public void setLanguage(String language) {
    _language = language;
  }

  public String getLanguage() {
    return _language;
  }

  public String getDisplayLanguage(String language){
    Locale locale = new Locale(language);
    return locale.getDisplayLanguage();
  }
  
  public String changeLanguage(String languageCode) {
    if (Utilities.getSupportedLanguages(Utilities.HandleDefault.Include).contains(languageCode)) {
      _language = languageCode;
    }
    return Page.UserWelcome.getRedirectUrl();
  }

  public String gotoBlog() {
    return "http://blog.mueller-bruehl.de";
  }

  public String encodeResourceURL(String url) {
    return FacesContext.getCurrentInstance().getExternalContext().encodeResourceURL(url);
  }

  String _searchText = "";

  public String getSearchText() {
    return _searchText;
  }

  public void setSearchText(String searchText) {
    _searchText = searchText;
  }

  public String search() {
    if (_searchText.length() < 3) {
      return "";
    }
    Page target = _mode == Mode.Admin ? Page.BookList : Page.UserBookPresenter;
    String url = target.getUrl() + "?searchText=" + _searchText;
    //_searchText = "";
    return url;
  }
//    public List<SimpleLink> getAllReviews() {
//        if (_reviewLinks == null) {
//            _reviewLinks = _reviewLinkFacade.getAllReviewLinks();
//        }
//        return _reviewLinks;
//    }
//
//  List<SimpleLink> _recentReviewLinks;
//    public List<SimpleLink> getRecentReviews() {
//        if (_recentReviewLinks == null) {
//            _recentReviewLinks = _reviewLinkFacade.getRecentReviewLinks(_language);
//        }
//        return _recentReviewLinks;
//    }
//
//    public List<SimpleLink> getBooks() {
//        if (_bookLinks == null) {
//            _bookLinks = _bookFacade.getBookLinks();
//        }
//        return _bookLinks;
//    }
//
//    public List<SimpleLink> getAdminBookList() {
//        endConversation();
//        if (_adminBookLinks == null) {
//            _adminBookLinks = _bookFacade.getAdminBookLinks();
//        }
//        return _adminBookLinks;
//    }
//
//    public void clearCache() {
//        _bookLinks = null;
//        _adminBookLinks = null;
//        _recentReviewLinks = null;
//        _reviewLinks = null;
//    }
}
