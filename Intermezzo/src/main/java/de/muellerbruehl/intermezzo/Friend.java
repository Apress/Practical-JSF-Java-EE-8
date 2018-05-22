/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.intermezzo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mmueller
 */
public class Friend {

  public Friend(String name) {
    _name = name;
  }

  private final List<Book> _books = new ArrayList<>();

  public List<Book> getBooks() {
    return _books;
  }

  private String _name;

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 13 * hash + Objects.hashCode(this._name);
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
    final Friend other = (Friend) obj;
    if (!Objects.equals(this._name, other._name)) {
      return false;
    }
    return true;
  }

}
