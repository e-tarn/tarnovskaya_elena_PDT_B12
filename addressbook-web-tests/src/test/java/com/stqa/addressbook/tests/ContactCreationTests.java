package com.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsJson() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
    }.getType());

    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();

  }

  @Test(dataProvider = "validContactsJson")
  public void testContactCreationFluent(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().allContacts();

    app.contact().initContactCreation();
    app.contact().create(contact);

    Contacts after = app.contact().allContacts();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

  @Test
  public void testContactCreationHashSet() {
    Groups dbGroups = app.db().groups();
    ContactData newContact = new ContactData()
            .withFname("AriaNew")
            .withLname("AnevichNew")
            .withmName("MName")
            .withAddress("Moscow")
            .withEmail("123456")
            .withMobPhone("7890123")
            .withEmail("qw@we.com")
    .inGroup(dbGroups.iterator().next());
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();

    app.contact().initContactCreation();

    app.contact().create(newContact);

    Set<ContactData> after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

    before.add(newContact);
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
            .withEmail("qw@we.com");
            //.withGroup("[none]");
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
