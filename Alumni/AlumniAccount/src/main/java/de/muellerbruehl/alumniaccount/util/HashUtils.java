/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author mmueller
 */
public class HashUtils {

  private static final Logger LOGGER = Logger.getLogger("HashUtils");

  public static byte[] getShaHash(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA");
      byte[] digest = md.digest(input.getBytes("utf-8"));
      return digest;
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
      return new byte[0];
    }
  }

  public static byte[] hashPassword(String password, 
          byte[] salt, 
          int iterations, 
          int keyLength) {
    try {
      SecretKeyFactory skf = SecretKeyFactory
              .getInstance("PBKDF2WithHmacSHA512");
      PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), 
              salt, 
              iterations, 
              keyLength);
      SecretKey key = skf.generateSecret(spec);
      return key.getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
      throw new RuntimeException(ex);
    }
  }

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

