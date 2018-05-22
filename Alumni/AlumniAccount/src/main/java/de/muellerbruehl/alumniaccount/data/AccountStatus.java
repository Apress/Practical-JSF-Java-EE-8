/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.data;

/**
 *
 * @author mmueller
 */
public enum AccountStatus {
  New,
  Active,
  Inactive,
  Retired;
  
  public String getKey() {
    return name().substring(0, 1);
  }
  
  public static AccountStatus fromKey(String key){
    for (AccountStatus status : values()) {
      if (status.getKey().equals(key)){
        return status;
      }
    }
    throw new IllegalArgumentException("AccountStatus, unknown key: " + key);
  }
}
