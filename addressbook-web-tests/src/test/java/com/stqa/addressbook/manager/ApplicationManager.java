package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.ContactData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  protected WebDriver wd;
  protected WebDriverWait wait;
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
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wait = new WebDriverWait(wd, 25);
    wd.get("http://localhost/addressbook/");
    session = new SessionHelper(wd, wait);
    nav = new NavigationHelper(wd, wait);
    group = new GroupHelper(wd, wait);
    contact = new ContactHelper(wd, wait);
    session.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }


  public GroupHelper group() {
    return group;
  }

  public ContactHelper contact() {
    return contact;
  }

  public SessionHelper getSession() {
    return session;
  }

  public NavigationHelper goTo() {
    return nav;
  }


}
