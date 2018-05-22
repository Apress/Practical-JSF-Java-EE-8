/** *********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ********************************************************** */
package de.muellerbruehl.tinycalculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author mmueller
 */
public class TinyCalculatorTest {

  TinyCalculator _calculator;

  @BeforeEach 
  public void init() {
    _calculator = new TinyCalculator();
    _calculator.setParam1(6);
    _calculator.setParam2(4);
  }

  @Test
  public void testAdd() {
    _calculator.add();
    assertEquals(10., _calculator.getResult());
  }

  @Test
  public void testSubtract() {
    _calculator.subtract();
    assertEquals(2., _calculator.getResult());
  }

  @Test
  public void testMultiply() {
    _calculator.multiply();
    assertEquals(24., _calculator.getResult());
  }

  @Test
  public void testDivide() {
    _calculator.divide();
    assertEquals(1.5, _calculator.getResult());
  }
}
