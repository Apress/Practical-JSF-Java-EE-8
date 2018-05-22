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
package de.muellerbruehl.alumni.gui.mail;

import javax.mail.Message.RecipientType;

/**
 *
 * @author mmueller
 */
public class Recipient {

  private final String _email;
  private final RecipientType _type;

  public Recipient(String email, RecipientType type) {
    _email = email.trim();
    _type = type;
  }

  public String getEmail() {
    return _email;
  }

  public RecipientType getType() {
    return _type;
  }

}
