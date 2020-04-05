package com.stqa.mantis.manager;

import com.stqa.mantis.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHelper extends HelperBase{

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public Set<User> all() {
    return  null;
  }

  public void selectFromUsersTable(String userName) {
    click(By.xpath("//a[contains(., '"+ userName+"')]"));
  }

  public void clickResetPasswordButton() {
    click(By.cssSelector("[action = 'manage_user_reset.php'] .button"));
  }
}
