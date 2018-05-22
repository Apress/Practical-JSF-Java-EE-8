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
package de.muellerbruehl.alumni.gui.user;

import de.muellerbruehl.alumni.business.dto.Account;
import de.muellerbruehl.alumni.gui.enums.Page;
import java.io.Serializable;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mmueller
 */
@Named
@SessionScoped
public class UserController implements Serializable {

  //@Inject private AccountService _accountService;

  private Account _account;

  @PostConstruct
  public void init() {
  }

  public void obtainAccount(String loginName) {
    //_account = _accountService.findAccountByName(loginName);
  }

  public String getClassRoom() {
    return Page.Classroom.url();
  }

  public Account getAccount() {
    return _account;
  }

  public String getRoles() {
    List<String> allRoles = Arrays.asList(new String[]{"admin", "member"});
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    Principal userPrincipal = request.getUserPrincipal();
    String userRoles = allRoles.stream().filter(r -> request.isUserInRole(r)).collect(Collectors.joining(", "));
    return userPrincipal.getName() + ": " + userRoles;
  }

  private byte[] _finalYearId;

  public byte[] getFinalYearId() {
    return _finalYearId;
  }

  public void setFinalYearId(byte[] finalYearId) {
    _finalYearId = finalYearId;
  }

  public int getLastMessageId() {
    return 3;
  }
}
