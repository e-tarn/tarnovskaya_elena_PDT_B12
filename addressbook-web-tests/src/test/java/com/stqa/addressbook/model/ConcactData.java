package com.stqa.addressbook.model;

public class ConcactData {
  private final String fname;
  private final String lname;
  private final String address;
  private final String homePhone;
  private final String mobPhone;
  private final String email;

  public ConcactData(String fname, String lname, String address, String homePhone, String mobPhone, String email) {
    this.fname = fname;
    this.lname = lname;
    this.address = address;
    this.homePhone = homePhone;
    this.mobPhone = mobPhone;
    this.email = email;
  }

  public String getFname() {
    return fname;
  }

  public String getLname() {
    return lname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobPhone() {
    return mobPhone;
  }

  public String getEmail() {
    return email;
  }
}
