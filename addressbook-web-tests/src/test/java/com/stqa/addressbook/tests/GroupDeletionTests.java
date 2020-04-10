package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("name"));
    }
  }

  @Test
  public void testAnyGroupDeletionFluent() {

    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.db().groups();

    assertThat(after.size(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedGroup)));


  }

  @Test
  public void testAnyGroupDeletion() {

    Set<GroupData> before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.db().groups();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedGroup);
    Assert.assertEquals(after, before);
  }

  @Test
  public void testGroupDeletionByIndex() {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);


  }


}
