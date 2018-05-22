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
package de.muellerbruehl.alumni.gui.login;

import de.muellerbruehl.alumni.business.dto.AccountClient;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author mmueller
 */
@Named
@RequestScoped
public class Activate {
  
  private boolean _success;

  public boolean isSuccess() {
    return _success;
  }

  public void setSuccess(boolean success) {
    this._success = success;
  }
  
    @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        String key = "" + params.get("key");
        AccountClient client = new AccountClient();
        client.activateAccount(key);
        _success = true;
    }  
}
