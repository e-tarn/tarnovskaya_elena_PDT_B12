package com.stqa.addressbook.model;

import java.util.Objects;

public class ContactData {
  int id = Integer.MAX_VALUE;
  private String fname;
  private String lname;
  private String mName;
  private String address;
  private String homePhone;
  private String mobPhone;
  private String email;
  private String group;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withFname(String fname) {
    this.fname = fname;
    return this;
  }

  public ContactData withLname(String lname) {
    this.lname = lname;
    return this;
  }

  public ContactData withmName(String mName) {
    this.mName = mName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobPhone(String mobPhone) {
    this.mobPhone = mobPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
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

  @Override
  public String toString() {
    return  "id=" + id +
            ", fname='" + fname + '\'' +
            ", lname='" + lname + '\'' ;
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


}
