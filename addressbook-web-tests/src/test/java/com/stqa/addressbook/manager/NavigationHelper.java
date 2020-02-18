package com.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToHomePage() {
    click(By.cssSelector("[href='./']"));
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }
}
