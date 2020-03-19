package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationFluent() {
    app.goTo().homePage();
    Contacts before = app.contact().allContacts();

    app.contact().initContactCreation();
    ContactData contact = new ContactData()
            .withFname("AriaNew")
            .withLname("AnevichNew")
            .withmName("MName")
            .withAddress("Moscow")
            .withEmail("123456")
            .withMobPhone("7890123")
            .withEmail("qw@we.com")
            .withGroup("[none]");
    app.contact().create(contact);

    Contacts after = app.contact().allContacts();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

  @Test
  public void testContactCreationHashSet() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();

    app.contact().initContactCreation();
    ContactData contact = new ContactData()
            .withFname("AriaNew")
            .withLname("AnevichNew")
            .withmName("MName")
            .withAddress("Moscow")
            .withEmail("123456")
            .withMobPhone("7890123")
            .withEmail("qw@we.com")
            .withGroup("[none]");
    app.contact().create(contact);

    Set<ContactData> after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

    before.add(contact);
    assertThat(after, equalTo(before));

  }

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();

    app.contact().initContactCreation();
    ContactData contact = new ContactData()
            .withFname("AriaNew")
            .withLname("AnevichNew")
            .withmName("MName")
            .withAddress("Moscow")
            .withEmail("123456")
            .withMobPhone("7890123")
            .withEmail("qw@we.com")
            .withGroup("[none]");
    app.contact().create(contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
//    int max = 0;
//    for (ContactData c : after) {
//      if (c.getId() > max) {
//        max = c.getId();
//      }
//    }
//    contact.withId(max);
    contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
