package com.stqa.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends  TestBase {
  @Test
  public void testContactCreation(){
    goToHomePage();
    initContactCreation();
    fillContactCreationForm(new ConcactData("Olga", "Ivanova", "Moscow", "123456", "7890123", "qw@we.com"));
    submitContactCreation();
    returnToHomePage();
  }

  public void returnToHomePage() {
    wd.findElement(By.cssSelector("[href='index.php']")).click();
  }

  public void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }

  public void fillContactCreationForm(ConcactData concactData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(concactData.getFname());


    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(concactData.getAddress());

    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(concactData.getHomePhone());

    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(concactData.getMobPhone());

    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(concactData.getEmail());
  }

  public void initContactCreation() {
    wd.findElement(By.cssSelector("[href='edit.php']")).click();
  }

  public void goToHomePage() {
    wd.findElement(By.cssSelector("[href='./']")).click();
  }
}
