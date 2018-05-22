/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.books.helper.converter;

import de.muellerbruehl.books.entities.Category;
import de.muellerbruehl.books.helper.SessionTools;
import de.muellerbruehl.books.services.CategoryService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author mmueller
 */
@FacesConverter(value = "CategoryConverter", forClass = Category.class)
@Named
@Singleton
public class CategoryConverter implements Serializable, Converter {

    @Inject
    private CategoryService _categoryService;
    @Inject
    private SessionTools _sessionTools;

    private static List<Category> _categories;

    public CategoryConverter() {
        System.out.println("CategoryConverter");
    }

    @PostConstruct
    private void init() {
    }

    public List<Category> getCategories() {
        if (_categories == null) {
            _categories = _categoryService.findAll();
        }
        return _categories;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt(value);
            return getCategories().stream().filter(c -> c.getId() == id).findFirst().get();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Category category = (Category) value;
        return "" + category.getId();

    }

}
