/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.data;

import de.muellerbruehl.alumniaccount.util.HashUtils;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "AccountRole")
@IdClass(AccountRoleId.class)
@XmlRootElement
public class AccountRole implements Serializable{
  
  public AccountRole(){}
  public AccountRole(String accountId, String roleName){}
  
  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @Column(name = "accountId")
  private byte[] _accountId;

  public String getAccountId() {
    return HashUtils.byte2hex(_accountId);
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property RoleName">
  @Id
  @Column(name = "roleName")
  private String _roleName;

  @Size(min = 1, max = 50)
  public String getRoleName() {
    return _roleName;
  }

  public void setRoleName(String roleName) {
    _roleName = roleName;
  }
  // </editor-fold>


}
