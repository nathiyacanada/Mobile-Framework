package com.game.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LeaderPage extends BasePage {
	TouchAction touchAction;
	AppiumDriver driver;
	WebElement leaderSelect;
	private static Logger log = LogManager
			.getLogger(LeaderPage.class.getName());

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='LEADERS']")
	public static WebElement leaderTap;

	public LeaderPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		touchAction = new TouchAction(driver);
	}

	public void selectLeaderTap() throws InterruptedException {
		log.info("Inside LeaderPage : selectLeaderTap Method ");
		tapByElement(touchAction, leaderTap, driver);
		selectPlayer();
		log.info("Successful Exit of the LeaderPage : selectLeaderTap Method ");
	}

	public void selectPlayer() {
		log.info("Inside LeaderPage :selectPlayer Method ");
		try {
			driver.findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(textContains(\"Paul\"))"));
			leaderSelect = driver
					.findElement(MobileBy
							.AndroidUIAutomator("new UiSelector().textContains(\"Paul\")"));
			addExplicitWait(leaderSelect, driver);
			tapByElement(touchAction, leaderSelect, driver);
			log.info("Successful Exit of the LeaderPage : selectPlayer Method ");
		} catch (NoSuchElementException exception) {
			log.error("Element not found Exception " + leaderSelect);
			exception.printStackTrace();
		}
	}

}
