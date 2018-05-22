/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.books.common;

import de.muellerbruehl.books.entities.Review;
import de.muellerbruehl.books.enums.Mode;
import de.muellerbruehl.books.enums.Page;
import de.muellerbruehl.books.helper.ReviewInfo;
import de.muellerbruehl.books.helper.SessionTools;
import de.muellerbruehl.books.services.ReviewService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class ReviewListController {

  @Inject ReviewService _reviewService;
  @Inject SessionTools _sessionTools;

  private List<Review> _recentReviewList;
  private List<ReviewInfo> _reviewInfos;
  private String _field = "title";

  @PostConstruct
  private void init() {
    FacesContext fc = FacesContext.getCurrentInstance();
    Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
    _field = "title";
    if (paramMap.containsKey("field")) {
      String field = paramMap.get("field");
      List<String> allowed = Arrays.asList("title", "language", "location");
      if (field != null && allowed.contains(field)) {
        _field = field;
      }
    }
  }

  public List<Review> getRecentReviews() {
    if (_recentReviewList == null) {
      _recentReviewList = _reviewService.findRecent(_sessionTools.getLanguage());
    }
    return _recentReviewList;
  }

  public List<ReviewInfo> getReviews() {
    if (_reviewInfos == null) {
      _reviewInfos = _reviewService.findReviews();
      if (_sessionTools.getMode() == Mode.User) {
        _reviewInfos.addAll(_reviewService.findReviewLinks());
      }
    }
    switch (_field) {
      case "title":
        return _reviewInfos.stream().sorted((r1, r2) -> r1.getTitle().compareTo(r2.getTitle())).collect(Collectors.toList());
      case "language":
        return _reviewInfos.stream().sorted((r1, r2) -> r1.getLanguage().compareTo(r2.getLanguage())).collect(Collectors.toList());
      case "location":
        return _reviewInfos.stream().sorted((r1, r2) -> Boolean.compare(r1.isIntern(), r2.isIntern())).collect(Collectors.toList());
    }
    return _reviewInfos;
  }

}
