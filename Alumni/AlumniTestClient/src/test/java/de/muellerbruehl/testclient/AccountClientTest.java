/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.testclient;

import javax.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mmueller
 */
public class AccountClientTest {

  public AccountClientTest() {
  }

  private Account createAccount() {
    Account account = new Account();
    account.setLoginName("testclient");
    account.setFirstName("Michael");
    account.setLastName("Müller");
    account.setEmail("testclient@mueller-bruehl.de");
    account.setPassword("secret");
    return account;
  }

  @Test
  public void createAndDeleteAccount() {
    AccountClient client = new AccountClient();
    Account account = createAccount();
    Response postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());

    String path = postJson.getLocation().getPath();
    String id = path.substring(path.lastIndexOf("/") + 1);
    assertEquals("testclient", client.getAccount(id).getLoginName());
    client.deleteAccount(id);
    assertEquals(null, client.getAccount(id));
  }

  @Test
  public void retrieveAccessKey() {
    AccountClient client = new AccountClient();
    Account account = createAccount();
    Response postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());

    String key = postJson.readEntity(String.class);
    assertEquals(32, key.length());

    String path = postJson.getLocation().getPath();
    String id = path.substring(path.lastIndexOf("/") + 1);
    assertNotEquals(id, key);
        
    assertEquals("testclient", client.getAccount(id).getLoginName());
    client.deleteAccount(id);
    assertEquals(null, client.getAccount(id));
  }

  @Test
  public void rejectSecondAccountWithSameEmail() {
    AccountClient client = new AccountClient();
    Account account = createAccount();
    Response postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());
    String path = postJson.getLocation().getPath();
    String id = path.substring(path.lastIndexOf("/") + 1);

    account = createAccount();
    postJson = client.createAccount(account);
    assertEquals(409, postJson.getStatus());

    client.deleteAccount(id);
    assertEquals(null, client.getAccount(id));
  }

  @Test
  public void changeSameLoginName() {
    AccountClient client = new AccountClient();
    Account account = createAccount();
    Response postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());
    String path = postJson.getLocation().getPath();
    String id = path.substring(path.lastIndexOf("/") + 1);
    assertEquals("testclient", client.getAccount(id).getLoginName());

    account = createAccount();
    account.setEmail("other@mail.com");
    postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());
    path = postJson.getLocation().getPath();
    String id2 = path.substring(path.lastIndexOf("/") + 1);
    assertNotEquals("testclient", client.getAccount(id2).getLoginName());
    assertTrue(client.getAccount(id2).getLoginName().startsWith("testclient"));
    
    client.deleteAccount(id);
    assertEquals(null, client.getAccount(id));
    client.deleteAccount(id2);
    assertEquals(null, client.getAccount(id2));
  }

  @Test
  public void enableAccountAndLogin() {
    AccountClient client = new AccountClient();
    Account account = createAccount();
    Response postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());

    String path = postJson.getLocation().getPath();
    String id = path.substring(path.lastIndexOf("/") + 1);
    String roles = client.login(account.getLoginName(), account.getPassword());
    // login for an account in "new" state must return an empty member list
    assertEquals("", roles);

    client.enableAccount(id);

    roles = client.login(account.getLoginName(), account.getPassword());
    // login for an account in "active" state must return a valid member list
    assertEquals("member", roles);
    
    client.deleteAccount(id);
  }

  @Test
  public void activateAccountAndLogin() {
    AccountClient client = new AccountClient();
    Account account = createAccount();
    Response postJson = client.createAccount(account);
    assertEquals(201, postJson.getStatus());

    String path = postJson.getLocation().getPath();
    String id = path.substring(path.lastIndexOf("/") + 1);
    String roles = client.login(account.getLoginName(), account.getPassword());
    // login for an account in "new" state must return an empty member list
    assertEquals("", roles);

    String key = postJson.readEntity(String.class);
    client.activateAccount(key);

    roles = client.login(account.getLoginName(), account.getPassword());
    // login for an account in "active" state must return a valid member list
    assertEquals("member", roles);
    
    client.deleteAccount(id);
  }

  
}
