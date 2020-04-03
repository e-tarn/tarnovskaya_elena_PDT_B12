package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.testng.annotations.Test;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DbHelper {
  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from GroupData").list();

    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();

    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public Contacts contactsWithGroups() {
    try {
      Connection conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select id from address_in_groups");
      Contacts contacts = new Contacts();
      while (rs.next()) {
        contacts().add(new ContactData().withId(rs.getInt("id")));

      }
      rs.close();
      stmt.close();
      conn.close();
      System.out.println(contacts());
      return contacts();


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return null;
  }

  public int countOfConnectedGroupsToContact(int contactID) {
    try {
      Connection conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT count(*) AS count from address_in_groups where id = " + contactID);

      int num = 0;
      while (rs.next()) {
        num = (rs.getInt(1));
        System.out.println("Count is " + num);
        return num;
      }

      rs.close();
      stmt.close();
      conn.close();


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return 0;
  }

  public int countOfConnectedGroupsToContactDB() {
    try {
      Connection conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT count(*) AS count from address_in_groups");

      int num = 0;
      while (rs.next()) {
        num = (rs.getInt(1));
        System.out.println("Count is " + num);
        return num;
      }

      rs.close();
      stmt.close();
      conn.close();


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return 0;
  }

  // @Test
//  public void hbGroupsFromContactData(){
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    List result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
//    for ( ContactData contact : (List<ContactData>) result ) {
//      // System.out.println(contact);
//     // System.out.println(contact.getGroups());
//      Groups groups = contact.getGroups();
//      System.out.println(groups);
//    }
//
//    System.out.println(result.size());
//    session.getTransaction().commit();
//    session.close();
//    //return groups();
//  }

  public Groups groupsConnectedToContact(int contactId) {
    try {
      Connection conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select group_id from address_in_groups where id = "+ contactId);
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")));

      }
      rs.close();
      stmt.close();
      conn.close();
      System.out.println(groups);
      return groups;


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
return null;
  }

  public Groups groupsIdOnlyJdbc() {
    try {
      Connection conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                      "user=root&password=&serverTimezone=UTC");
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select group_id from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")));

      }
      rs.close();
      stmt.close();
      conn.close();

      return groups;


    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return null;
  }
}
