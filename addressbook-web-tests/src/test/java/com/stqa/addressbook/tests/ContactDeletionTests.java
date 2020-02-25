package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void preconditions(){
    if(!app.getContact().isThereAContact()){
      app.getContact().createContact(new ContactData("fName", "lname"));
    }
  }



  @Test
  public void testContactDeletionFromHomePage() {
    app.getNav().goToHomePage();
    app.getContact().selectContact();
    app.getContact().deleteContact();
  }

  @Test
  public void testContactDeletionFromContactModificationPage() {
    app.getNav().goToHomePage();
    app.getContact().initEditFirstContact();
    app.getContact().deleteContactFromModificationForm();

  }
}


