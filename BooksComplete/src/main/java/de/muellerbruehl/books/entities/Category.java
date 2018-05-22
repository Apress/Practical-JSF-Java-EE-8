/*
 * ***********************************************************
 * This software is created by Michael MÃ¼ller.
 * (c) all rights reserved.
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * ***********************************************************
 */
package de.muellerbruehl.books.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Michael MÃ¼ller <michael.mueller at mueller-bruehl.de>
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    // <editor-fold defaultstate="collapsed" desc="Property Id">
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catId")
    private int _id = -1;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Name">
    @Column(name = "catName")
    private String _name;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property TranslationList">
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ctCategoryId", referencedColumnName = "catId")
    @MapKey(name = "_language")
    private Map<String, CategoryTranslation> _catTranslations = new HashMap<>();

    public Map<String, CategoryTranslation> getCategoryTranslations() {
        return _catTranslations;
    }

    public void setCategoryTranslations(Map<String, CategoryTranslation> catTranslations) {
        _catTranslations = catTranslations;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property Quantity">
    @Transient
    private int _quantity;

    public int getQuantity() {
        return _quantity;
    }

    public void setQuantity(int quantity) {
        _quantity = quantity;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Property QuantityClass">
    @Transient
    private int _quantityClass;

    public int getQuantityClass() {
        return _quantityClass;
    }

    public void setQuantityClass(int quantityClass) {
        _quantityClass = quantityClass;
    }
    // </editor-fold>

    public String getTranslatedName(String langCode) {
        if (_catTranslations.containsKey(langCode)) {
            return _catTranslations.get(langCode).getName();
        }
        return "";
    }

    public void setTranslatedName(String langCode, String name) {
        if (_catTranslations.containsKey(langCode)) {
            _catTranslations.get(langCode).setName(name);
        } else {
            CategoryTranslation translation = new CategoryTranslation();
            translation.setLanguage(langCode);
            translation.setCategoryId(_id);
            translation.setName(name);
            _catTranslations.put(langCode, translation);
        }
    }

    public String getTranslatedNameOrDefault(String langCode) {
        if (_catTranslations.containsKey(langCode)) {
            String name = _catTranslations.get(langCode).getName();
            if (name.isEmpty()) {
                return _name;
            }
            return name;
        }
        return _name;
    }

    // <editor-fold defaultstate="collapsed" desc="hashcode / equals / toString">
    @Override
    public int hashCode() {
        if (_id < 0) {
            return _name.hashCode();
        }
        return _id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if (_id < 0 && other._id < 0) {
            return _name.equals(other._name);
        }
        return _id == other._id;
    }

    @Override
    public String toString() {
        return "Category[ id=" + _id + "] " + _name;
    }
    // </editor-fold>

}
