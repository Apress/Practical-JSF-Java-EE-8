package de.muellerbruehl.books.user;

import de.muellerbruehl.books.entities.Book;
import de.muellerbruehl.books.helper.SessionTools;
import de.muellerbruehl.books.services.BookService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class BookPresenter implements Serializable{

  @Inject private SessionTools _sessionTools;
  private List<Book> _bookList = new ArrayList<>();

  public List<Book> getBookList() {
    return _bookList;
  }

  public void setBookList(List<Book> bookList) {
    _bookList = bookList;
  }
  @Inject private BookService _bookService;

  @PostConstruct
  private void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
    try {
      if (paramMap.containsKey("bookId")) {
        int bookId = Integer.parseInt(paramMap.get("bookId"));
        Book book = _bookService.find(bookId);
        if (book != null) {
          _bookList.add(book);
        }
      } else if (paramMap.containsKey("categoryId")) {
        int categoryId = Integer.parseInt(paramMap.get("categoryId"));
        _bookList = _bookService.findByCategory(categoryId);
      } else if (paramMap.containsKey("searchText")) {
          String searchText = paramMap.get("searchText");
        _bookList = _bookService.findBySearchText(searchText, _sessionTools.getLanguage());
      }
    } catch (NumberFormatException e) {
      // ignore
    }
  }
}
