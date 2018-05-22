/*
 * ***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * ***********************************************************
 */
package de.muellerbruehl.books.services;

import de.muellerbruehl.books.entities.Category;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author mmueller
 */
//@Named
//@RequestScoped
@Stateless
public class CategoryService extends AbstractService<Category> {

    public CategoryService() {
        super(Category.class);
    }

    public Map<Integer, Integer> countBooksPerCategory() {
        String statement = "SELECT bcCategoryId, count(0) as cntBooks FROM Book join mapBookCategory on bookId = bcBookId group by bcCategoryId;";
        List data = getEntityManager().createNativeQuery(statement).getResultList();
        Map<Integer, Integer> result = new HashMap<>();
        for (Object x : data) {
            Object[] info = (Object[]) x;
            int categoryId = (int) info[0];
            int count = (int) (long) info[1];
            result.put(categoryId, count);
        }
        return result;
    }

}
