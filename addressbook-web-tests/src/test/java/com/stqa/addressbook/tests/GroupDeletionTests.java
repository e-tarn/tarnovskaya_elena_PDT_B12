package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData("name", "g_header", "g_footer"));
    }
  }
  @Test
  public void testGroupDeletion() {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);


  }


}
