package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.ContactData;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private Properties properties;
  protected WebDriver wd;
  protected WebDriverWait wait;
  GroupHelper group;
  ContactHelper contact;
  SessionHelper session;
  NavigationHelper nav;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser)  {
    this.browser = browser;
    properties = new Properties();

  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
if("".equals(properties.getProperty("selenium.server"))){
  if (browser.equals(BrowserType.FIREFOX)) {
    wd = new FirefoxDriver();
  } else if (browser.equals(BrowserType.CHROME)) {
    wd = new ChromeDriver();
  } else if (browser.equals(BrowserType.IE)) {
    wd = new InternetExplorerDriver();
  }
} else{
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setBrowserName(browser);
  wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
}
    dbHelper = new DbHelper();

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wait = new WebDriverWait(wd, 25);
    wd.get(properties.getProperty("web.baseUrl","http://localhost/addressbook/"));
    session = new SessionHelper(wd, wait);
    nav = new NavigationHelper(wd, wait);
    group = new GroupHelper(wd, wait);
    contact = new ContactHelper(wd, wait);
    session.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

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

  public DbHelper db() {
    return dbHelper;
  }

}
