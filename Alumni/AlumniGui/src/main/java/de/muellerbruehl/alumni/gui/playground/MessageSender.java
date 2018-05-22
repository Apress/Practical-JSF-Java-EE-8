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
package de.muellerbruehl.alumni.gui.playground;

import de.muellerbruehl.alumni.gui.application.EventPatcher;
import java.util.UUID;
import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author mmueller
 */
@Singleton
public class MessageSender {
  @Inject EventPatcher _eventPatcher;
  
  @Schedule(hour = "*", minute = "*", second = "*/5")
  private void messenger(){
    _eventPatcher.sendMessage(UUID.randomUUID().toString());
  }
  
}
