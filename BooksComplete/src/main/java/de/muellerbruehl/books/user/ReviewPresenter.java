/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.books.user;

import de.muellerbruehl.books.entities.Review;
import de.muellerbruehl.books.enums.Page;
import de.muellerbruehl.books.services.ReviewService;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class ReviewPresenter implements Serializable {

  @Inject private ReviewService _reviewService;
  private Review _review;

  @PostConstruct
  private void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
    try {
      if (paramMap.containsKey("reviewId")) {
        int reviewId = Integer.parseInt(paramMap.get("reviewId"));
        _review = _reviewService.find(reviewId);
      } else {
        int bookId = Integer.parseInt(paramMap.get("bookId"));
        String language = paramMap.get("language");
        _review = _reviewService.find(bookId, language);
      }
    } catch (Exception e) {
      // this might occur if an id could not be parsed or an id is not found within the DB
      NavigationHandler nav = fc.getApplication().getNavigationHandler();
      nav.handleNavigation(fc, null, Page.UserNoData.getRedirectUrl());
    }
  }

  public Review getReview() {
    return _review;
  }

}
