package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.ConcactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.cssSelector("[href='index.php']"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactCreationForm(ConcactData concactData) {
    type(By.name("firstname"), concactData.getFname());
    type(By.name("address"), concactData.getAddress());
    type(By.name("home"), concactData.getHomePhone());
    type(By.name("mobile"), concactData.getMobPhone());
    type(By.name("email"), concactData.getEmail());
  }

  public void initContactCreation() {
    click(By.cssSelector("[href='edit.php']"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.cssSelector("[onclick='DeleteSel()']"));
    acceptAlert();
  }


  public void initEditFirstContact() {
    click(By.cssSelector("[title=Edit]"));
  }

  public void confirmUpdateContact() {
    click(By.name("update"));
  }

  public void deleteContactFromModificationForm() {
    click(By.cssSelector("[action='delete.php'] [name=update]"));
  }
}
