package com.stqa.addressbook.model;

import java.util.Objects;

public class ContactData {
  int id;
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
    this.id = 0;
    this.fname = fname;
    this.lname = lname;
    this.mName = mName;
    this.address = address;
    this.homePhone = homePhone;
    this.mobPhone = mobPhone;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String fname, String lname, String mName,
                     String address, String homePhone, String mobPhone,
                     String email, String group) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.mName = mName;
    this.address = address;
    this.homePhone = homePhone;
    this.mobPhone = mobPhone;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String fname, String lname) {
    this.fname = fname;
    this.lname = lname;

  }

  public ContactData(String fname, String lname) {
    this.fname = fname;
    this.lname = lname;

  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", fname='" + fname + '\'' +
            ", lname='" + lname + '\'' +
            '}';
  }

  public String getFname() {
    return fname;
  }

  public int getId() {
    return id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(fname, that.fname) &&
            Objects.equals(lname, that.lname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fname, lname);
  }

  public void setId(int id) {
    this.id = id;
  }
}
