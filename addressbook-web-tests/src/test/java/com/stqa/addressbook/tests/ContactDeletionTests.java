package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void preconditions() {
    app.goTo().homePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData().withFname("fName").withLname("lname"));
    }
  }

  @Test
  public void testContactDeletionFromHomePage() throws InterruptedException {
    List<ContactData> before = app.contact().list();
    app.contact().select(before.size() - 1);
    app.contact().delete();
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }

  @Test(enabled = false)
  public void testContactDeletionFromContactModificationPage() throws InterruptedException {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteFromModificationForm(index);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(after, before);
  }
}


