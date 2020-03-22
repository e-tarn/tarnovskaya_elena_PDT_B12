package com.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;
@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  int id = Integer.MAX_VALUE;
  @Expose
  private String fname;
  @Expose
  private String lname;
  private String mName;
  private String address;
  private String homePhone;
  private String mobPhone;
  private String workPhone;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;

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

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }


  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
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
    return "ContactData{" +
            "id=" + id +
            ", fname='" + fname + '\'' +
            ", lname='" + lname + '\'' +
            ", address='" + address + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", allEmails='" + allEmails + '\'' +
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

  public String getWorkPhone() {
    return workPhone;
  }


  public String getAllPhones() {
    return allPhones;
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

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }
}
