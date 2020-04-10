package com.stqa.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class TestBase {
  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }


  public boolean isIssueOpen(int issueId) throws IOException {
    return !getIssueStatus(issueId).equals("Resolved")|| !getIssueStatus(issueId).equals("Closed");

  }


  public String getIssueStatus(int id) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/"+id+".json"))
           .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement state = parsed.getAsJsonObject().getAsJsonArray("issues").get(0).getAsJsonObject().get("state_name");
    return new Gson().fromJson(state, new TypeToken<String>(){}.getType());
  }
}
