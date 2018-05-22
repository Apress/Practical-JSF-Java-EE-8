/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.data.entities;

import de.muellerbruehl.alumni.util.UuidUtil;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mmueller
 */
@Entity
@Table(name = "College")
public class College implements Serializable {
  private static final long serialVersionUID = 1L;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @Column(name = "id")
  private byte[] _id = UuidUtil.toBytes(UUID.randomUUID());

  public byte[] getId() {
    return _id;
  }

  public void setId(byte[] id) {
    _id = id;
  }
  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property CountryCode">
  @Column(name = "countryCode")
  private String _countryCode;

  public String getCountryCode() {
    return _countryCode;
  }

  public void setCountryCode(String countryCode) {
    _countryCode = countryCode;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property PostalCode">
  @Column(name = "postalCode")
  private String _postalCode;

  public String getPostalCode() {
    return _postalCode;
  }

  public void setPostalCode(String postalCode) {
    _postalCode = postalCode;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property Town">
  @Column(name = "town")
  private String _town;

  public String getTown() {
    return _town;
  }

  public void setTown(String town) {
    _town = town;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property Street">
  @Column(name = "street")
  private String _street;

  public String getStreet() {
    return _street;
  }

  public void setStreet(String street) {
    _street = street;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property Name">
  @Column(name = "name")
  private String _name = "";

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }
  // </editor-fold>    
}
