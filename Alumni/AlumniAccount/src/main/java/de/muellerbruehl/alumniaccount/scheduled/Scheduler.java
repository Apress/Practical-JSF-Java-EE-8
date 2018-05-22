/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.scheduled;

import de.muellerbruehl.alumniaccount.data.AccountFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author mmueller
 */
@Singleton
public class Scheduler {

  @Inject DatabaseCleaner _dbCleaner;

  @Schedule(hour = "*/1")
  private void cleanDatabase() {
    _dbCleaner.cleanAccountRequests();
  }


}
