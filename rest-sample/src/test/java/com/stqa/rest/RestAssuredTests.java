package com.stqa.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import  io.restassured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestAssuredTests  {
  @BeforeClass
  public void init(){
   RestAssured.authentication =  RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }
  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("test"+ System.currentTimeMillis()).withDescription("test qwerty d");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);

  }

  private int createIssue(Issue newIssue) throws IOException {

    String json = RestAssured.given()
            .param("subject", newIssue.getSubject())
            .param("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json?limit=500").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");


    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

}
