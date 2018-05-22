package de.muellerbruehl.alumniaccount.scheduled;

import de.muellerbruehl.alumniaccount.data.AccountFacade;
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

  @Inject private AccountFacade _registerService;

  @Asynchronous
  public void cleanAccountRequests() {
    try {
      _registerService.deleteOldAccountRequests();
    } catch (Exception ex) {
      Logger.getLogger(Scheduler.class.getName())
              .log(Level.SEVERE, null, ex.getMessage());
    }
  }

}
