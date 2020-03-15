package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    app.goTo().homePage();

    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData()
              .withFname("fName")
              .withLname("lname"));
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
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
