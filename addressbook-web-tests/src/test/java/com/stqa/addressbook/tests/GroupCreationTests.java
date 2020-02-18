package com.stqa.addressbook.tests;


import com.stqa.addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends  TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    app.getNav().goToGroupPage();
    app.getGroup().initGroupCreation();
    app.getGroup().fillGroupCreationForm(new GroupData("name", "g_header", "g_footer"));
    app.getGroup().submitGroupCreation();
    app.getGroup().returnToGroupPage();
  }
}


