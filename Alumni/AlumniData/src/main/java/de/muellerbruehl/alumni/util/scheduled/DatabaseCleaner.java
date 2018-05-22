/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.util.scheduled;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mmueller
 */
@Stateless
public class DatabaseCleaner {

  //@Inject private AccountService _registerService;

  @Asynchronous
  public void cleanAccountRequests() {
    try {
    //  _registerService.deleteOldAccountRequests();
    } catch (Exception ex) {
      Logger.getLogger(Scheduler.class.getName())
              .log(Level.SEVERE, null, ex.getMessage());
    }
  }

}
