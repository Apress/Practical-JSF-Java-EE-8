/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muellerbruehl.alumniaccount.util;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DynamicTest;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

/**
 *
 * @author mmueller
 */
public class HashUtilsTest {

  @Test
  public void testByte2hex00() {
    byte[] byteArray = new byte[1];
    byteArray[0] = 0;
    assertEquals("00", HashUtils.byte2hex(byteArray));
  }

  @Test
  public void testByte2hex01() {
    byte[] byteArray = new byte[1];
    byteArray[0] = 1;
    assertEquals("01", HashUtils.byte2hex(byteArray));
  }

  @Test
  public void testByte2hex0A() {
    byte[] byteArray = new byte[1];
    byteArray[0] = 10;
    assertEquals("0A", HashUtils.byte2hex(byteArray));
  }

  @Test
  public void testByte2hex7F() {
    byte[] byteArray = new byte[1];
    byteArray[0] = 127;
    assertEquals("7F", HashUtils.byte2hex(byteArray));
  }

  @Test
  public void testByte2hexFF() {
    byte[] byteArray = new byte[1];
    byteArray[0] = -1;
    assertEquals("FF", HashUtils.byte2hex(byteArray));
  }

  @Test
  public void testByte2hexFF00() {
    byte[] byteArray = new byte[2];
    byteArray[0] = -1;
    byteArray[1] = 0;
    assertEquals("FF00", HashUtils.byte2hex(byteArray));
  }

  @Test
  public void testHex2byteSingle() {
    String hex = "01AF0F1E";
    assertEquals(hex, HashUtils.byte2hex(HashUtils.hex2byte(hex)));
  }

  @TestFactory
  Stream<DynamicTest> testHex2byte() {
    return Stream.of("01AF0F1E", "00", "FF")
            .map(h -> dynamicTest(h, () -> assertEquals(h, HashUtils.byte2hex(HashUtils.hex2byte(h)))));
  }

}
