package com.automationcalling.driverconfig;

import com.automationcalling.android.AndroidUtils;
import com.automationcalling.appiumutils.MobileTestDriver;
import com.automationcalling.commonutils.Constant;
import com.automationcalling.ios.IOSUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverFactory {
    protected AppiumDriver<MobileElement> driver;
    protected DesiredCapabilities caps;
    protected String platformName;
    protected MobileTestDriver mobileTestDriver;

    /**
     * To get SauceLab User name which is set in Environment Variable
     * @return
     */
    public String getSauceUserName() {
        return System.getenv("SAUCE_USERNAME");
    }

    /**
     * To get SauceKey which is set in environment variable
     * @return
     */
    public String getSauceKey() {
        return System.getenv("SAUCE_ACCESS_KEY");
    }

    /**
     * To get TestObject API Key which is set in environment variable
     * @return
     */
    public String getTestAPPKey() {
        return System.getenv("TESTOBJECT_API_KEY");
    }

    @BeforeClass
    @Parameters({"ExecutionType", "platformName", "deviceName", "platformVersion", "appName",
            "deviceOrientation", "appiumVersion", "realdeviceendpoint", "testobject_app_id",
            "appiumServerName", "appiumServerPort", "udid"})
    public void init(String ExecutionType, String platformName, String deviceName, String platformVersion,
                     String appName, String deviceOrientation, String appiumVersion, String realdeviceendpoint, String testobject_app_id
            , String appiumServerName, String appiumServerPort, String udid) {
        try {
            this.platformName = platformName;
            driver = getDriver(ExecutionType, platformName, deviceName, platformVersion, appName, deviceOrientation, appiumVersion, realdeviceendpoint,
                    testobject_app_id, appiumServerName, appiumServerPort, udid);
            Assert.assertNotNull(driver);
        } catch (Exception  | AssertionError e) {
            Assert.fail("Driver Initialization is failed" + e.getMessage());
        }
    }

    /**
     * Initalizing local/SuaceLabEmulator/SauceLabRealDevice
     * @param deviceParam passing device configuration
     * @return
     * @throws IOException
     */
    protected AppiumDriver<MobileElement> getDriver(String... deviceParam) throws IOException {
        if (deviceParam[0].equalsIgnoreCase("localRealDevice")) {
            localRealDeviceInitialization(deviceParam);
        } else if (deviceParam[0].equalsIgnoreCase("SauceLabEmulator")) {
            sauceLabEmulatorDeviceInitialization(deviceParam);
        } else if (deviceParam[0].equalsIgnoreCase("SauceLabRealDevice")) {
            sauceLabRealDeviceInitialization(deviceParam);
        } else if (deviceParam[0].equalsIgnoreCase("GenyMotionDevice")) {
            genyMotionDeviceInitialization(deviceParam);
        } else {
            driver = null;
        }
        return driver;
    }

    /**
     * Initalizing local device capabilities
     * @param deviceParam
     * @throws IOException
     */
    private void localRealDeviceInitialization(String... deviceParam) throws IOException {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", deviceParam[1]); //Initalizing platform Name Android or IOS
        caps.setCapability("deviceName", deviceParam[2]);
        caps.setCapability("platformVersion", deviceParam[3]);
        caps.setCapability("app", System.getProperty("user.dir") + deviceParam[4]);
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("noReset", "true");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("udid", deviceParam[11]); //Give Device ID of your mobile phone
        //If app is already installed then use apppackage or appactivity
        /*caps.setCapability("appPackage", PropsLoader.getPropertyValue("packagename"));
        caps.setCapability("appActivity", PropsLoader.getPropertyValue("activityname"));*/
        if (deviceParam[1].equalsIgnoreCase("Android")) {
            driver = new AndroidDriver<>(new URL(deviceParam[9] + ":" + deviceParam[10] + "/wd/hub"), caps);
            mobileTestDriver = new AndroidUtils(driver);
        } else if (deviceParam[1].equalsIgnoreCase("iOS")) {
            driver = new IOSDriver<>(new URL(deviceParam[9] + ":" + deviceParam[10] + "/wd/hub"), caps);
            mobileTestDriver = new IOSUtils(driver);
        }
        driver.manage().timeouts().implicitlyWait(Constant.SyncTime.WAITIN_60SECS.getValue(), TimeUnit.SECONDS);
    }

    /**
     * Initalizing SauceLabEmulatorDevice Capabilities
     * @param deviceParam
     * @throws IOException
     */
    private void sauceLabEmulatorDeviceInitialization(String... deviceParam) throws IOException {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", deviceParam[1]); //Initalizing platform Name Android or IOS
        caps.setCapability("deviceName", deviceParam[2]);
        caps.setCapability("platformVersion", deviceParam[3]);
        caps.setCapability("app", deviceParam[4]);
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("noReset", "true");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("udid", deviceParam[11]); //Give Device ID of your mobile phone
        if (deviceParam[1].equalsIgnoreCase("Android")) {
            driver = new AndroidDriver<>(new URL("http://" + getSauceUserName() + ":" + getSauceKey() + "@ondemand.saucelabs.com:80/wd/hub"), caps);
            mobileTestDriver = new AndroidUtils(driver);
        } else if (deviceParam[1].equalsIgnoreCase("iOS")) {
            driver = new IOSDriver<>(new URL("http://" + getSauceUserName() + ":" + getSauceKey() + "@ondemand.saucelabs.com:80/wd/hub"), caps);
            mobileTestDriver = new IOSUtils(driver);
        }
        driver.manage().timeouts().implicitlyWait(Constant.SyncTime.WAITIN_60SECS.getValue(), TimeUnit.SECONDS);
    }

    /**
     * Initalizing SauceLab Real device Capabiliities
     * @param deviceParam
     * @throws IOException
     */
    private void sauceLabRealDeviceInitialization(String... deviceParam) throws IOException {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", deviceParam[1]);
        caps.setCapability("deviceName", deviceParam[2]);
        caps.setCapability("platformVersion", deviceParam[3]);
        caps.setCapability("testobject_api_key", getTestAPPKey());
        caps.setCapability("appiumVersion", deviceParam[6]);
        caps.setCapability("testobject_app_id", deviceParam[8]);
        if (deviceParam[1].equalsIgnoreCase("Android")) {
            driver = new AndroidDriver<>(new URL(deviceParam[7]), caps);
            mobileTestDriver = new AndroidUtils(driver);
        } else if (deviceParam[1].equalsIgnoreCase("iOS")) {
            driver = new IOSDriver<>(new URL(deviceParam[7]), caps);
            mobileTestDriver = new IOSUtils(driver);
        }
        driver.manage().timeouts().implicitlyWait(Constant.SyncTime.WAITIN_60SECS.getValue(), TimeUnit.SECONDS);
    }

    /**
     * Initializing GenyMotionDevice capabilities
     * @param deviceParam
     * @throws IOException
     */
    private void genyMotionDeviceInitialization(String... deviceParam) throws IOException {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", deviceParam[1]); //Initalizing platform Name Android or IOS
        caps.setCapability("deviceName", deviceParam[2]);
        caps.setCapability("platformVersion", deviceParam[3]);
        caps.setCapability("app", System.getProperty("user.dir") + deviceParam[4]);
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("noReset", "true");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("udid", deviceParam[11]);
        if (deviceParam[1].equalsIgnoreCase("Android")) {
            driver = new AndroidDriver<>(new URL(deviceParam[9] + ":" + deviceParam[10] + "/wd/hub"), caps);
            mobileTestDriver = new AndroidUtils(driver);
        } else if (deviceParam[1].equalsIgnoreCase("iOS")) {
            driver = new IOSDriver<>(new URL(deviceParam[9] + ":" + deviceParam[10] + "/wd/hub"), caps);
            mobileTestDriver = new IOSUtils(driver);
        }
        driver.manage().timeouts().implicitlyWait(Constant.SyncTime.WAITIN_60SECS.getValue(), TimeUnit.SECONDS);
    }

    protected void quitDriver() {
        try {
            if (driver != null)
                driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is for taking screenshots
     *
     * @param name
     * @return
     */
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public byte[] saveScreenshot(String name) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * This method to identify test pass or fail and add screenshot in allure
     *
     * @param iHookCallBack
     * @param iTestResult
     */
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        iHookCallBack.runTestMethod(iTestResult);
        if (iTestResult.getThrowable() != null) {
            this.saveScreenshot(iTestResult.getName());
        }
    }

    @AfterClass
    public void tearDownScripts() throws MalformedURLException {
        quitDriver();
    }

}
