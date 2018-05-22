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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "AccountRequest")
public class AccountRequest implements Serializable {
  private static final long serialVersionUID = 1L;


  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "RequestId")
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

  @Size(min = 3, max = 50, message = "Length of first name must be between {min} and {max} characters")
  public String getFirstName() {
    return _firstName;
  }

  public void setFirstName(String firstName) {
    _firstName = firstName;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LastName">
  private String _lastName;

  @Size(min = 3, max = 50, message = "{validation.lastname.size}")
  public String getLastName() {
    return _lastName;
  }

  public void setLastName(String lastName) {
    _lastName = lastName;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Email">
  private String _email;
  
  @Size(max = 100, message = "Email must be less or equal {max} characters")
//  @Pattern(regexp=
//          "(\\w[a-zA-Z_0-9+-.]*\\w|\\w+)@(\\w(\\w|-|\\.)*\\w|\\w+)\\.[a-zA-Z]+", message = "{javax.validation.constraints.Pattern.message}")
  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }
  // </editor-fold>

}
