/*
 * **************************************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.gui.login;

import de.muellerbruehl.alumni.gui.enums.Page;
import de.muellerbruehl.alumni.gui.user.UserController;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mmueller
 */
@Named
@ViewScoped
public class Login implements Serializable {

  private static final Logger LOGGER = Logger.getLogger("Login");
  private static final long serialVersionUID = 1L;

  private String _loginName = "";
  private String _password;
  private final UserController _userController;
  
  @Inject
  public Login (UserController userController){
    _userController = userController;
  }
  
  //<editor-fold defaultstate="collapsed" desc="geteer/setter">
  public String getLoginName() {
    return _loginName;
  }
  
  public void setLoginName(String loginName) {
    _loginName = loginName;
  }
  
  public String getPassword() {
    return _password;
  }
  
  public void setPassword(String password) {
    _password = password;
  }
  //</editor-fold>
  
  public String login() {

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

    //_eventPatcher.sendMessage("User tries to log in: " + _loginName);
    
    try {
      request.logout();
      request.login(_loginName, _password);
    } catch (ServletException e) {
      if ("This is request has already been authenticated".equals(e.getMessage())) {
        return Page.UserHome.url();
      }
      context.addMessage(null, new FacesMessage("Login failed."));
      return Page.LoginError.url();
    }

    _userController.obtainAccount(_loginName);
    //_eventPatcher.sendMessage("User logged in: " + _loginName);
    return Page.UserHome.url();
  }

  public String logout() {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    try {
      request.logout();
      System.out.println("successfuly logged out");
    } catch (ServletException e) {
      context.addMessage(null, new FacesMessage("Logout failed."));
    }
    return Page.Login.url();
  }

}
