package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFname("fName").withLname("lname"));
    }
  }

  @Test
  public void testAnyContactDeletionFromHomePageFluent()  {
    Contacts before = app.contact().allContacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().allContacts();

    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));

  }

  @Test
  public void testContactDeletionFromHomePage() {
    List<ContactData> before = app.contact().list();
    app.contact().delete(before.size() - 1);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }

  @Test(enabled = false)
  public void testContactDeletionFromContactModificationPage() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteFromModificationForm(index);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }
}


