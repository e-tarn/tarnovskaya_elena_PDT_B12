package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.GroupData;
import com.stqa.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

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
    for (GroupData group : (List<GroupData>) result) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();
  return  new Groups(result);
  }

}
