package com.automationcalling.android;

import com.automationcalling.appiumutils.CommonAppium;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


/*
 All native Android Native elements implementation can be done,
 except the commond method in CommonAppium
 */
 public class AndroidUtils extends CommonAppium {

    private AndroidDriver<MobileElement> androidDriver;

    /**
     *
     * @param driver passing driver Instance
     */
    public AndroidUtils(AppiumDriver<MobileElement> driver) {

        super(driver);
        androidDriver = (AndroidDriver<MobileElement>) driver;
    }

}
