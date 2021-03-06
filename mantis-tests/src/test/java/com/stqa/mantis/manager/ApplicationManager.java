package com.stqa.mantis.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private Properties properties;
  protected WebDriver wd;
  protected WebDriverWait wait;

 // protected SessionHelper session;

  private String browser;
  private RegistrationHelper registrationHelper;
  private MailHelper mailHelper;
  private SessionHelperUI sessionHelper ;
  private JamesHelper jamesHelper;
  private NavigationHelper navigationHelper;
  private UserHelper userHelper;
  private DbHelper dbHelper;
  private SoapHelper soapHelper;


  public ApplicationManager(String browser)  {
    this.browser = browser;
    properties = new Properties();

  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


//    session.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));

  }


  public void stop() {
   if(wd != null) {
     wd.quit();
   }
  }



//  public SessionHelper getSession() {
//    return session;
//  }

public HttpSession newSession(){
    return  new HttpSession(this);
}

  public String getProperty(String key) {
    return properties.getProperty(key);
  }



  public WebDriver getDriver() {
    if (wd == null){
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wait = new WebDriverWait(wd, 25);
      wd.get(properties.getProperty("web.baseUrl","http://localhost/mantisbt-1.2.19/"));
    }
    return  wd;
  }

  public RegistrationHelper registration() {
    if(registrationHelper == null){
      registrationHelper = new  RegistrationHelper(this);
    }
    return  registrationHelper;
  }

  public MailHelper mail(){
    if(mailHelper == null){
      mailHelper = new MailHelper(this); 
    }
    return  mailHelper;
  }

  public JamesHelper james(){
    if(jamesHelper == null){
      jamesHelper = new JamesHelper(this);
    }
    return  jamesHelper;

  }

  public SessionHelperUI sessionUI(){
    if(sessionHelper == null){
      sessionHelper = new SessionHelperUI(this);
    }
    return  sessionHelper;

  }

  public NavigationHelper goTo(){
    if(navigationHelper == null){
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }

  public UserHelper user(){
    if(userHelper == null){
      userHelper = new UserHelper(this);
    }
    return userHelper;
  }

  public DbHelper db() {
    if(dbHelper == null){
      dbHelper = new DbHelper();
    }
    return dbHelper;
  }

  public SoapHelper soap(){
    if(soapHelper == null){
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
}
