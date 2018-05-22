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
package de.muellerbruehl.alumni.data.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "Acount")
public class Account implements Serializable  {

    private static final long serialVersionUID = 1L;

    public Account(){
      _firstName = "";
      _lastName = "";
    }

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bookId")
  public int getId() {
    return _id;
  }

  public void setId(int id) {
    _id = id;
  }

  private int _id = -1;
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property FirstName">
  private String _firstName;
  public String getFirstName() {
    return _firstName;
  }

  public void setFirstName(String firstName) {
    if (firstName == null){
      throw new IllegalArgumentException("FirstName must not be null");
    }
    _firstName = firstName;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LastName">
  private String _lastName;
  public String getLastName() {
    return _lastName;
  }

  public void setLastName(String lastName) {
    if (lastName == null){
      throw new IllegalArgumentException("LastName must not be null");
    }
    _lastName = lastName;
  }
  // </editor-fold>
  

  // <editor-fold defaultstate="collapsed" desc="Property UserName">
  private String _userName;
  public String getUserName() {
    return _userName;
  }

  public void setUserName(String userName) {
    _userName = userName;
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

  // <editor-fold defaultstate="collapsed" desc="Property Email">
  private String _email;
  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Password">
  private String _password;
  public String getPassword() {
    return _password;
  }

  public void setPassword(String password) {
    _password = password;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Birthyear">
  private int _birthYear;
  public int getBirthYear() {
    return _birthYear;
  }

  public void setBirthYear(int birthYear) {
    _birthYear = birthYear;
  }
  // </editor-fold>


    public String getDisplayName() {
        if (_lastName.length() > 0 && _firstName.length() > 0) {
            return _lastName + ", " + _firstName;
        }
        return _lastName + _firstName;
    }
    
    // </editor-fold>    

}
