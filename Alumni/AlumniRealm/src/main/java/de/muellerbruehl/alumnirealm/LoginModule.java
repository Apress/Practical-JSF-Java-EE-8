/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumnirealm;

import com.sun.appserv.security.AppservPasswordLoginModule;
import javax.security.auth.login.LoginException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mmueller
 */
public class LoginModule extends AppservPasswordLoginModule {

  @Override
  protected void authenticateUser() throws LoginException {
    if (!(_currentRealm instanceof AlumniRealm)) {
      throw new LoginException("Unexpected realm: "
              + _currentRealm.getClass().getSimpleName());
    }

    String[] groups = obtainPermittedGroups(_username, _passwd);
    if (groups.length > 0 && !groups[0].isEmpty()) {
      commitUserAuthentication(groups);
    }
  }

  private static final String BASE_URI = "http://localhost:8082/AlumniAccount/api";

  String[] obtainPermittedGroups(String userName, char[] passwd) {
    Client client = ClientBuilder.newClient();
    WebTarget resource = client.target(BASE_URI)
            .path("account")
            .path("login")
            .path(userName)
            .path(new String(passwd));
    String groups = resource.request(MediaType.APPLICATION_JSON).get(String.class);
    client.close();
    return groups.split(";");
  }

}
