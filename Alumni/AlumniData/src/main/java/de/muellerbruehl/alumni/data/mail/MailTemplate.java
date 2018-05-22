/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.data.mail;

import de.muellerbruehl.alumni.util.UuidUtil;
import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "MailTemplate")
public class MailTemplate implements Serializable {

  private static final long serialVersionUID = 1L;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @Column(name = "id")
  private byte[] _id = UuidUtil.makeUuidAsBytes();

  public byte[] getId() {
    return _id;
  }

  public void setId(byte[] id) {
    _id = id;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Name">
  @Column(name = "name")
  private String _name = "";

  @Size(max = 100)
  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property Subject">
  @Column(name = "subject")
  private String _subject = "";

  @Size(max = 200)
  public String getSubject() {
    return _subject;
  }

  public void setSubject(String subject) {
    _subject = subject;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="Property Body">
  @Column(name = "body")
  private String _body = "";

  @Size(max = 4000)
  public String getBody() {
    return _body;
  }

  public void setBody(String body) {
    _body = body;
  }
  // </editor-fold>    

  // <editor-fold defaultstate="collapsed" desc="hashCode & equals">
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Arrays.hashCode(_id);
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
    final MailTemplate other = (MailTemplate) obj;
    return Arrays.equals(_id, other._id);
  }
  // </editor-fold>    

}
