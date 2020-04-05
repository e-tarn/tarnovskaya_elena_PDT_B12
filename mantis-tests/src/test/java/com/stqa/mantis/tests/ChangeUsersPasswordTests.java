package com.stqa.mantis.tests;

import com.stqa.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUsersPasswordTests extends TestBase {


  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testChangeUsersPasswordByAdmin() throws IOException, MessagingException, InterruptedException {
    String userName = app.db().usersSetJdbc().iterator().next().getName();
    String email = String.format("%s@localhost.localdomain", userName);
    System.out.println(userName);

    app.sessionUI().login("administrator", "root");
    app.goTo().manageUsersPage();
    app.user().selectFromUsersTable(userName);
    app.user().clickResetPasswordButton();


    List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, "password");
    Thread.sleep(3000);
    assertTrue(app.newSession().login(userName, "password"));
  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
