/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.testclient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:AccountsResource
 * [/Accounts]<br>
 * USAGE:
 * <pre>
 *        AccountClient client = new AccountClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author mmueller
 */
public class AccountClient {

  private final WebTarget _webTarget;
  private final Client _client;
  private static final String BASE_URI = "http://localhost:8082/AlumniAccount/api";

  public AccountClient() {
    _client = javax.ws.rs.client.ClientBuilder.newClient();
    _webTarget = _client.target(BASE_URI).path("account");
  }

  public Response createAccount(Object requestEntity) throws ClientErrorException {
    Entity<Object> entity = Entity.entity(requestEntity, MediaType.APPLICATION_JSON);
    return _webTarget.request(MediaType.APPLICATION_JSON).post(entity, Response.class);
  }

  public Account getAccount(String id) throws ClientErrorException {
    WebTarget resource = _webTarget.path(id);
    return resource.request(MediaType.APPLICATION_JSON).get(Account.class);
  }

  public void deleteAccount(String id) throws ClientErrorException {
    WebTarget resource = _webTarget.path(id);
    resource.request(MediaType.APPLICATION_JSON).delete();
  }
  
  public boolean activateAccount(String accessKey)throws ClientErrorException {
    WebTarget resource = _webTarget.path("activate").path(accessKey);
    Entity<Object> entity = Entity.entity("activate", MediaType.APPLICATION_JSON);
    Response response = resource.request(MediaType.APPLICATION_JSON).put(entity);
    return response.getStatus() == Response.Status.OK.getStatusCode();
  }
  
  public void enableAccount(String id)throws ClientErrorException {
    WebTarget resource = _webTarget.path(id);
    Entity<Object> entity = Entity.entity("enable", MediaType.APPLICATION_JSON);
    resource.request(MediaType.APPLICATION_JSON).put(entity);
  }
  
  public void disableAccount(String id)throws ClientErrorException {
    WebTarget resource = _webTarget.path(id);
    Entity<Object> entity = Entity.entity("disable", MediaType.APPLICATION_JSON);
    resource.request(MediaType.APPLICATION_JSON).put(entity);
  }
  
  public String login(String loginName, String password) throws ClientErrorException {
    WebTarget resource = _webTarget.path("login").path(loginName).path(password);
    return resource.request(MediaType.APPLICATION_JSON).get(String.class);
  }

 public String getJson() throws ClientErrorException {
    WebTarget resource = _webTarget;
    return resource.request(MediaType.APPLICATION_JSON).get(String.class);
  }

  public void close() {
    _client.close();
  }
  
}
