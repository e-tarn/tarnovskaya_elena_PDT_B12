package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends  TestBase {
 //usable if all phones fields - required
  @Test(enabled=false)
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().allContacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobPhone(), equalTo(cleaned(contactInfoFromEditForm.getMobPhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }


  @Test
  public void testContactPhonesReverseCheck() {
    app.goTo().homePage();
    ContactData contact = app.contact().allContacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
//    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
//    assertThat(contact.getMobPhone(), equalTo(cleaned(contactInfoFromEditForm.getMobPhone())));
//    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobPhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
                    .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }

}
