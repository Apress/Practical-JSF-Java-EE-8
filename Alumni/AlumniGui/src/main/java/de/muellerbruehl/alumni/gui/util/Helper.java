/*
 * **************************************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 * 
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * For any other usage, please contact the author.
 * michael.mueller@mueller-bruehl.de
 * **************************************************************************
 */
package de.muellerbruehl.alumni.gui.util;

import java.util.ResourceBundle;

/**
 *
 * @author mmueller
 */
public class Helper {
    public static String getMessage(String key) {
        ResourceBundle messageBundle = ResourceBundle.getBundle("de.muellerbruehl.alumni.messages");
        return messageBundle.getString(key);
    }
  
}
