/*
 * ***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * ***********************************************************
 */
package de.muellerbruehl.books.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Michael Müller <michael.mueller at mueller-bruehl.de>
 */
@Entity
@Table(name = "CategoryTranslation")
public class CategoryTranslation implements Serializable {

  private static final long serialVersionUID = 1L;

  // <editor-fold defaultstate="collapsed" desc="Property Id">
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ctId")
  private int _id = -1;

  public int getId() {
    return _id;
  }

  public void setId(int id) {
    _id = id;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property CategoryId">
  @Column(name = "ctCategoryId")
  private int _categoryId = -1;

  public int getCategoryId() {
    return _categoryId;
  }

  public void setCategoryId(int id) {
    _categoryId = id;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Language">
  @Column(name = "ctLanguage")
  private String _language;

  public String getLanguage() {
    return _language;
  }

  public void setLanguage(String language) {
    _language = language;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="Property Name">
  @Column(name = "ctName")
  private String _name;

  public String getName() {
    return _name;
  }

  public void setName(String name) {
    _name = name;
  }
    // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
  @Override
  public int hashCode() {
    if (_id < 0) {
      int hash = 3;
      hash = 89 * hash + _categoryId;
      hash = 89 * hash + Objects.hashCode(_language);
      return hash;
    }
    return _id;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof CategoryTranslation)) {
      return false;
    }
    CategoryTranslation other = (CategoryTranslation) object;
    if (_id < 0 && other._id < 0) {
      return _categoryId == other._categoryId
              && _language.equals(other._language);
    }
    return _id == other._id;
  }

  @Override
  public String toString() {
    return "CategoryTranlation[ id=" + _id + "] " + _name;
  }
  // </editor-fold>

}
