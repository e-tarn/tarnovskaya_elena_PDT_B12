package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() {
    app.getNav().goToGroupPage();
    if (!app.getGroup().isThereAGroup()) {
      app.getGroup().createGroup(new GroupData("name", "g_header", "g_footer"));
    }
    List<GroupData> before = app.getGroup().getGroupList();
    app.getGroup().selectGroup(before.size() - 1);
    app.getGroup().deleteGroup();
    app.getGroup().returnToGroupPage();
    List<GroupData> after = app.getGroup().getGroupList();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);


  }
}
