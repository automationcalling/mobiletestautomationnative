package com.automationcalling.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactHomePageObjects {

    private AppiumDriver<MobileElement> driver;

    @FindBy(id = "addContactButton")
    @AndroidFindBy(id = "com.example.android.contactmanager:id/addContactButton")
    @iOSFindBy(id = "com.example.android.contactmanager:id/addContactButton")
    private WebElement addContactButton;

    public ContactHomePageObjects(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getAddContactButton()
    {
        return addContactButton;
    }
}
