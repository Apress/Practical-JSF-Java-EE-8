package de.muellerbruehl.books.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mmueller
 */
public enum Page {

  BookList("/common/bookList"),
  UserTemplate("/user/booksTemplate"),
  UserWelcome("/user/welcome"),
  UserNoData("/user/noData"),
  UserBookPresenter("/user/bookPresenter"),
  UserReview("/user/review"),
  UserReviewList("/user/reviewList"),
  AdminTemplate("/admin/adminTemplate"),
  AdminWelcome("/admin/welcome"),
  AdminNoSave("/admin/noSave"),
  AdminCategoryEditor("/admin/categoryEditor"),
  AdminCategoryTranslator("/admin/categoryTranslator"),
  AdminBookEditor("/admin/bookEditor"),
  AdminReviewEditor("/admin/reviewEditor"),
  AdminReviewList("/admin/reviewList");

  ;

  private Page(String url) {
    _url = url;
  }
  private final String _url;

  public String getUrl() {
    return _url + ".xhtml";
  }

  public String getRedirectUrl() {
    return _url + ".xhtml?faces-redirect=true";
  }

  private static Map<String, String> _pages;

  public static Map<String, String> getPages() {
    if (_pages == null) {
      _pages = new HashMap<>();
      for (Page page : Page.values()) {
        _pages.put(page.name(), page.getUrl());
      }
    }
    return _pages;
  }
}
