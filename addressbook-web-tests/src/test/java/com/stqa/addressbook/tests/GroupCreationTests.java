package com.stqa.addressbook.tests;


import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
  @Test
  public void testGroupCreationFluent() {

    app.goTo().groupPage();
    Groups before = app.group().allGroups();
    GroupData group = new GroupData()
            .withName("name")
            .withHeader("g_header")
            .withFooter("g_footer");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().allGroups();

    assertThat(after, equalTo(before.withAdded(
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreationFluent() {

    app.goTo().groupPage();
    Groups before = app.group().allGroups();
    GroupData group = new GroupData()
            .withName("name'")
            .withHeader("g_header")
            .withFooter("g_footer");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().allGroups();

    assertThat(after, equalTo(before));
  }

  @Test
  public void testGroupCreationHashSet() throws Exception {

    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData()
            .withName("name")
            .withHeader("g_header")
            .withFooter("g_footer");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);

    assertThat(after, equalTo(before));
//MatcherAssert.assertThat(after, equalTo(before.withAdded(group)));
  }

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData()
            .withName("name")
            .withHeader("g_header")
            .withFooter("g_footer");
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
    group.withId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));

  }


}


