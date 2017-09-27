package com.psu.ios;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
//import org.junit.*;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
public class AppiumIOS {

	protected IOSDriver<IOSElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    ExtentHtmlReporter html;
    ExtentXReporter extentx;
    ExtentReports extent;
    Screenshot screenshotTaker = new Screenshot();
    String reportDirectory = "reports";
    String reportFormat = "xml";
    String testName = "PSCU_Automized_Testing";
    String deviceName;
    ExtentTest test;
    ScreenCapture screenCapture;


	@BeforeSuite
    public void Reports() throws MalformedURLException {
        this.deviceName = "Streebo’s iPhone";
       // html = new ExtentHtmlReporter("D:\\Automation_Testing\\IOS\\IOS_TestResults.html");
        html = new ExtentHtmlReporter(String.format("\\Automation_IOS\\HTML_Reports\\IOS_Automation.html",this.deviceName));
        extent = new ExtentReports();
        screenCapture = new ScreenCapture();
        // extent.attachReporter(html, extentx);
        extent.attachReporter(html);
    }
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
    
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);

        // iPhone 7 = f172ad34e324e431749f19dbcc4fd8e9f9c75b34
        // iPhone 6S  = ae2ebecd7878587c83f7551dbff4acf1a376e660
        // iPhone 5 = a551a5ab21bfdd80b721e72a0ac376ef7c0d67cf
        // iPhone 6+ = 3835b88d9e35a00043f5ea066cb83634d286605c
        // iPhone 6 = c9021b8d2cd9f2e85a99d35195958b5e45727656
        // iPhone SE = c9021b8d2cd9f2e85a99d35195958b5e45727656

        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.streebo.pscu.mobile");
        driver = new IOSDriver<IOSElement>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test(priority = 0 ,testName = "Forgot Username", enabled=true)
    public void testForgotUsername() throws  IOException,InterruptedException{
        testName = "Forgot Username";
        screenCapture.setName(testName);
        test = extent.createTest(testName);

        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure forgot username option should be available in login screen",new MediaEntityModelProvider(screenCapture));


        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Forgot Username' and @class='UIAStaticText']")));
        driver.findElement(By.xpath("//*[@text='Forgot Username' and @class='UIAStaticText']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User is redirected to forgot username page",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@placeholder='Enter email address' and ./parent::*[@placeholder='Enter email address']]")).sendKeys("test");
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Next should be dissabled and inline error message displayed",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@placeholder='Enter email address' and ./parent::*[@placeholder='Enter email address']]")).clear();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User has cleared email id field",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@placeholder='Enter email address' and ./parent::*[@placeholder='Enter email address']]")).sendKeys("test@test.com");
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User has entered valid email id next button will be enabled",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='NEXT arrow forward']")).click();
        String text1 = driver.findElement(By.xpath("//*[@class='UIATextField' and ./parent::*[@class='UIAView' and ./parent::*[@text]]]")).getText();
        AssertJUnit.assertEquals(text1, "In what city was your high school?");
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"After click on next user should be redirected to MFA Question screen",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@placeholder='Enter Answer' and ./parent::*[@placeholder='Enter Answer']]")).sendKeys("test1");
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User has entered incorrect Answer",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='SUBMIT']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User click on SUBMIT",new MediaEntityModelProvider(screenCapture));

        String message = driver.findElement(By.xpath("//*[@class='UIAStaticText' and ./parent::*[./following-sibling::*[@class='UIAButton'] and ./parent::*[./parent::*[@text]]]]")).getText();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User get error message "+message,new MediaEntityModelProvider(screenCapture));
        driver.findElement(By.xpath("//*[@text='OK']")).click();

        driver.findElement(By.xpath("//*[@placeholder='Enter Answer' and ./parent::*[@placeholder='Enter Answer']]")).clear();
        driver.findElement(By.xpath("//*[@placeholder='Enter Answer' and ./parent::*[@placeholder='Enter Answer']]")).sendKeys("test");
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User has entered valid answer ",new MediaEntityModelProvider(screenCapture));
        driver.findElement(By.xpath("//*[@text='SUBMIT']")).click();

        message = driver.findElement(By.xpath("//*[@class='UIAStaticText' and ./parent::*[./following-sibling::*[@class='UIAButton'] and ./parent::*[./parent::*[@text]]]]")).getText();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User get success message "+message,new MediaEntityModelProvider(screenCapture));
        driver.findElement(By.xpath("//*[@text='OK']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1 ,testName = "Log In", enabled=true)
    public void performLogin() throws  IOException,InterruptedException {
        testName = "Log In";
        screenCapture.setName(testName);
        test = extent.createTest(testName);

        driver.findElement(By.xpath("//*[@placeholder='Username' and ./parent::*[@placeholder='Username']]")).sendKeys("test");
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS, "Make sure Login button should be disable", new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@placeholder='Password' and ./parent::*[@placeholder='Password']]")).sendKeys("test1");
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS, "Make sure Login button should be enabled", new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='LOGIN' and @class='UIAButton']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS, "Make sure user should be on Account Summary screen", new MediaEntityModelProvider(screenCapture));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

        @Test(priority = 2 ,testName = "Payment Summary", enabled=true)
    public void testPaymentSummary() throws  IOException,InterruptedException{
        testName = "Payment Summary";
        screenCapture.setName(testName);
        test = extent.createTest(testName);

        driver.findElement(By.xpath("//*[@class='UIAView' and @width>0 and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@text='Account Summary' and @class='UIAView']]]]]")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure user should be on Transaction screen on click of any card",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@class='UIAView' and @width>0 and ./*[@text='PENDING' and @class='UIAImage']]")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure transaction should be filtered and only pending transaction will be display",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@class='UIAView' and @width>0 and ./*[@text='POSTED' and @class='UIAImage']]")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure transaction should be filtered and only posted transaction will be display",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@placeholder='Enter keyword, amount']")).sendKeys("nik");
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure list will be display based on search data",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@class='UIAButton' and ./preceding-sibling::*[./*[@text]] and ./preceding-sibling::*[@placeholder='Enter keyword, amount']]")).click();
        driver.hideKeyboard();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure on click of clear search data should be cleared and all data should display",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@class='UIAView' and @width>0 and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@text='Transaction' and @class='UIAView']]]]]")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure list should be expanded on click of it",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@class='UIAView' and @width>0 and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@class='UIAView' and ./*[@text='Transaction' and @class='UIAView']]]]]")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Make sure list should be collapsed on click of expanded item",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='menuIcon']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Click on three bar menu icon",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='MY ACCOUNTS']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User should be redirected to My Account screen",new MediaEntityModelProvider(screenCapture));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(priority = 3 ,testName = "Perform Logout", enabled=true)
    public void performLogout() throws  IOException,InterruptedException{
        testName = "Log Out";
        screenCapture.setName(testName);
        test = extent.createTest(testName);

        driver.findElement(By.xpath("//*[@text='menuIcon']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"Click on three bar menu icon",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='LOGOUT']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OK']")));
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"App should display confirmation for log out",new MediaEntityModelProvider(screenCapture));

        driver.findElement(By.xpath("//*[@text='OK']")).click();
        screenCapture.setPath(screenshotTaker.getscreenshot(driver, testName));
        test.log(Status.PASS,"User should be logged-out and redirected to login screen on click of OK",new MediaEntityModelProvider(screenCapture));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


  
	@AfterSuite
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}
