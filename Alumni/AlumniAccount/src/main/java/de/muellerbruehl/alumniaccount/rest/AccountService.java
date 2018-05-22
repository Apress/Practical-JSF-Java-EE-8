package de.muellerbruehl.alumniaccount.rest;

import de.muellerbruehl.alumniaccount.data.Account;
import de.muellerbruehl.alumniaccount.data.AccountFacade;
import de.muellerbruehl.alumniaccount.data.AccountStatus;
import java.io.StringReader;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/account")
public class AccountService {

  private static final Logger LOGGER = Logger.getLogger("AccountService");

  @Context private UriInfo context;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJson() {
    return Response.noContent().build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAccount(Account account) {
    try {
      AccountFacade.getInstance().createAccount(account);
      URI path = context.getAbsolutePath().resolve(account.getId());
      return Response.created(path).entity(account.getAccessKey()).build();
    } catch (IllegalArgumentException ex) {
      return Response.status(Status.CONFLICT).build();
    } catch (Exception ex) {
      return Response.notAcceptable(null).build();
    }
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Account getAccount(@PathParam("id") String id) {
    Account account = AccountFacade.getInstance().findAccount(id);
    return account;
  }

  @PUT
  @Path("{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void putAccount(@PathParam("id") String id, String data) {
    switch (data) {
      case "enable":
        setStatus(id, AccountStatus.Active);
        return;
      case "disable":
        setStatus(id, AccountStatus.Inactive);
        return;
      default:
        updateAccount(id, data);
    }
  }

  private void setStatus(String id, AccountStatus status) {
    Account account = AccountFacade.getInstance().findAccount(id);
    account.setStatus(status);
    AccountFacade.getInstance().saveAccount(account);
  }

  private void updateAccount(String id, String data) {
    JsonObject content;
    try (JsonReader jsonReader = Json.createReader(new StringReader(data))) {
      content = jsonReader.readObject();
    }
    Account account = AccountFacade.getInstance().findAccount(id);
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") String id) {
    AccountFacade.getInstance().deleteAccount(id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("login/{loginName}/{password}")
  public String login(@PathParam("loginName") String loginName, 
          @PathParam("password") String password) {
    try {
      Account account = AccountFacade.getInstance()
              .findAccountByName(loginName);
      if (!account.checkPassword(password)) {
        return "";
      }
      return AccountFacade.getInstance().retrieveRoles(account.getId());
    } catch (IllegalArgumentException ex) {
      LOGGER.log(Level.INFO, ex.getMessage());
    }
    return "";
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("activate/{key}")
  public Response activateAccount(@PathParam("key") String accessKey) {
    try {
      AccountFacade.getInstance().enableAccount(accessKey);
      return Response.ok().build();
    } catch (Exception ex) {
      LOGGER.log(Level.INFO, ex.getMessage());
      return Response.serverError().build();
    }
  }

}
