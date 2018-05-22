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
package de.muellerbruehl.alumni.gui.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmueller
 */
public class StringUtils {
  private static final Logger LOGGER = Logger.getLogger("StringUtils");

  public static String byte2hex(byte[] byteArray) {
    StringBuilder sb = new StringBuilder();
    for (byte b : byteArray) {
      sb.append(String.format("%02X", b));
    }
    return sb.toString();
  }

  public static byte[] hex2byte(String hex) {
    int bytes = hex.length() / 2;
    if (hex.length() - 2 * bytes != 0) {
      String msg = "Length of hex must be divisible by 2";
      LOGGER.log(Level.SEVERE, "{0}: {1}", new Object[]{msg, hex});
      throw new IllegalArgumentException(msg);
    }
    byte[] byteArray = new byte[bytes];
    for (int i = 0; i < bytes; i++) {
      byteArray[i] = (byte) ((Character.digit(hex.charAt(2 * i), 16) << 4)
              + Character.digit(hex.charAt(2 * i + 1), 16));
    }
    return byteArray;
  }
  
}
