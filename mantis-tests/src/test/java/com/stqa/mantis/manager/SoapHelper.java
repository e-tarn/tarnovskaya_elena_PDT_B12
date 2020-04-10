package com.stqa.mantis.manager;

import biz.futureware.mantis.rpc.soap.client.*;
import com.stqa.mantis.model.Issue;
import com.stqa.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private final ApplicationManager app;
//http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php?wsdl
// new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();

    ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), "root");
    return Arrays.asList(projects).stream().map((p) -> new Project()
            .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  public Set<Issue> getIssues(int projectId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();

    IssueData[] issues = mc.mc_project_get_issues(app.getProperty("web.adminLogin"), "root", BigInteger.valueOf(projectId), BigInteger.valueOf(-1), BigInteger.valueOf(500));
    return Arrays.asList(issues).stream().map((i) -> new Issue()
            .withId(i.getId().intValue())).collect(Collectors.toSet());
  }


  public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator().getMantisConnectPort(

            new URL(app.getProperty("web.baseUrl") + "api/soap/mantisconnect.php"));
  }


  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueData);
    IssueData createdIssueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), issueId);
    return new Issue()
            .withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project()
                    .withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));

  }


  public boolean isIssuesExists(int projectId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_project_get_issues(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(projectId), BigInteger.valueOf(-1), BigInteger.valueOf(1)).length > 0;

  }

  public int getIssuesCountInProject(int projectId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData[] issueData = mc.mc_project_get_issues(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(projectId), BigInteger.valueOf(-1), BigInteger.valueOf(1));
    return issueData.length;
  }


  public IssueData getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    return getMantisConnect().mc_issue_get(app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
  }

}
