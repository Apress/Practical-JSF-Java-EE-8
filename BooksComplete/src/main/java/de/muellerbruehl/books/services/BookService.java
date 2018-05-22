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

import de.muellerbruehl.books.entities.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 *
 * @author mmueller
 */
//@Named
//@RequestScoped
@Stateless
public class BookService extends AbstractService<Book> {

  public BookService() {
    super(Book.class);
  }

  /**
   * Find all books ordered by the given field This methods shows the usage of
   * the criteria query
   *
   * @param field
   * @return
   */
  public List<Book> findAllBy(String field) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Book> cq = cb.createQuery(Book.class);
    Root request = cq.from(Book.class);
    Order order = cb.asc(request.get(field));
    cq.select(request).orderBy(order);
    return getEntityManager().createQuery(cq).getResultList();
  }

  /**
   * Find all books of the given category This methods is an example for using a
   * native SQL query
   *
   * @param categoryId
   * @return
   */
  public List<Book> findByCategory(int categoryId) {
    String statement = "SELECT Book.* FROM Book join mapBookCategory on bookId = bcBookId and bcCategoryId = ?1;";
    return getEntityManager().createNativeQuery(statement, Book.class).setParameter(1, categoryId).getResultList();

  }

    public List<Book> findBySearchText(String fragment, String languageCode) {
        String sqlQuery = "select Book.*"
                + " from Book left join BookTrans on bookId = btBookId and btLanguage = ?1"
                //" where bookTitle like (?2) or bookSubtitle like (?2) or bookAuthor like (?2) or bookPublisher like (?2) or isnull (btShortText, bookShorttext) like (?2) or bookISBN like (?2) or replace (bookISBN, '-', '') like (?2)" // MS SQL Server
                + " where bookTitle like (?2) or bookSubtitle like (?2) or bookAuthor like (?2) or bookPublisher like (?2) or ifnull (btShortText, bookShorttext) like (?2) or bookISBN like (?2) or replace (bookISBN, '-', '') like (?2)"
                + " order by bookId desc;";
        return getEntityManager().createNativeQuery(sqlQuery, Book.class).setParameter(1, languageCode).setParameter(2, "%" + fragment + "%").getResultList();
    }

}
