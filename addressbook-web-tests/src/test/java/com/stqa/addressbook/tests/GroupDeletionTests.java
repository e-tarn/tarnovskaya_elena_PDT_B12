package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion() {
    app.getNav().goToGroupPage();
    if (!app.getGroup().isThereAGroup()) {
      app.getGroup().createGroup(new GroupData("name", "g_header", "g_footer"));
    }
    app.getGroup().selectFirstGroup();
    app.getGroup().deleteGroup();
    app.getGroup().returnToGroupPage();
  }
}
