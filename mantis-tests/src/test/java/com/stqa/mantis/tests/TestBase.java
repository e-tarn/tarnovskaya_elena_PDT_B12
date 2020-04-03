package com.stqa.mantis.tests;


import com.stqa.mantis.manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

  protected static ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }

  @BeforeMethod
  public void startLogger(Method m, Object[] p) {
    logger.info("Start test " + m.getName()
            + " with parameters: " + Arrays.asList(p));
  }

  @AfterMethod
  public void stopLogger(Method m) {
    logger.info("Stop test " + m.getName());
  }


}
