/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.data.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mmueller
 */
public class AccountInfo {

  // <editor-fold defaultstate="collapsed" desc="Property Birthday">
  @Temporal(value = TemporalType.DATE)
  @Column(name = "birthday")
  private Date _birthday;

  public void setBirthday(Date birthday) {
    _birthday = birthday;
  }
  // </editor-fold>

  public Date getBirthday() {
    return _birthday;
  }
  
}
