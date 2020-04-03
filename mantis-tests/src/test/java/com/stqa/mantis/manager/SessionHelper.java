package com.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  public void login(String userName, String password) {
    type(By.name("user"), userName);
    type(By.name("pass"), password);
    click(By.xpath("//input[@value='Login']"));

  }
}
