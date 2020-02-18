package com.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
  protected WebDriver wd;
  GroupHelper group;
  ContactHelper contact;
  SessionHelper session;
  NavigationHelper nav;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    session = new SessionHelper(wd);
    nav = new NavigationHelper(wd);
    group = new GroupHelper(wd);
    contact = new ContactHelper(wd);
    session.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }


  public GroupHelper getGroup() {
    return group;
  }

  public ContactHelper getContact() {
    return contact;
  }

  public SessionHelper getSession() {
    return session;
  }

  public NavigationHelper getNav() {
    return nav;
  }
}
