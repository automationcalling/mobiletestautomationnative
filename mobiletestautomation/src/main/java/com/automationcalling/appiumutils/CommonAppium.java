package com.automationcalling.appiumutils;

import com.automationcalling.commonutils.Constant;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class CommonAppium implements MobileTestDriver {
    protected AppiumDriver<MobileElement> driver;

    public CommonAppium(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    /**
     * Swipe from Left to Right
     * @param xAxis
     * @param endX
     */
    public void swipeToRight(double xAxis, double endX) {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * xAxis);
        int endx = (int) (size.width * endX);
        int starty = size.height / 2;
        new TouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(Constant.SyncTime.WAITIN_2SECS.getValue())))
                .moveTo(PointOption.point(endx, starty)).release().perform();
    }

    /**
     * Swipe from Right to Left
     * @param xAxis
     * @param endX
     */
    public void swipeToLeft(double xAxis, double endX) {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * xAxis);
        int endx = (int) (size.width * endX);
        int starty = size.height / 2;
        new TouchAction(driver).press(PointOption.point(startx, starty))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(Constant.SyncTime.WAITIN_2SECS.getValue())))
                .moveTo(PointOption.point(endx, starty)).release().perform();
    }


    /**
     *
     * @param scrollType "up" or "down"
     * @param element
     */
    public void scrolltoElement(String scrollType, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", scrollType);
        scrollObject.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: scroll", scrollObject);
    }


    public String getSnapshot(String classname, String methodname, String platformType, String deviceName) {
        /*String desLocation = null;
        try {
            TakesScreenshot ts = driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            desLocation = System.getProperty("user.dir") +SCREENSHOTLOC+classname +
                    "_" + methodname + "_" + platformType + "_" + deviceName+".png";
            File destination=new File(desLocation);
            System.out.println(desLocation);
            FileUtils.copyFile(source, destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desLocation;*/
        return null;
    }

    /**
     * Back to the screen
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Get current device size
     * @return
     */
    public Dimension getSizeofDevices() {
        return driver.manage().window().getSize();
    }

    /**
     * Get Device ScreenWidth
     * @return
     */
    public int getScreenWidth() {
        return getSizeofDevices().getWidth();
    }

    /**
     * Get Device Screen Height
     * @return
     */
    public int getScreenHeight() {
        return getSizeofDevices().getHeight();
    }


    /**
     * Implicit wait condition
     * @param timeWaitInSecs
     */
    public void implictyWaitConidtion(long timeWaitInSecs) {
        driver.manage().timeouts().implicitlyWait(timeWaitInSecs, TimeUnit.SECONDS);
    }

    /**
     * Wait until visibility of element
     * @param element
     */
    public void synchronizationVisibilityofElement(WebElement element) {

        new WebDriverWait(driver, Constant.SyncTime.WAITIN_45SECS.getValue()).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Click on element
     * @param element
     */
    public void clickOn(WebElement element) {
        synchronizationVisibilityofElement(element);
        element.click();

    }

    /**
     * Verify element is present
     * @param element
     * @return
     */
    public boolean isElementPresent(WebElement element) {
        synchronizationVisibilityofElement(element);
        return element.isDisplayed();
    }

    /**
     * Enter value to the textbox
     * @param element
     * @param text
     */
    public void enterText(WebElement element, String text) {
        synchronizationVisibilityofElement(element);
        element.sendKeys(text);
    }

    /**
     * GetText from element
     * @param element
     * @return
     */
    public String getElementText(WebElement element)
    {
        synchronizationVisibilityofElement(element);
        return element.getText();
    }


}
