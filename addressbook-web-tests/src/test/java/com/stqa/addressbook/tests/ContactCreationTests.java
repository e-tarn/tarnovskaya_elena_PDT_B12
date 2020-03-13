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
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();

    app.contact().initContactCreation();
    ContactData contact = new ContactData(
            "Aria",
            "Anevich",
            "MName",
            "Moscow",
            "123456",
            "7890123",
            "qw@we.com", "[none]");
    app.contact().create(contact);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
int max = 0;
for (ContactData c : after){
  if(c.getId()> max){
    max = c.getId();
  }
}
contact.setId(max);
    //contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
   //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
