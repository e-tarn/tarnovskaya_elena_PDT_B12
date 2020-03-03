package com.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  public void goToHomePage() {
    if (isElementPresent(By.name("maintable"))) {
      return;
    }
    click(By.cssSelector("[href='./']"));
  }

  public void goToGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }
}
