package com.automationcalling.ios;

import com.automationcalling.appiumutils.CommonAppium;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

/*
 All native iOS Native elements implementation can be done,
 except the commond method in CommonAppium
 */
public class IOSUtils extends CommonAppium {

    private IOSDriver<MobileElement> iOSDriver;

    public IOSUtils(AppiumDriver<MobileElement> driver){
        super(driver);
        iOSDriver = (IOSDriver<MobileElement>) driver;
    }

}
