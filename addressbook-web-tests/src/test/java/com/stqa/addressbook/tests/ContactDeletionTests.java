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
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFname("fName").withLname("lname"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testAnyContactDeletionFromHomePageFluent() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();

    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));

  }



  @Test(enabled = false)
  public void testContactDeletionFromContactModificationPage() {
    Contacts before = app.db().contacts();
    int index = before.size() - 1;
    app.contact().deleteFromModificationForm(index);
    Contacts after = app.db().contacts();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }
}


