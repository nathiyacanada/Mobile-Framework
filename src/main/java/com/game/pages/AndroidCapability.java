package com.game.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidCapability {
	DesiredCapabilities cap;
	private static Logger log=LogManager.getLogger(AndroidCapability.class.getName());
	public AndroidDriver<AndroidElement> capabilitiesForAndroid()  
	{
	 AndroidDriver<AndroidElement> driver = null;
	 File appDir = new File("src/main/resources");
     File app = new File(appDir, "theScore Live Sports Scores News Stats Videos_v19.7.1_apkpure.com.apk");
     cap = new DesiredCapabilities();
     cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
     cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
     
	try {
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	} 
	catch (MalformedURLException exception) {
	  log.error("Error connecting to server" +exception.getMessage());
		exception.printStackTrace();
	}
	catch (SessionNotCreatedException exception) {
		  log.error("Not able to create Session " + exception.getMessage());
			exception.printStackTrace();
		}
	catch (WebDriverException exception) {
		  log.error("Connection Refused : " + exception.getMessage());
		  exception.printStackTrace();
		}
	catch (Exception exception) {
		  log.error("Exception : "+ exception.getMessage());
		  exception.printStackTrace();
		}
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
	}
}
