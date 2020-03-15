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
      app.group().create(new GroupData().withName("name"));
    }
  }
  @Test
  public void testGroupModification() {


    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId())
            .withName("mod")
            .withHeader("mod")
            .withFooter("mod");

    app.group().modfy(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }
}
