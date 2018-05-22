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
package de.muellerbruehl.alumni.login;

import de.muellerbruehl.alumni.data.entities.AccountRequest;
import de.muellerbruehl.alumni.data.services.RegisterService;
import de.muellerbruehl.alumni.util.Helper;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class Register {

  @Inject private RegisterService _registerService;

  private final AccountRequest _accountRequest = new AccountRequest();

  public AccountRequest getAccountRequest() {
    return _accountRequest;
  }

  public String register() {
    _registerService.save(_accountRequest);
    return "index.xhtml";
  }

  public void checkEmail(FacesContext context, UIComponent component, Object value) {
    String address = (String) value;
    if (!address.matches("(\\w[a-zA-Z_0-9+-.]*\\w|\\w+)@(\\w(\\w|-|\\.)*\\w|\\w+)\\.[a-zA-Z]+")) {
      String msg = Helper.getMessage("errEmail");
      throw new ValidatorException(new FacesMessage(msg));
    }
  }

  public void checkPassword(FacesContext context, UIComponent component, Object value) {
    UIViewRoot root = context.getViewRoot();
    String targetId = component.getNamingContainer().getClientId() + ":password";
    Object password = ((HtmlInputSecret) root.findComponent(targetId)).getValue();
    if (password != null && !password.equals("" + value)) {
      String msg = Helper.getMessage("msgPasswordMismatch");
      throw new ValidatorException(new FacesMessage(msg));
    }
  }

}
