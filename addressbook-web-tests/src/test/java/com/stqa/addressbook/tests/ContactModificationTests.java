package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    app.getNav().goToHomePage();

    if (!app.getContact().isThereAContact()) {
      app.getContact().createContact(new ContactData("fName", "lname"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.getContact().getContactsList();
    app.getContact().initEditContact(before.size() - 1);
    ContactData contact = new ContactData(
            before.get(before.size() - 1).getId(),
            "modName",
            "L",
            "M",
            "Odessa",
            "76543",
            "45653334444",
            "jjjj@kkkk.com", null);
    app.getContact().fillContactCreationForm(contact, false);
    app.getContact().confirmUpdateContact();
    app.getContact().returnToHomePage();
    List<ContactData> after = app.getContact().getContactsList();


    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(contact);

    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }
}
