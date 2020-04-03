package com.stqa.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_in_groups")
public class ContGroup {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "group_id")
  private int groupId;

  public ContGroup(int id, int groupId) {
    this.id = id;
    this.groupId = groupId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public int getGroupId() {
    return groupId;
  }
  public int getId() {
    return id;
  }



  @Override
  public String toString() {
    return "ContGroup{" +
            "id=" + id +
            ", groupId=" + groupId +
            '}';
  }
}
