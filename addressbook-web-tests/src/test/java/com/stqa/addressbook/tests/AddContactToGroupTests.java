package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import com.stqa.addressbook.model.Groups;
import com.stqa.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFname("fName").withLname("lname"));
    }
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("name"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts contacts = app.db().contacts();

    ContactData modifiedContact = contacts.iterator().next();
    int connectedToContactGroupsCount = app.db().countOfConnectedGroupsToContact(modifiedContact.getId());
    // to check, that selected contact is not connected to all groups. If Yes, we should create new group
    if (app.db().groups().size() == connectedToContactGroupsCount) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("name"));
    }
    //List of available groups, not connected to this contact
    Groups availableGroups = app.db().groupsIdOnlyJdbc();
    Groups connectedToContact = app.db().groupsConnectedToContact(modifiedContact.getId());
    availableGroups.removeAll(connectedToContact);

    // we will add this contact - id to the group with id:
    int contactToEdit = modifiedContact.getId();
    int groupToEdit = availableGroups.iterator().next().getId();
    System.out.println(contactToEdit + " " + groupToEdit);

    //add group from UI
    app.goTo().homePage();
    app.contact().selectContactById(contactToEdit);
    app.contact().addContactToTheGroup(groupToEdit);

    connectedToContact.add(new GroupData().withId(groupToEdit));

    int connectedToContactGroupsCountAfter = app.db().countOfConnectedGroupsToContact(modifiedContact.getId());

    Groups afterConnectToContact = app.db().groupsConnectedToContact(modifiedContact.getId());

    assertThat(connectedToContactGroupsCountAfter, equalTo(connectedToContactGroupsCount + 1));
    assertThat(afterConnectToContact, equalTo(connectedToContact));
  }


}
