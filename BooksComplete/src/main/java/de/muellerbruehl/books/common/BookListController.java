package de.muellerbruehl.books.common;

import de.muellerbruehl.books.entities.Book;
import de.muellerbruehl.books.helper.SessionTools;
import de.muellerbruehl.books.services.BookService;
import java.util.Arrays;
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
public class BookListController {

  @Inject BookService _bookService;
  @Inject private SessionTools _sessionTools;

  private List<Book> _bookList;
  private String _field = "title";
  private String _searchText = "";

  public String getSearchText() {
    return _searchText;
  }

  public void setSearchText(String searchText) {
    _searchText = searchText;
  }
  
  @PostConstruct
  private void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
    _field = "title";
    if (paramMap.containsKey("field")) {
      String field = paramMap.get("field");
      List<String> allowed = Arrays.asList("title", "publisher", "year");
      if (field != null && allowed.contains(field)) {
        _field = field;
      }
    }
    if (paramMap.containsKey("searchText")) {
      _searchText = paramMap.get("searchText");
    }
  }

  /**
   * gets and caches the booklist the cache speeds up subsequent list requests
   * during the same HTTP request
   *
   * @return
   */
  public List<Book> getBookList() {
    if (_bookList == null) {
      if (_searchText.isEmpty()){
      _bookList = _bookService.findAllBy(_field);
      }else{
        _bookList = _bookService.findBySearchText(_searchText, _sessionTools.getLanguage());
      }
    }
    return _bookList;
  }
}
