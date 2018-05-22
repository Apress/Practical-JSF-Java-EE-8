/** *********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 ********************************************************** */
package de.muellerbruehl.selenium;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author mmueller
 */
public class TinyCalculatorSeleniumTest {

  private static WebDriver _driver;

  @BeforeAll
  public static void setUpClass() {
    System.setProperty("webdriver.gecko.driver", "/home/mmueller/.local/geckodriver");
    _driver = new FirefoxDriver();
  }

  @AfterAll
  public static void tearDownClass() {
    _driver.quit();
  }

  @BeforeEach
  public void setUp() {
    _driver.get("http://localhost:8080/TinyCalculator/index.xhtml");
    setValue("form:param1", "6");
    setValue("form:param2", "4");
  }

  private void setValue(String id, String value) {
    WebElement element = _driver.findElement(By.id(id));
    element.clear();
    element.sendKeys(value);
  }

  @Test
  public void testAdd() {
    _driver.findElement(By.id("form:add")).click();
    String text = _driver.findElement(By.id("form:result")).getText();
    assertEquals("10.0", text);
  }

  @Test
  public void testSubstract() {
    _driver.findElement(By.id("form:sub")).click();
    String text = _driver.findElement(By.id("form:result")).getText();
    assertEquals("2.0", text);
  }

  @Test
  public void testMultiply() {
    _driver.findElement(By.id("form:mul")).click();
    String text = _driver.findElement(By.id("form:result")).getText();
    assertEquals("24.0", text);
  }

  @Test
  public void testDivide() {
    _driver.findElement(By.id("form:div")).click();
    String text = _driver.findElement(By.id("form:result")).getText();
    assertEquals("1.5", text);
  }
}
