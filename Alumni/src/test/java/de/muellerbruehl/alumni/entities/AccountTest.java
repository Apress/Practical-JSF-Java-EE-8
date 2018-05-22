package de.muellerbruehl.alumni.entities;

import de.muellerbruehl.alumni.data.entities.Account;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mmueller
 */
public class AccountTest {

    @Test
    public void testGetDisplayName() {
        System.out.println("getDisplayName");
        Account instance = new Account();
        instance.setFirstName("John");
        instance.setLastName("Doo");
        String expResult = "Doo, John";
        String result = instance.getDisplayName();
        assertThat(result, is(expResult));
    }

    @Test
    public void testGetDisplayNameWhenFirstNameIsMissing() {
        System.out.println("testGetDisplayNameWhenFirstNameIsMissing");
        Account instance = new Account();
        instance.setLastName("Doo");
        String expResult = "Doo";
        String result = instance.getDisplayName();
        assertThat(result, is(expResult));
    }

    @Test
    public void testGetDisplayNameWhenLastNameIsMissing() {
        System.out.println("testGetDisplayNameWhenLastNameIsMissing");
        Account instance = new Account();
        instance.setFirstName("John");
        String expResult = "John";
        String result = instance.getDisplayName();
        assertThat(result, is(expResult));
    }

    @Test
    public void testGetDisplayNameWhenNamesAreMissing() {
        System.out.println("testGetDisplayNameWhenNamesAreMissing");
        Account instance = new Account();
        String expResult = "";
        String result = instance.getDisplayName();
        assertThat(result, is(expResult));
    }

//    @Test
//    public void testSetLastNameToNull() {
//        System.out.println("testSetLastNameToNull");
//        Account instance = new Account();
//        try {
//            instance.setLastName(null);
//            fail("IllegalArgumentException expected");
//        } catch (IllegalArgumentException ex) {
//            assertThat(ex.getMessage(), is("FastName must not be null"));
//        }
//    }
//
//    @Test
//    public void testSetFirstNameToNull() {
//        System.out.println("testSetLastNameToNull");
//        Account instance = new Account();
//        try {
//            instance.setFirstName(null);
//            fail("IllegalArgumentException expected");
//        } catch (IllegalArgumentException ex) {
//            assertThat(ex.getMessage(), is("FirstName must not be null"));
//        }
//    }
//
//    @Test
//    public void testSetEmailToNull() {
//        System.out.println("testSetEmailToNull");
//        try {
//            Account instance = new Account();
//            instance.setEmail(null);
//            fail("IllegalArgumentException expected");
//        } catch (IllegalArgumentException ex) {
//            assertThat(ex.getMessage(), is("email must not be null"));
//        }
//    }

}
