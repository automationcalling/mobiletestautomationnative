package com.automationcalling.testsuites;

import com.automationcalling.android.AndroidUtils;
import com.automationcalling.appiumutils.MobileTestDriver;
import com.automationcalling.driverconfig.DriverFactory;
import com.automationcalling.ios.IOSUtils;
import com.automationcalling.pageactions.ContactHomePageActions;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.IHookable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SimpleTest extends DriverFactory implements IHookable {
    private ContactHomePageActions contactHomePageActions;

    @BeforeMethod
    public void init() {
        contactHomePageActions = new ContactHomePageActions(driver, mobileTestDriver);
    }

    @Test(priority = 0)
    @Description("Clicking Add new contact button")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Mobile - Add button")
    public void sampleTest() {
        contactHomePageActions.clickAddContactButton();
    }

}
