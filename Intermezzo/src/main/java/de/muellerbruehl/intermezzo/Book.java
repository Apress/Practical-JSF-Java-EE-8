/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.intermezzo;

import java.util.Objects;

/**
 *
 * @author mmueller
 */
public class Book {

  public Book(String name) {
    _title = name;
  }
  
  private String _title;

  public String getTitle() {
    return _title;
  }

  public void setTitle(String title) {
    _title = title;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + Objects.hashCode(_title);
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
    final Book other = (Book) obj;
    if (!Objects.equals(this._title, other._title)) {
      return false;
    }
    return true;
  }
  
}
