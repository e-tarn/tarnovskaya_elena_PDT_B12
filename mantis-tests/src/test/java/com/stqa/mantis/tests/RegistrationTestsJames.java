package com.stqa.mantis.tests;

import com.stqa.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTestsJames extends TestBase {

  @Test
  public void testRegistration() throws IOException, MessagingException {
   long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost.localdomain", now);

    String user = String.format("user%s", now);
    String password = "password";
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);

    app.registration().finish(confirmationLink, "password");
    assertTrue(app.newSession().login(user, password));


  }




}
