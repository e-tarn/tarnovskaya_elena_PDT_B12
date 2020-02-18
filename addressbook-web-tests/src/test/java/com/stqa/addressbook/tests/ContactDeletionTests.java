package com.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
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


