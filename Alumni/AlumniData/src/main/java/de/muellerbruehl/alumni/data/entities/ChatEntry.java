package de.muellerbruehl.alumni.data.entities;

import de.muellerbruehl.alumni.util.UuidUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "ChatEntry")
public class ChatEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  public ChatEntry() {
  }

  public ChatEntry(byte[] finalYearId, byte[] accountId, String message) {
    _finalYearId = finalYearId;
    _accountId = accountId;
    _message = message;
  }

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int _id;

  public int getId() {
    return _id;
  }

  public void setId(int id) {
    _id = id;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property FinalYearId">
  @Column(name = "finalYearId")
  private byte[] _finalYearId;

  public byte[] getFinalYearId() {
    return _finalYearId;
  }

  public void setFinalYearId(byte[] finalYearId) {
    this._finalYearId = finalYearId;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property AccountId">
  @Column(name = "accountId")
  private byte[] _accountId;

  public byte[] getAccountId() {
    return _accountId;
  }

  public void setAccountId(byte[] accountId) {
    _accountId = accountId;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property Message">
  @Column(name = "message")
  private String _message = "";

  public String getMessage() {
    return _message;
  }

  public void setMessage(String message) {
    _message = message;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property Moment">
  @Column(name = "moment")
  @Temporal(TemporalType.TIMESTAMP)
  private Date _moment = new Date();

  public Date getMoment() {
    return _moment;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="hashCode / equals / toString">
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 23 * hash + this._id;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ChatEntry other = (ChatEntry) obj;
    if (this._id != other._id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "de.muellerbruehl.classreunion.Class[ id=" + _accountId + " ]";
  }
  // </editor-fold>    

}
