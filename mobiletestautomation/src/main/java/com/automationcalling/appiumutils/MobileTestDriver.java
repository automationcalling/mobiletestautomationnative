package com.automationcalling.appiumutils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public interface MobileTestDriver {

    void implictyWaitConidtion(long secs);

    void clickOn(WebElement element);

    void navigateBack();

    String getElementText(WebElement element);

    Dimension getSizeofDevices();

    int getScreenWidth();

    int getScreenHeight();

    void swipeToLeft(double xAxis, double endX);

    void swipeToRight(double xAxis, double endX);

    boolean isElementPresent(WebElement element);

    void enterText(WebElement element, String text);

    void scrolltoElement(String scrollType, WebElement element);

    String getSnapshot(String classname, String classmethodname, String platformType, String deviceName);

}
