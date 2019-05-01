package com.game.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.game.pages.AndroidCapability;
import com.game.pages.LeaderPage;
import com.game.pages.LeaguePage;
import com.game.pages.NBABasketBallPage;
import com.game.pages.PlayerPage;
import com.game.pages.ReadFromExcel;
import com.game.pages.SigninPage;
import com.game.pages.WelcomePage;

public class RandomPlayer_TC001 extends AndroidCapability {

	AndroidDriver<AndroidElement> driver;
	private String testDataFileName = "TheScoreApp.xlsx";
	private String testDataLocation = "src/main/resources";
	private static Logger log = LogManager.getLogger(RandomPlayer_TC001.class
			.getName());

	@BeforeClass
	public void beforeClass() {
		log.info("inside BeforeClass Method");
		driver = capabilitiesForAndroid();
		log.info("SessionId Is " + driver.getSessionId());
	}

	@Test(dataProvider = "data")
	public void RandomPlayer_TestCase001(Map<Object, Object> dataMap)
			throws InterruptedException {
		log.info("Inside Selecting Random Player Testcase");
		new WelcomePage(driver).clickSigninLink();
		new SigninPage(driver).signIn(dataMap);
		new LeaguePage(driver).selectLeagueTap();
		Boolean title = new NBABasketBallPage(driver)
				.checkBasketBallTitle(dataMap);
		Assert.assertTrue(title);
		new LeaderPage(driver).selectLeaderTap();
		Boolean playerName = new PlayerPage(driver).getPlayerTitle(dataMap);
		Assert.assertTrue(playerName);
	}

	@DataProvider(name = "data")
	public Object[][] dataSupplier() {
		return new ReadFromExcel().readData(testDataLocation, testDataFileName);
	}
}