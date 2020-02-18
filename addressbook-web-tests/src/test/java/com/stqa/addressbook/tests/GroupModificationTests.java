package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends  TestBase {
  @Test
  public void testGroupModification(){
    app.getNav().goToGroupPage();
    app.getGroup().selectFirstGroup();
    app.getGroup().initGroupModification();
    app.getGroup().fillGroupForm(new GroupData(
            "mod",
            "mod",
            "mod"));
    app.getGroup().submitGroupModification();
  }
}
