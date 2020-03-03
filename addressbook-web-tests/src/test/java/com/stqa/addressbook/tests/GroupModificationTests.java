package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {
    app.getNav().goToGroupPage();
    if (!app.getGroup().isThereAGroup()) {
      app.getGroup().createGroup(new GroupData("name", "g_header", "g_footer"));
    }
    List<GroupData> before = app.getGroup().getGroupList();

    app.getGroup().selectGroup(before.size() - 1);
    app.getGroup().initGroupModification();
    GroupData group = new GroupData(
            before.get(before.size() - 1).getId(),
            "mod",
            "mod",
            "mod");
    app.getGroup().fillGroupForm(group);
    app.getGroup().submitGroupModification();
    app.getGroup().returnToGroupPage();
    List<GroupData> after = app.getGroup().getGroupList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
