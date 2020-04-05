package com.stqa.mantis.model;

public class User {
  String name;
  String email;
  String password;

  public String getName() {
    return name;
  }

  public User setName(String name) {
    this.name = name;
    return  this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return  this;
  }



  public User setPassword(String password) {
    this.password = password;
    return  this;
  }
}
