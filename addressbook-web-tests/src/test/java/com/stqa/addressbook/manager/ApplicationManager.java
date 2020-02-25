package com.stqa.addressbook.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager{
  protected WebDriver wd;
  GroupHelper group;
  ContactHelper contact;
  SessionHelper session;
  NavigationHelper nav;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }


  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();

      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      session = new SessionHelper(wd);
      nav = new NavigationHelper(wd);
      group = new GroupHelper(wd);
      contact = new ContactHelper(wd);
      session.login("admin", "secret");
    }
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
