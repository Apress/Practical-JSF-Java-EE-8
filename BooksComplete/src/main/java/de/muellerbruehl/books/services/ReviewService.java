/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.books.services;

import de.muellerbruehl.books.entities.Review;
import de.muellerbruehl.books.entities.ReviewLink;
import de.muellerbruehl.books.helper.ReviewInfo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author mmueller
 */
@Stateless
public class ReviewService extends AbstractService<Review> {

  public ReviewService() {
    super(Review.class);
  }

  public Review find(int bookId, String language) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Review> query = cb.createQuery(Review.class);
    Root<Review> request = query.from(Review.class);
    query.select(request).where(cb.and(cb.equal(request.get("_bookId"), bookId)), cb.equal(request.get("_language"), language));
    TypedQuery<Review> q = getEntityManager().createQuery(query);
    return q.getSingleResult();
  }

  public List<Review> findRecent(String language) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Review> query = cb.createQuery(Review.class);
    Root request = query.from(Review.class);
    query.select(request).where(cb.equal(request.get("_language"), language)).orderBy(cb.desc(request.get("_creationDate")));
    return getEntityManager().createQuery(query).setMaxResults(5).getResultList();
  }

  public List<ReviewInfo> findReviews() {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<ReviewInfo> query = cb.createQuery(ReviewInfo.class);
    Root request = query.from(Review.class);
    query.select(cb.construct(ReviewInfo.class, request.get("_book").get("title"), request.get("_language"), request.get("_bookId")));
    return getEntityManager().createQuery(query).getResultList();
  }

  public List<ReviewInfo> findReviewLinks() {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<ReviewInfo> query = cb.createQuery(ReviewInfo.class);
    Root request = query.from(ReviewLink.class);
    query.select(cb.construct(ReviewInfo.class, request.get("_book").get("title"), request.get("_language"), request.get("_url")));
    return getEntityManager().createQuery(query).getResultList();
  }

}
