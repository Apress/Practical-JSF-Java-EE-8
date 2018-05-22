package de.muellerbruehl.books.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Review")
public class Review implements Serializable {

  private static final long serialVersionUID = 1L;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rvId")
  private Integer _id;

  public Integer getId() {
    return _id;
  }

  public void setId(Integer id) {
    _id = id;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property BookId">
  @Column(name = "rvBookId")
  private Integer _bookId;

  public Integer getBookId() {
    return _bookId;
  }

  public void setBookId(Integer bookId) {
    _bookId = bookId;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Date">
  @Column(name = "rvDate")
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date _creationDate = null;

  public Date getCreationDate() {
    return _creationDate;
  }

  @PrePersist
  public void tagCreationDate() {
    _creationDate = Calendar.getInstance().getTime();
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Property Language">
  @Column(name = "rvLanguage")
  private String _language;

  public String getLanguage() {
    return _language;
  }

  public void setLanguage(String language) {
    _language = language;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Text">
  @Column(name = "rvText")
  private String _text;

  public String getText() {
    return _text;
  }

  public void setText(String text) {
    _text = text;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Book">
  @ManyToOne
  @JoinColumn(name = "rvBookId", insertable = false, updatable = false)
  private Book _book;

  public Book getBook() {
    return _book;
  }

  // </editor-fold>
   
  // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (_id != null ? _id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Review)) {
      return false;
    }
    Review other = (Review) object;
    if ((_id == null && other.getId() != null) || (_id != null && !_id.equals(other.getId()))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.muellerbruehl.books.entities.Review[ id=" + _id + " ]";
  }
  // </editor-fold>

}
