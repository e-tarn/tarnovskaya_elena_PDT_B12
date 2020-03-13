package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData("name", "g_header", "g_footer"));
    }
  }
  @Test
  public void testGroupModification() {


    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData(
            before.get(index).getId(),
            "mod",
            "mod",
            "mod");
    app.group().modfy(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
