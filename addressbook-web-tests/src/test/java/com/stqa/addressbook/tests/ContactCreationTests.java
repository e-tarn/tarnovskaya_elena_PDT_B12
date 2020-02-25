package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends  TestBase {
  @Test
  public void testContactCreation(){
    app.getNav().goToHomePage();
    app.getContact().initContactCreation();
    app.getContact().fillContactCreationForm(new ContactData(
            "fName",
            "Lname",
            "MName",
            "Moscow",
            "123456",
            "7890123",
            "qw@we.com", "mod"), true);
    app.getContact().submitContactCreation();
    app.getContact().returnToHomePage();
  }

}
