/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.testclient;

import javax.ws.rs.core.Response;

/**
 *
 * @author mmueller
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    AccountClient client = new AccountClient();
    Account account = new Account();
    account.setLoginName("testmain");
    account.setFirstName("Michael");
    account.setLastName("Müller");
    account.setEmail("testmain@mueller-bruehl.de");
    account.setPassword("secret");
    Response postJson = client.createAccount(account);
    if (postJson.getStatus() == 201) {
      String path = postJson.getLocation().getPath();
      String id = path.substring(path.lastIndexOf("/") + 1);
      System.out.println(id);
      System.out.println("Account result: " + client.getAccount(id).getDisplayName());
      client.deleteAccount(id);
    }else{
      System.out.println("Failure: " + postJson.getStatus());
    }

  }

}
