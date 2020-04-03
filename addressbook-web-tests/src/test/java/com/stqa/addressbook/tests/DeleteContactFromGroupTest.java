package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase {
  @BeforeMethod
  public void preconditions() {
    //is contact present?
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFname("fName").withLname("lname"));
    }
    //is group present?
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("name"));
    }
    //is contact connected to group present?
    Contacts contactsAddedToAnyGroup = app.db().contactsWithGroups();
    Groups listOfGroupsConnectedToContact = app.db().groupsIdOnlyJdbc();
    if (app.db().countOfConnectedGroupsToContactDB() == 0) {
      app.goTo().homePage();
      app.contact().addContactToTheGroup(contactsAddedToAnyGroup.iterator().next().getId(), listOfGroupsConnectedToContact.iterator().next().getId());
    }

  }

  @Test
  public void testDeleteContactFromGroup() {
    ContactData modifiedContact = app.db().contactsWithGroups().iterator().next();
    Groups groupsConnectedToContact = app.db().groupsConnectedToContact(modifiedContact.getId());

    int contactToEdit = modifiedContact.getId();
    int groupToEdit = groupsConnectedToContact.iterator().next().getId();

    int connectedToContactGroupsCount = app.db().countOfConnectedGroupsToContact(modifiedContact.getId());

    // we will delete this contact - id from the group with id:
    System.out.println(contactToEdit + " " + groupToEdit);

    //delete Contact from group from UI
    app.goTo().homePage();
    app.contact().filterContactsByGroup(groupToEdit);
    app.contact().selectContactById(contactToEdit);

    app.contact().deleteContactFromTheGroup();

    groupsConnectedToContact.remove(groupToEdit);

    int connectedToContactGroupsCountAfter = app.db().countOfConnectedGroupsToContact(modifiedContact.getId());

    Groups afterDeleteFromContact = app.db().groupsConnectedToContact(modifiedContact.getId());

    assertThat(connectedToContactGroupsCountAfter, equalTo(connectedToContactGroupsCount - 1));
    assertThat(afterDeleteFromContact, equalTo(groupsConnectedToContact.without(new GroupData().withId(groupToEdit))));
  }
}
