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

import de.muellerbruehl.alumni.business.dto.Account;
import de.muellerbruehl.alumni.business.dto.AccountClient;
import de.muellerbruehl.alumni.data.mail.MailTemplate;
import de.muellerbruehl.alumni.data.mail.TemplateName;
import de.muellerbruehl.alumni.gui.enums.Page;
import de.muellerbruehl.alumni.gui.mail.Mailer;
import de.muellerbruehl.alumni.gui.util.Helper;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

/**
 *
 * @author mmueller
 */
@Named
@ViewScoped
public class Register implements Serializable {

  private Account _account = new Account();

  public Account getAccount() {
    return _account;
  }

  public void setAccount(Account account) {
    this._account = account;
  }

  String _password;

  public String getPassword() {
    return _password;
  }

  public void setPassword(String password) {
    _password = password;
  }

  public void checkPassword(FacesContext context,
          UIComponent component,
          Object value) {
    String sValue = "" + value;
    if (!sValue.equals(_password)) {
      String msg = Helper.getMessage("msgPasswordMismatch");
      throw new ValidatorException(new FacesMessage(msg));
    }
  }

  public void dummy(FacesContext context,
          UIComponent component,
          Object value) {
  }

  public void checkEmail(FacesContext context, UIComponent component, Object value) {
    String address = (String) value;
    if (!address.matches("(\\w[a-zA-Z_0-9+-.]*\\w|\\w+)@(\\w(\\w|-|\\.)*\\w|\\w+)\\.[a-zA-Z]+")) {
      String msg = Helper.getMessage("errEmail");
      throw new ValidatorException(new FacesMessage(msg));
    }
  }

  public String register() {
    String outcome = "";

    if (notValidPassword()) {
      return outcome;
    }

    AccountClient client = new AccountClient();
    Response postJson = client.createAccount(_account);
    switch (postJson.getStatus()) {
      case 201:
        String accessKey = postJson.readEntity(String.class);
        sendMail(accessKey);
        outcome = Page.Login.urlRedirect();
        break;
      case 409:
        handleExistingEmail();
        break;
      default:
    }
    client.close();
    return outcome;
  }

  private boolean notValidPassword() {
    return _password == null || !_password.equals(_account.getPassword());
  }

  @Inject private Mailer _mailer;

  private void sendMail(String accessKey) {
    MailTemplate template = _mailer.findTemplateByName(TemplateName.ActivationMail);
    String subject = template.getSubject();
    String body = template
            .getBody()
            .replace("{firstName}", _account.getFirstName())
            .replace("{link}", getUrl(accessKey));
    _mailer.sendMail(_account.getEmail(), subject, body);
  }

  private String getUrl(String key) {
    HttpServletRequest request = obtainServletRequest();
    try {
      URL url = new URL(request.getScheme(),
              request.getServerName(),
              request.getServerPort(),
              request.getContextPath() + Page.Activate.url() + "?key=" + key);
      return url.toString();
    } catch (MalformedURLException ex) {
      Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
      return "";
    }
  }

  private HttpServletRequest obtainServletRequest() {
    FacesContext context = FacesContext.getCurrentInstance();
    return (HttpServletRequest) context.getExternalContext().getRequest();
  }

  private void handleExistingEmail() {
    MailTemplate template = _mailer.findTemplateByName(TemplateName.ExistingEmail);
    String subject = template.getSubject();
    String body = template.getBody();
    _mailer.sendMail(_account.getEmail(), subject, body);
  }

}
