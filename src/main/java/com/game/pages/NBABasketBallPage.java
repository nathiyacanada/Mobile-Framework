package com.game.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NBABasketBallPage extends BasePage {
	AppiumDriver driver;
	String leagueTitle;
	private static Logger log = LogManager.getLogger(NBABasketBallPage.class
			.getName());
	@AndroidFindBy(id = "com.fivemobile.thescore:id/title_text")
	public static WebElement nbaBasket;

	public NBABasketBallPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public Boolean checkBasketBallTitle(Map<Object, Object> dataMap) {
		log.info("Inside the checkBasketBallTitle ");
		try {
			leagueTitle = dataMap.get("LeagueTitle").toString();
		} catch (NullPointerException exception) {
			log.error(" Testing value from Map is Null "
					+ exception.getMessage());
			exception.printStackTrace();
		}
		return getText(nbaBasket, leagueTitle, driver);
	}
}
