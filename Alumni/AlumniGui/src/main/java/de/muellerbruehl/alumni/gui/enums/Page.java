/*
 * **************************************************************************
 * This software is created by Michael M??ller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.gui.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mmueller
 */
public enum Page {
  Activate("/public/activate"),
  Login("/public/login"),
  Register("/public/register"),
  RequestPassword("/public/requestPassword"),
  LoginError("/public/loginError"),
  
  UserHome("/member/userHome"),
  JoinFinalClass("/member/joinFinalClass"),
  Classroom("/member/classroom"),

  AdminTasks("/admin/tasks")
  ;

  private Page(String url) {
    _url = url;
  }
  private final String _url;

  public String url() {
    return _url + ".xhtml";
  }

  public String urlRedirect() {
    return _url + ".xhtml?faces-redirect=true";
  }

  private static Map<String, String> _pages;

  public static Map<String, String> getPages() {
    if (_pages == null) {
      _pages = new HashMap<>();
      for (Page page : Page.values()) {
        _pages.put(page.name(), page.url());
      }
    }
    return _pages;
  }
}
