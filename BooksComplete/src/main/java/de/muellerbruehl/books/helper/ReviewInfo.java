package de.muellerbruehl.books.helper;

import de.muellerbruehl.books.entities.Book;
import de.muellerbruehl.books.enums.Page;
import javax.faces.context.FacesContext;

/**
 *
 * @author mmueller
 */
public class ReviewInfo {

  public ReviewInfo(String title, String language, String url) {
    _title = title;
    _language = language;
    _url = url;
  }

  public ReviewInfo(String title, String language, int bookId) {
    _title = title;
    _language = language;
    _bookId = bookId;
    _url = buildUrl(bookId, language);
  }

  public ReviewInfo(Book book, String language) {
    _title = book.getTitle();
    _language = language;
    _bookId = book.getId();
    _url = buildUrl(_bookId, language);
  }

  private String buildUrl(int bookId, String language) {
    String path = FacesContext.getCurrentInstance()
            .getExternalContext().getRequestContextPath();
    return path + Page.UserReview.getUrl() + 
            "?bookId=" + bookId + "&language=" + language;
  }

  // <editor-fold defaultstate="collapsed" desc="Property BookId">
  private int _bookId;

  public int getBookId() {
    return _bookId;
  }

  public void setBookId(int bookId) {
    _bookId = bookId;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Title">
  private String _title;

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LanguageCode">
  private String _language;

  public String getLanguage() {
    return _language;
  }

  public void setLanguage(String language) {
    _language = language;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property URL">
  private String _url;

  public String getUrl() {
    return _url;
  }

  public void setUrl(String url) {
    _url = url;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Intern">
  public boolean isIntern() {
    String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    return _url.startsWith(path + Page.UserReview.getUrl());
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
  @Override
  public int hashCode() {
    int hash = 47 * _title.hashCode() + _language.hashCode();
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ReviewInfo)) {
      return false;
    }
    ReviewInfo other = (ReviewInfo) object;
    return _title.equals(other.getTitle()) && _language.equals(other.getLanguage());
  }
  // </editor-fold>
}
