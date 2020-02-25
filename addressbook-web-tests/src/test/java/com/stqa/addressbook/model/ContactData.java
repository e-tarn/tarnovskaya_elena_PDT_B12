package com.stqa.addressbook.model;

public class ContactData {
  private String fname;
  private String lname;
  private String mName;
  private String address;
  private String homePhone;
  private String mobPhone;
  private String email;
  private String group;

  public ContactData(String fname, String lname, String mName,
                     String address, String homePhone, String mobPhone,
                     String email, String group) {
    this.fname = fname;
    this.lname = lname;
    this.mName = mName;
    this.address = address;
    this.homePhone = homePhone;
    this.mobPhone = mobPhone;
    this.email = email;
    this.group = group;
  }

  public ContactData(String fname, String lname) {
    this.fname = fname;
    this.lname = lname;

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

  public String getGroup() {
    return group;
  }

  public String getmName() {
    return mName;
  }
}
