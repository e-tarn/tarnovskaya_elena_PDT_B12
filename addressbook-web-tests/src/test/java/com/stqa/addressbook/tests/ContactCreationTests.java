package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ConcactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends  TestBase {
  @Test
  public void testContactCreation(){
    app.getNav().goToHomePage();
    app.getContact().initContactCreation();
    app.getContact().fillContactCreationForm(new ConcactData(
            "Olga",
            "Ivanova",
            "Moscow",
            "123456",
            "7890123",
            "qw@we.com"));
    app.getContact().submitContactCreation();
    app.getContact().returnToHomePage();
  }

}
