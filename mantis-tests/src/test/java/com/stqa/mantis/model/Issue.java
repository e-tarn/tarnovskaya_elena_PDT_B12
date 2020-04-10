package com.stqa.mantis.model;

public class Issue {
  private int id;
  private String summary;
  private String description;
  private String status;
  private  Project project;

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue setStatus(String status) {
    this.status = status;
    return this;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public String getStatus() {
    return status;
  }

  public Project getProject() {
    return project;
  }

  @Override
  public String toString() {
    return "Issue{" +
            "id=" + id +
            ", status='" + status + '\'' +
            '}';
  }
}

