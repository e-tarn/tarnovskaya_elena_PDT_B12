package com.stqa.addressbook.tests;

import com.stqa.addressbook.model.ConcactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
    app.getNav().goToHomePage();
    app.getContact().initEditFirstContact();
    app.getContact().fillContactCreationForm(new ConcactData(
            "modName",
            "M",
            "Odessa",
            "76543",
            "45653334444",
            "jjjj@kkkk.com"));
    app.getContact().confirmUpdateContact();
  }
}
