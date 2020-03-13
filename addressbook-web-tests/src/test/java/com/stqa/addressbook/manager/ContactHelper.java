package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  //public ContactHelper(WebDriver wd) {
//    super(wd);
//  }

  public ContactHelper(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  public void returnToHomePage() {
    click(By.cssSelector("[href='index.php']"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactCreationForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFname());
    type(By.name("middlename"), contactData.getmName());
    type(By.name("lastname"), contactData.getLname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobPhone());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group")))
              .selectByVisibleText(contactData.getGroup());
    } else Assert.assertFalse(isElementPresent(By.name("new_group")));

  }

  public void fillContactCreationFormShortVersion(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFname());
    type(By.name("middlename"), contactData.getmName());

  }

  public void initContactCreation() {
    click(By.cssSelector("[href='edit.php']"));
  }

  public void select(int i) {
    clickByIndex(By.name("selected[]"), i);
  }

  public void delete() throws InterruptedException {
    click(By.cssSelector("[onclick='DeleteSel()']"));
    acceptAlert();
    Thread.sleep(5000);
    //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintable")));
  }


  public void initEditFirstContact() {
    click(By.cssSelector("[title=Edit]"));
  }

  public void confirmUpdateContact() {
    click(By.name("update"));
  }

  public void deleteFromModificationForm(int index) throws InterruptedException {
    initEdit(index);
    click(By.cssSelector("[action='delete.php'] [name=update]"));
    Thread.sleep(5000);
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactCreationFormShortVersion(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(ContactData contact, int index) {
    initEdit(index);
    fillContactCreationForm(contact, false);
    confirmUpdateContact();
    returnToHomePage();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.xpath(".//td[1]/input")).getAttribute("value"));
      String lname = row.findElement(By.xpath(".//td[2]")).getText();
      String fname = row.findElement(By.xpath(".//td[3]")).getText();
      ContactData contact = new ContactData(id, fname, lname);
      contacts.add(contact);
    }
    return contacts;
  }

  public void initEdit(int i) {
    clickByIndex(By.cssSelector("[title=Edit]"), i);
  }
}
