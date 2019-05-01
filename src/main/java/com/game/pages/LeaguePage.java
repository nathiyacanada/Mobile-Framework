package com.game.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LeaguePage extends BasePage {

	 TouchAction touchAction;
	 AppiumDriver driver;
	private static Logger log = LogManager
			.getLogger(LeaguePage.class.getName());

	@AndroidFindBy(id = "com.fivemobile.thescore:id/icon")
	public static List<WebElement> leagueSelect;

	@AndroidFindBy(id = "com.fivemobile.thescore:id/img_score_logo")
	public static List<WebElement> theScoreTitle;
	private WebElement leagueOption;

	public LeaguePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		touchAction = new TouchAction(driver);
	}

	public void selectLeagueTap() {
		log.info("Inside the LeaguePage : selectLeagueTap Method ");
		addExplicitWaitList(leagueSelect, driver);
		leagueOption = leagueSelect.get(4);
		tapByElement(touchAction, leagueOption, driver);
		selectGameOption();
		log.info("Exit the selectLeagueTap Method ");
	}

	public void selectGameOption() {
		try {
			log.info("Inside the selectGameOption Method ");
			WebElement selectNbaBasket = driver
					.findElement(MobileBy
							.AndroidUIAutomator("UiSelector().text(\"NBA Basketball\")"));
			tapByElement(touchAction, selectNbaBasket, driver);
			log.info("Exit the selectGameOption Method ");
		} catch (NoSuchElementException exception) {
			log.error("Element not found Exception ");
			exception.printStackTrace();
		}

	}

}
