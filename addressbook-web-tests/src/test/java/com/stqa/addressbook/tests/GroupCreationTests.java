package com.stqa.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line!=null){
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});

      line = reader.readLine();
    }
  return list.iterator();
    }

  @DataProvider
  public Iterator<Object[]> validGroupsXml() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line!=null){
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);

    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }

  @DataProvider
  public Iterator<Object[]> validGroupsJson() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line!=null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());

        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }


  @Test(dataProvider = "validGroupsJson")
  public void testGroupCreationFluent(GroupData group) {

    app.goTo().groupPage();
    Groups before = app.group().allGroups();
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


