package com.stqa.mantis.tests;

import com.stqa.mantis.model.Issue;
import com.stqa.mantis.model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{
  @Test
  public  void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getId() + " " + project.getName());
    }
  }

  @Test
  public void tescCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue createdIssue = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), createdIssue.getSummary());
  }
}
