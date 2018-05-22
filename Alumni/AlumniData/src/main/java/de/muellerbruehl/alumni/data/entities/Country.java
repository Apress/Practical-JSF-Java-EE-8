/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.data.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "Country")
public class Country implements Serializable {
  
  //<editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @Column(name = "id")
  private int _id;
  
  public int getId() {
    return _id;
  }
  
  public void setId(int id) {
    _id = id;
  }
  //</editor-fold>
  
  //<editor-fold defaultstate="collapsed" desc="Property LangCode">
  @Column(name = "langCode")
  private String _langCode;
  
  public String getLangCode() {
    return _langCode;
  }

  public void setLangCode(String langCode) {
    _langCode = langCode;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Property CountryCode">
  @Column(name = "countryCode")
  private String _countryCode;
  
  public String getCountryCode() {
    return _countryCode;
  }

  public void setCountryCode(String countryCode) {
    _countryCode = countryCode;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Property Name">
  @Column(name = "name")
  private String _name;

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="hashCode & equals">
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 61 * hash + _id;
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
    final Country other = (Country) obj;
    if (_id != other._id) {
      return false;
    }
    return true;
  }
  //</editor-fold>
  
}
