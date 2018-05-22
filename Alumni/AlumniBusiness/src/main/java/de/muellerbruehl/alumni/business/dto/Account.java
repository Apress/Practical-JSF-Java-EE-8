/*
 * **************************************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.business.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author mmueller
 */
public class Account implements Serializable{

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  private byte[] _id;

  public byte[] getId() {
    return _id;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Key">
  private byte[] _accessKey;

  public byte[] getAccessKey() {
    return _accessKey;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LoginName">
  private String _loginName;

  @Size(min = 1, max = 50)
  public String getLoginName() {
    return _loginName;
  }

  public void setLoginName(String loginName) {
    _loginName = loginName;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property FirstName">
  private String _firstName = "";

  @Size(min = 1, max = 50)
  public String getFirstName() {
    return _firstName;
  }

  public void setFirstName(String firstName) {
    _firstName = firstName;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LastName">
  private String _lastName;

  @Size(min = 1, max = 50, message = "{validation.lastname.size}")
  public String getLastName() {
    return _lastName;
  }

  public void setLastName(String lastName) {
    _lastName = lastName;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Email">
  private String _email = "";

  @Size(min = 6, max = 100)
  @Pattern(regexp = "(\\w[a-zA-Z_0-9+-.]*\\w|\\w+)@(\\w(\\w|-|\\.)*\\w|\\w+)\\.[a-zA-Z]+", message = "{validation.constraints.wrong.format.message} ### {validation.constraints.email.message}")
  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Password">
  private String _password = "";

  @Size(min = 6, max = 100)
  public String getPassword() {
    return _password;
  }

  public void setPassword(String password) {
    _password = password;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Birthday">
  private Date _birthday;

  public Date getBirthday() {
    return _birthday;
  }

  public void setBirthday(Date birthday) {
    _birthday = birthday;
  }
  // </editor-fold>

  public String getDisplayName() {
    return (_firstName + " " + _lastName).trim();
  }

}
