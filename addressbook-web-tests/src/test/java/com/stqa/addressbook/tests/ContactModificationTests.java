package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFname("fName")
              .withLname("lname"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactModificationFluent() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFname("modName")
            .withLname("L")
            .withmName("M")
            .withAddress("Odessa")
            .withHomePhone("76543")
            .withMobPhone("45653334444")
            .withEmail("jjjj@kkkk.com");
    app.contact().modifyById(contact);
    Contacts after = app.db().contacts();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }


  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId())
            .withFname("modName")
            .withLname("L")
            .withmName("M")
            .withAddress("Odessa")
            .withHomePhone("76543")
            .withMobPhone("45653334444").withEmail("jjjj@kkkk.com");
    app.contact().modify(contact, index);
    List<ContactData> after = app.contact().list();


    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    //Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    // contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }


}
