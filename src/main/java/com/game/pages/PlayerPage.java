package com.game.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlayerPage extends BasePage {
	AppiumDriver driver;
	String playerName;
	private static Logger log = LogManager
			.getLogger(PlayerPage.class.getName());

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Chris Paul']")
	public static WebElement playerSelect;

	public PlayerPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public Boolean getPlayerTitle(Map<Object, Object> dataMap)
			throws InterruptedException {
		try {

			addExplicitWait(playerSelect, driver);
			playerName = dataMap.get("PlayerName").toString();
		} catch (NullPointerException exception) {
			log.error(" Testing value from Map is Null "
					+ exception.getMessage());
			exception.printStackTrace();
		}
		return getText(playerSelect, playerName, driver);

	}

}
