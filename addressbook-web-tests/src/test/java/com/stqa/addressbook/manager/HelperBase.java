package com.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
  protected WebDriver wd;
  protected WebDriverWait wait;

  public HelperBase(WebDriver wd, WebDriverWait wait) {
    this.wd = wd;
    this.wait = wait;
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void clickByIndex(By locator, int i) {
    wd.findElements(locator).get(i).click();
  }

  public void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

      public void selectFromDropDown(By locator, String item){
    if(item!= null) {
      new Select(wd.findElement(locator))
              .selectByVisibleText(item);
    }





  }


  public void acceptAlert() {
    if (isAlertPresent()) {
      wd.switchTo().alert().accept();
    }
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
