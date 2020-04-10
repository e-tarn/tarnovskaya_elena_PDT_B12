package com.stqa.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import com.stqa.mantis.manager.ApplicationManager;
import com.stqa.mantis.model.Issue;
import com.stqa.mantis.model.Project;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

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


  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    IssueData issueData = app.soap().getIssue(issueId);
    return !issueData.getStatus().getName().equals("closed");
  }


  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      System.out.println("Skipped because issue " + issueId + " isn't closed");
    }
  }
}




