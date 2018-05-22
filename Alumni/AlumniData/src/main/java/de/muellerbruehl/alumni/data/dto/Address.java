package de.muellerbruehl.alumni.data.dto;

import java.util.Objects;

public class Address {
  
  // <editor-fold defaultstate="collapsed" desc="Property CountryCode">
  private String _countryCode;

  public String getCountryCode() {
    return _countryCode;
  }

  public void setCountryCode(String countryCode) {
    _countryCode = countryCode;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property PostalCode">
  private String _postalCode;

  public String getPostalCode() {
    return _postalCode;
  }

  public void setPostalCode(String postalCode) {
    _postalCode = postalCode;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property Town">
  private String _town;

  public String getTown() {
    return _town;
  }

  public void setTown(String town) {
    _town = town;
  }
  // </editor-fold>    
  
  // <editor-fold defaultstate="collapsed" desc="Property Street">
  private String _street;

  public String getStreet() {
    return _street;
  }

  public void setStreet(String street) {
    _street = street;
  }
  // </editor-fold>    

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(_countryCode);
    hash = 89 * hash + Objects.hashCode(_postalCode);
    hash = 89 * hash + Objects.hashCode(_town);
    hash = 89 * hash + Objects.hashCode(_street);
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
    final Address other = (Address) obj;
    if (!Objects.equals(_countryCode, other._countryCode)) {
      return false;
    }
    if (!Objects.equals(_postalCode, other._postalCode)) {
      return false;
    }
    if (!Objects.equals(_town, other._town)) {
      return false;
    }
    return Objects.equals(_street, other._street);
  }
   
}
