package de.muellerbruehl.books.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "ReviewLink")
public class ReviewLink implements Serializable {

  private static final long serialVersionUID = 1L;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Integer _id;

  public Integer getId() {
    return _id;
  }

  public void setId(Integer id) {
    _id = id;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property BookId">
  @Column(name = "BookId")
  private Integer _bookId;

  public Integer getBookId() {
    return _bookId;
  }

  public void setBookId(Integer bookId) {
    _bookId = bookId;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LanguageCode">
  @Column(name = "LanguageCode")
  private String _language;

  public String getLanguage() {
    return _language;
  }

  public void setLanguage(String language) {
    _language = language;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property URL">
  @Column(name = "URL")
  private String _url = "";

  public String getUrl() {
    return _url;
  }

  public void setUrl(String url) {
    _url = url;
  }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Property Book">
  @ManyToOne
  @JoinColumn(name = "BookId", insertable = false, updatable = false)
  private Book _book;

  public Book getBook() {
    return _book;
  }
  
  // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (getId() != null ? getId().hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ReviewLink)) {
      return false;
    }
    ReviewLink other = (ReviewLink) object;
    if ((getId() == null && other.getId() != null) || (getId() != null && !_id.equals(other.getId()))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.muellerbruehl.books.entities.ReviewLink[ id=" + getId() + " ]";
  }
    // </editor-fold>
}
