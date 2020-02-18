package com.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
  @Test
  public void testGroupDeletion(){
    app.getNav().goToGroupPage();
    app.getGroup().selectFirstGroup();
    app.getGroup().deleteGroup();
    app.getGroup().returnToGroupPage();
  }
}
