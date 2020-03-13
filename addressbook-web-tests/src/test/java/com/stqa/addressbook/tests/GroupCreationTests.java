package com.stqa.addressbook.tests;


import com.stqa.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData("name", "g_header", "g_footer");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    //old compare
//int max = 0;
//for (GroupData g : after){
//  if(g.getId()> max){
//    max = g.getId();
//  }
//}
    //group.setId(max);
    //new compare (stream)
//    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    };
//    int max1 = after.stream().max(byId).get().getId();
//    group.setId(max1);

    //lambda
    // int max1 = after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }



}


