package com.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelperUI extends HelperBase {

  public SessionHelperUI(ApplicationManager app) {
    super(app);
  }

  public void login(String userName, String password) {
    type(By.name("username"), userName);
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Login']"));

  }
}
