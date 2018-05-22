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
package de.muellerbruehl.alumniaccount.data;

import de.muellerbruehl.alumniaccount.util.HashUtils;
import static de.muellerbruehl.alumniaccount.util.UuidUtil.makeUuidAsBytes;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "Account")
@XmlRootElement
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final int KEY_LEN = 1024;
  private static final int ROUNDS = 100_021;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @Column(name = "id")
  private final byte[] _id = makeUuidAsBytes();

  public String getId() {
    return HashUtils.byte2hex(_id);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Key">
  @Column(name = "accessKey")
  private final byte[] _accessKey = makeUuidAsBytes();

  public String getAccessKey() {
    return HashUtils.byte2hex(_accessKey);
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Property Status">
  @Column(name = "status")
  private AccountStatus _status = AccountStatus.New;

  public AccountStatus getStatus() {
    return _status;
  }

  public void setStatus(AccountStatus status) {
    _status = status;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LoginName">
  @Column(name = "loginName")
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
  @Column(name = "firstName")
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
  @Column(name = "lastName")
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
  @Column(name = "email")
  private String _email = "";

  @Size(min = 6, max = 100)
  public String getEmail() {
    return _email;
  }

  public void setEmail(String email) {
    _email = email;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property LastChanged">
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  @Column(name = "lastChanged")
  private Date _lastChanged = new Date();

  public Date getLastChanged() {
    return _lastChanged;
  }

  public void setLastChanged(Date lastChanged) {
    _lastChanged = lastChanged;
  }

  @PreUpdate
  private void tagLastChanged() {
    _lastChanged = new Date();
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Created">
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  @Column(name = "created")
  private Date _created = new Date();

  public Date getCreated() {
    return _created;
  }

  public void setCreated(Date created) {
    _created = created;
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Property Password">
  @Column(name = "password")
  private byte[] _passwordHash;

  public void setPassword(String password){
    _passwordHash = obtainPasswordHash(password);
  }

  public boolean checkPassword(String password) {
    return _status == AccountStatus.Active 
            && Arrays.equals(obtainPasswordHash(password), _passwordHash);
  }

  private byte[] obtainPasswordHash(String password) {
    byte[] passwordHash = HashUtils.hashPassword(password, makeSalt(), ROUNDS, KEY_LEN);
    return passwordHash;
  }

  private byte[] makeSalt() {
    byte[] salt = new byte[32];
    System.arraycopy(_id, 0, salt, 0, 16);
    System.arraycopy(_accessKey, 0, salt, 16, 16);
    return salt;
  }
  // </editor-fold>

}
