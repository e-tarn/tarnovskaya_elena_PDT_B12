package com.stqa.addressbook.manager;

import com.stqa.addressbook.model.ContactData;
import com.stqa.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      if(contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        selectFromDropDown(By.name("new_group"), contactData.getGroups().iterator().next().getName());
      }


    } else Assert.assertFalse(isElementPresent(By.name("new_group")));

//
//
//
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

  public void selectContactById(int id) {
    click(By.cssSelector("input[id='" + id + "']"));
  }

  public void selectEditContactById(int id) {
    //click(By.xpath("//input[@id='" + id + "']/../..//img[@title='Edit']"));
    click(By.xpath(String.format("//input[@id='%s']/../..//img[@title='Edit']", id)));
  }

  public void delete(int i) {
    select(i);
    click(By.cssSelector("[onclick='DeleteSel()']"));
    acceptAlert();

    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintable")));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    click(By.cssSelector("[onclick='DeleteSel()']"));
    acceptAlert();

    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("maintable")));
  }


  public void initEditFirstContact() {
    click(By.cssSelector("[title=Edit]"));
  }

  public void confirmUpdateContact() {
    click(By.name("update"));
  }

  public void deleteFromModificationForm(int index) {
    initEdit(index);
    click(By.cssSelector("[action='delete.php'] [name=update]"));

  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactCreationForm(contact, true);
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
      ContactData contact = new ContactData().withId(id).withFname(fname).withLname(lname);
      contacts.add(contact);
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.xpath(".//td[1]/input")).getAttribute("value"));
      String lname = row.findElement(By.xpath(".//td[2]")).getText();
      String fname = row.findElement(By.xpath(".//td[3]")).getText();
      ContactData contact = new ContactData().withId(id).withFname(fname).withLname(lname);

      contacts.add(contact);
    }
    return contacts;
  }

  public Contacts allContacts() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement row : rows) {
      int id = Integer.parseInt(row.findElement(By.xpath(".//td[1]/input")).getAttribute("value"));
      String lname = row.findElement(By.xpath(".//td[2]")).getText();
      String fname = row.findElement(By.xpath(".//td[3]")).getText();
      String address = row.findElement(By.xpath(".//td[4]")).getText();
      String allEmails = row.findElement(By.xpath(".//td[5]")).getText();
   String allPhones = row.findElement(By.xpath(".//td[6]")).getText();
     // String[] phones = row.findElement(By.xpath(".//td[6]")).getText().split("\n");

      ContactData contact = new ContactData()
              .withId(id)
              .withFname(fname)
              .withLname(lname)
              .withAddress(address)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones);

      contacts.add(contact);
    }
    return contacts;
  }

  public void initEdit(int i) {
    clickByIndex(By.cssSelector("[title=Edit]"), i);
  }


  public void modifyById(ContactData contact) {
    selectEditContactById(contact.getId());
    fillContactCreationForm(contact, false);
    confirmUpdateContact();
    returnToHomePage();

  }

  public ContactData infoFromEditForm(ContactData contact) {
    selectEditContactById(contact.getId());
    String lName = wd.findElement(By.name("lastname")).getAttribute("value");
    String fName = wd.findElement(By.name("firstname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();

    return new ContactData().withId(contact.getId())
            .withFname(fName)
            .withLname(lName)
            .withAddress(address)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3)
            .withHomePhone(home)
            .withMobPhone(mobile)
            .withWorkPhone(work);
  }

  public void addContactToTheGroup(int groupId) {

    Select selectGroup = new Select(wd.findElement(By.name("to_group")));
selectGroup.selectByValue(Integer.toString(groupId));
click(By.name("add"));
  }

  public void addContactToTheGroup(int contactId, int groupId){
   selectContactById(contactId);
   addContactToTheGroup(groupId);
  }

  public void filterContactsByGroup(int groupToEdit) {
    new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(groupToEdit));
  }

  public void deleteContactFromTheGroup() {
    click(By.name("remove"));


  }
}
