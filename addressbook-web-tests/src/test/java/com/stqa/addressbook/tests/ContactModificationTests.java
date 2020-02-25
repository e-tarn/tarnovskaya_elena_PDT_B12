package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    if (!app.getContact().isThereAContact()) {
      app.getContact().createContact(new ContactData("fName", "lname"));
    }
  }

  @Test
  public void testContactModification() {
    app.getNav().goToHomePage();
    app.getContact().initEditFirstContact();
    app.getContact().fillContactCreationForm(new ContactData(
            "modName",
            "L",
            "M",
            "Odessa",
            "76543",
            "45653334444",
            "jjjj@kkkk.com", null), false);
    app.getContact().confirmUpdateContact();
  }
}
