package com.stqa.mantis.manager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsersPage() {
    click(By.cssSelector("[href='/mantisbt-1.2.19/manage_overview_page.php']"));
    click(By.cssSelector("[href='/mantisbt-1.2.19/manage_user_page.php']"));
  }
}

