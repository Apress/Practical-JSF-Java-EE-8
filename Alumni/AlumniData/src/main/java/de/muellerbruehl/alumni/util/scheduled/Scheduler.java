/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumni.util.scheduled;

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
  
  //@Schedule(hour = "*/12")
  private void cleanDatabase() {
      //_dbCleaner.cleanAccountRequests();
  }
  
  //@Schedule(hour = "*", minute = "*", second = "*/10")
  private void someAction() {
      //_dbCleaner.cleanAccountRequests();
  }
}
