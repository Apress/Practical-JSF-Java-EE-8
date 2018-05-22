/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.data;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author mmueller
 */
public class AccountRoleId {

  private byte[] _accountId;

  private String _roleName;

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 97 * hash + Arrays.hashCode(this._accountId);
    hash = 97 * hash + Objects.hashCode(this._roleName);
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
    final AccountRoleId other = (AccountRoleId) obj;
    if (!Objects.equals(this._roleName, other._roleName)) {
      return false;
    }
    return Arrays.equals(this._accountId, other._accountId);
  }

  
}
