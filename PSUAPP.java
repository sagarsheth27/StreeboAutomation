package com.streebo.psumobile;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.bcel.verifier.exc.VerificationException;
import org.junit.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PSUAPP {
	//WebDriver driver;
	ExtentTest test;
	ExtentReports extent;
	Dimension size;
	AndroidDriver driver;
	
	@BeforeSuite
	public void extent_reports()
	{

		extent =new ExtentReports("D:\\Automation_Testing\\Android\\Android_TestResults.html",true);
		
		
	}
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		test = extent.startTest("Verify PSUAPP Application");
		// Create objects of Desiredcapabilities class.
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set device name using capabilities
		capabilities.setCapability("deviceName", "Pixel XL");
		//capabilities.setCapability("deviceName", "AndroidNew");
		test.log(LogStatus.PASS,"Phone Application Started");
		// Set capability as browser name here ,it is Android
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
		// Set capability of Android version number
		//capabilities.setCapability(CapabilityType.VERSION, "7.1");
		capabilities.setCapability(CapabilityType.VERSION, "6.0");
		// Set Android platform name as here in our case it is Android
		/*capabilities.setCapability("appium-version", "1.6.5");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("deviceName","HT6A40205681");*/
		capabilities.setCapability("platformName", "Android");
		test.log(LogStatus.PASS, "Load Application");
		// Set android app package capability names
		capabilities.setCapability("appPackage", "com.pscu.mobile");
		//capabilities.setCapability("appPath", "C:\\Users\\Sagar\\Downloads\\PSCU-mobileV0.0.8.apk");
		//capabilities.setCapability("udid", "HT6A40205681");
		// Set app launch activity in capability names as
		capabilities.setCapability("appActivity", "com.pscu.mobile.MainActivity");
		// Set Android driver in script to launch the app with appium server and
		// port number as below
		//driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		Screenshot s=new Screenshot();
		s.getscreenshot(driver);
		



	}
	
	@Test(priority = 0, description = "Test Case - 1 Open Mobile App and Enter Login data")
	public void Mobile() throws IOException, InterruptedException, AWTException {
		test=extent.startTest("Login Functionality of PSU Mobile App");
		driver.findElement(By.name("Username")).sendKeys("sagar");
		Thread.sleep(1000);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		test.log(LogStatus.PASS,"User Id is entered.");
		driver.findElement(By.name("Password")).sendKeys("ghghhgh");
		/*driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Robot handle = new Robot();
        handle.keyPress(KeyEvent.VK_TAB);
        handle.keyPress(KeyEvent.VK_T);
        handle.keyPress(KeyEvent.VK_E);
        handle.keyPress(KeyEvent.VK_S);
        handle.keyPress(KeyEvent.VK_T);*/
        test.log(LogStatus.PASS,"Password is entered.");
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //driver.findElement(By.name("LOGIN")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("LOGIN ")).click();
        //Thread.sleep(5000);
        driver.findElement(By.className("android.widget.Button")).click();
        test.log(LogStatus.PASS,"Click On login button");
                //Swipe Navigator bar from left to right
        /*driver.swipe(22, 456, 679, 416, 2000);
        driver.swipe(31, 224, 549, 202, 2000);
        Thread.sleep(2000);*/
      String actual=driver.findElement(By.name("Account Summary")).getTagName();
      System.out.println("The text is:::::::::::"+actual);
      String expected="android.webkit.WebView";
        try {
        	Assert.assertEquals(actual,expected);
        	//assertTrue(driver.findElement(By.name("Account Summar")).getText().matches("^[\\s\\S]*verify text is present[\\s\\S]*$"));
        	test.log(LogStatus.PASS,"Login into App Successfully"+":Verified");
        	} catch (Error e) {
        	test.log(LogStatus.FAIL,"Login into App Is not Successfully."+":Not Verified"+e.toString());
        	test.log(LogStatus.FAIL,"Account Summary Icon Is not dispayed."+e.getStackTrace());
        	//e.getStackTrace();
        	//verificationErrors.append(e);
        	}
        extent.endTest(test);
    	Screenshot s=new Screenshot();
		s.getscreenshot(driver);
		//extent.endTest(test);
		//extent.flush();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Thread.sleep(2000);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Thread.sleep(10000);
	}
	@Test(priority = 1, description = "Test Case - 2 Open Mobile App and Swipe the card",enabled=false)
	public void CardSwipe() throws IOException, InterruptedException, AWTException {
		test=extent.startTest("Card Swipe Functionality For PSU App");
		//driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
		driver.findElement(By.name("CARD SETTINGS")).click();
		test.log(LogStatus.PASS,"Click To swipe card");
		Thread.sleep(2000);
		Screenshot s1=new Screenshot();
		s1.getscreenshot(driver);
		//driver.findElement(By.xpath("//android.view.View[@index='0']")).click();
		driver.findElement(By.name("CARD SETTINGS")).click();
		test.log(LogStatus.PASS,"Click to reswipe Card");
		Thread.sleep(2000);
		
		Screenshot s=new Screenshot();
		s.getscreenshot(driver);
		extent.endTest(test);
		extent.flush();
		//Thread.sleep(10000);
	}
	@Test(priority = 2, description = "Test Case - 2 Open Mobile App and Transaction History Page",enabled=false)
	public void Transaction() throws IOException, InterruptedException, AWTException {
		test=extent.startTest("Transaction Functionality For PSU App");
		Thread.sleep(2000);
		driver.findElement(ByXPath.xpath("//android.widget.Image[@content-desc='visaWhite']")).click();
		test.log(LogStatus.PASS,"Click on card to vie transactions");
		Thread.sleep(1000);
		Screenshot s=new Screenshot();
		s.getscreenshot(driver);
		driver.findElement(ByXPath.xpath("//android.view.View[@content-desc='Kroger']")).click();
		test.log(LogStatus.PASS,"Click on accordian to see details");
		Screenshot s1=new Screenshot();
		s1.getscreenshot(driver);
		Thread.sleep(2000);
		List<WebElement> searchrslt = driver.findElements(By.className("android.widget.EditText"));
	    searchrslt.get(0).click();
	    searchrslt.get(0).sendKeys("kro");
		Thread.sleep(1000);
		//driver.findElement(ByXPath.className("//*[@class='android.widget.EditText']")).sendKeys("k");
		
		/*driver.findElement(By.name("Enter keyword, amount")).clear();
		driver.findElement(By.name("Enter keyword, amount")).sendKeys("kro");*/
		Robot handle = new Robot();
        handle.keyPress(KeyEvent.VK_K);
        handle.keyPress(KeyEvent.VK_R);
		//driver.getKeyboard().sendKeys("kroge");
		test.log(LogStatus.PASS,"Click on search field");
		Thread.sleep(2000);
		/*driver.findElement(ByXPath.xpath("//android.widget.EditText[@text='Enter keyword, amount']")).click();
		Thread.sleep(2000);
		driver.getKeyboard().sendKeys("kroge");*/
		//driver.findElement(ByXPath.xpath("//android.widget.EditText[@text='Enter keyword, amount']")).sendKeys("kro");
		Thread.sleep(2000);
		Screenshot s2=new Screenshot();
		s2.getscreenshot(driver);
		test.log(LogStatus.PASS,"Search with input data,in search field");
		Thread.sleep(1000);
		driver.findElement(ByXPath.xpath("//android.view.View[@content-desc='PENDING PENDING']")).click();
		test.log(LogStatus.PASS,"Click on pending status icon");
		Thread.sleep(2000);
		Screenshot s3=new Screenshot();
		s3.getscreenshot(driver);
		driver.findElement(ByXPath.xpath("//android.view.View[@content-desc='POSTED POSTED']")).click();
		test.log(LogStatus.PASS,"Click on posted status icon");
		Thread.sleep(2000);
		//driver.findElement(ByXPath.xpath("//android.widget.Button[@content-desc='arrow back ']")).click();
		//test.log(LogStatus.PASS,"Click on back arrow button");
		Thread.sleep(1000);
		s3.getscreenshot(driver);
		extent.endTest(test);
		extent.flush();
	}
	@Test(priority = 3, description = "Test Case - 2 Open Mobile App and Payment History Page",enabled=false)
	public void Payment() throws IOException, InterruptedException, AWTException {
		test=extent.startTest("Payment Functionality For PSU App");
		Thread.sleep(2000);
		driver.findElement(ByXPath.xpath("//android.widget.Image[@content-desc='visaWhite']")).click();
		test.log(LogStatus.PASS,"Click on visa card");
		Thread.sleep(1000);
		Screenshot s=new Screenshot();
		s.getscreenshot(driver);
		driver.findElement(ByXPath.xpath("//android.view.View['@content-desc='MAKE PAYMENT']")).click();
		test.log(LogStatus.PASS,"Click on Payment History");
		Thread.sleep(2000);
		driver.findElement(ByXPath.xpath("//android.view.View[@content-desc='PENDING PENDING']")).click();
		test.log(LogStatus.PASS,"Click on pending status icon");
		Thread.sleep(2000);
		Screenshot s3=new Screenshot();
		s3.getscreenshot(driver);
		driver.findElement(ByXPath.xpath("//android.view.View[@content-desc='POSTED POSTED']")).click();
		test.log(LogStatus.PASS,"Click on posted status icon");
		Thread.sleep(2000);
		driver.findElement(ByXPath.xpath("//android.view.View[@content-desc='SCHEDULED SCHEDULED']")).click();
		test.log(LogStatus.PASS,"Click on scheduled icon");
		Thread.sleep(2000);
		s3.getscreenshot(driver);
		extent.endTest(test);
		extent.flush();
	}
	
	@Test(priority = 4, description = "Test Case - 3 Account Functionality",enabled=true)
	public void Account() throws IOException, InterruptedException, AWTException {
		test=extent.startTest("Account Functionality For PSU App");
		driver.findElement(By.className("android.widget.Image")).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS,"Click on DP Image");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.name("UPDATE PASSWORD")).click();
		test.log(LogStatus.PASS,"Click On Update Password");
		Thread.sleep(2000);
		driver.findElement(By.name("arrow back ")).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS,"Click on back arrow button");
		driver.findElement(By.name("MY SECURITY QUESTIONS")).click();
		test.log(LogStatus.PASS,"Click on Security Questions");
		Thread.sleep(2000);
		driver.findElement(By.name("arrow back ")).click();
		test.log(LogStatus.PASS,"Click on back arrow button");
		Thread.sleep(2000);
		Screenshot s=new Screenshot();
		s.getscreenshot(driver);
		//driver.findElement(By.name("arrow back ")).click();
		driver.findElement(By.xpath("//android.widget.Button[@index='1']")).click();
		test.log(LogStatus.PASS,"Click on back arrow button to go main page");
		Thread.sleep(2000);
		/*Screenshot s1=new Screenshot();
		s1.getscreenshot(driver);*/
		//driver.findElement(By.id("menu")).click();
		//driver.findElement(By.xpath("//android.widget.Button[@contentDescription='menu']")).click();
		driver.findElement(ByClassName.className("android.widget.Button")).click();
		Screenshot s3=new Screenshot();
		s3.getscreenshot(driver);
		Thread.sleep(2000);
		driver.findElement(By.name("LOGOUT ")).click();
		//Use attribute as ID when run it through Appium server
		//driver.findElement(By.xpath("//android.widget.Button[@index='2']")).click();
		Thread.sleep(2000);
		test.log(LogStatus.PASS,"Log Out Successfully");
		driver.findElement(By.name("OK ")).click();
		test.log(LogStatus.PASS,"Click on Alert Ok Button");
		String actual=driver.findElement(By.name("Username")).getText();
		System.out.println("The actual text is:::"+actual);
		String expected="Username";
		try {
        	Assert.assertEquals(actual,expected);
        	//assertTrue(driver.findElement(By.name("Account Summar")).getText().matches("^[\\s\\S]*verify text is present[\\s\\S]*$"));
        	test.log(LogStatus.PASS,"Logout App Successfully"+"Verified");
        	} catch (Error e) {
        	test.log(LogStatus.FAIL,"Logout App Is not Successfully."+":Not Verified"+e.toString());
        	test.log(LogStatus.FAIL,"Home Page Is not dispayed."+e.getStackTrace());
        	//e.getStackTrace();
        	//verificationErrors.append(e);
        	}
		Screenshot s2=new Screenshot();
		s2.getscreenshot(driver);
		extent.endTest(test);
		//extent.flush();
		Thread.sleep(2000);
	}
	@AfterTest
	public void tearDown(){
	driver.quit();
	extent.flush();
	}
}
