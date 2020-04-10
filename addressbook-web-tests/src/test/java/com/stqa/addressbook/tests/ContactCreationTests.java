package com.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
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
    Contacts before = app.db().contacts();;

    app.contact().initContactCreation();
    app.contact().create(contact);

    Contacts after = app.db().contacts();;

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
    Set<ContactData> before = app.db().contacts();

    app.contact().initContactCreation();

    app.contact().create(newContact);

    Set<ContactData> after = app.db().contacts();;
    assertThat(after.size(), equalTo(before.size() + 1));
    newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

    before.add(newContact);
    assertThat(after, equalTo(before));
  }

}
