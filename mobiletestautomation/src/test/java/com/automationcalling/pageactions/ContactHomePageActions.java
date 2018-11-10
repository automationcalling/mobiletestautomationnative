package com.automationcalling.pageactions;

import com.automationcalling.appiumutils.MobileTestDriver;
import com.automationcalling.pageobjects.ContactHomePageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;

public class ContactHomePageActions extends ContactHomePageObjects {
    private MobileTestDriver mobileTestDriver;

    public ContactHomePageActions(AppiumDriver<MobileElement> driver, MobileTestDriver mobileTestDriver) {
        super(driver);
        this.mobileTestDriver = mobileTestDriver;
    }

    @Step("Click on Add Contact Button")
    public void clickAddContactButton()
    {
        mobileTestDriver.clickOn(getAddContactButton());
    }
}
