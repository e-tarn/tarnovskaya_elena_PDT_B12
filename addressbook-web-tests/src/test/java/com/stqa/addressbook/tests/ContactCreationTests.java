package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNav().goToHomePage();
    List<ContactData> before = app.getContact().getContactsList();

    app.getContact().initContactCreation();
    ContactData contact = new ContactData(
            "Flora",
            "Libovich",
            "MName",
            "Moscow",
            "123456",
            "7890123",
            "qw@we.com", "[none]");
    app.getContact().fillContactCreationForm(contact, true);
    app.getContact().submitContactCreation();
    app.getContact().returnToHomePage();
    List<ContactData> after = app.getContact().getContactsList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
