package com.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {


  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String userName, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), userName);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));


  }

  public void startUserCreationFromUI(){
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);
    String user = String.format("user%s", now);
    String password = "password";
    app.registration().start(user, email);
  }
}
