package com.streebo.psumobile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	 public void getscreenshot(WebDriver driver) throws IOException, InterruptedException 
     {
		  File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		    File dest= new File("E:/Automationscreenshot/screenshot_"+timestamp()+".png");
		    FileUtils.copyFile(scr, dest);
		}

		public String timestamp() {
		    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		}

}
