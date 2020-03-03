package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    app.getNav().goToHomePage();
    if (!app.getContact().isThereAContact()) {
      app.getContact().createContact(new ContactData("fName", "lname"));
    }
  }

  @Test
  public void testContactDeletionFromHomePage() {
    List<ContactData> before = app.getContact().getContactsList();
    app.getContact().selectContact(before.size() - 1);
    app.getContact().deleteContact();
    List<ContactData> after = app.getContact().getContactsList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }

  @Test
  public void testContactDeletionFromContactModificationPage() {
    // app.getNav().goToHomePage();
    List<ContactData> before = app.getContact().getContactsList();
    app.getContact().initEditContact(before.size() - 1);
    app.getContact().deleteContactFromModificationForm();
    List<ContactData> after = app.getContact().getContactsList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }
}


