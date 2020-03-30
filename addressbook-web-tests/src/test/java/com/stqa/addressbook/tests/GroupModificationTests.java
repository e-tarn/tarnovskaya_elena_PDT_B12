package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("name"));
    }
  }

  @Test
  public void testGroupModificationFluent() {
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("mod")
            .withHeader("mod")
            .withFooter("mod");

    app.goTo().groupPage();
    app.group().modfyById(group);
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

  @Test
  public void testGroupModification() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId())
            .withName("mod")
            .withHeader("mod")
            .withFooter("mod");

    app.group().modfy(index, group);
    List<GroupData> after = app.group().list();
    assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(group);

    assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }

  @Test
  public void testAnyGroupModification() {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("mod")
            .withHeader("mod")
            .withFooter("mod");

    app.group().modfyById(group);
    Set<GroupData> after = app.group().all();
    assertEquals(after.size(), before.size());
    before.remove(modifiedGroup);
    before.add(group);

    assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }
}
