package com.game.pages;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private static Logger log = LogManager.getLogger(BasePage.class.getName());

	public void tapByCoordinates(TouchAction touchAction, int x, int y) {
		try {
			log.debug("Successfully entered into the BasePage : tapByCoordinates Method ");
			touchAction.tap(PointOption.point(x, y)).perform();
			log.info("Successfully excited the BasePage : tapByCoordinates Method ");
		} catch (NoSuchElementException e) {
			log.error("Unable to locate the element in BasePage  "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void tapByElement(TouchAction touchAction, WebElement webElement,
			AppiumDriver driver) {
		try {
			log.debug("Successfully entered into the BasePage : tapByElement Method ");
			addExplicitWait(webElement, driver);
			touchAction.tap(tapOptions().withElement(element(webElement)))
					.perform();
			log.debug("Successfully executed the BasePage : tapByElement Method ");
		} catch (NoSuchElementException exception) {
			log.error("Unable to locate the element BasePage " + webElement);
			exception.printStackTrace();

		}
	}

	public void sendKeys(WebElement locatorName, String value,
			AppiumDriver driver) {
		try {
			log.debug("Successfully entered into the BasePage : sendKeys Method ");
			addExplicitWait(locatorName, driver);
			locatorName.clear();
			locatorName.sendKeys(value);
			log.info("User is able to enter" + value + " into " + locatorName
					+ " Field successfully ");
			log.debug("Successfully excited the BasePage : sendKeys Method ");

		} catch (NoSuchElementException e) {
			log.info("Not able to identify " + locatorName + " element ");
			e.printStackTrace();
		}
	}

	public Boolean getText(WebElement locatorName, String value,
			AppiumDriver driver) {
		try {
			log.debug("Successfully entered into the BasePage: getText Method ");
			addExplicitWait(locatorName, driver);
			log.info("Text value from the locator element is : "
					+ locatorName.getText());
			log.info("Testing  value " + value);
			if (locatorName.getText().contains(value)) {
				return true;
			}
		} catch (NoSuchElementException e) {
			log.error("Not able to identify Webelement " + locatorName);
		} catch (NullPointerException e) {
			log.error("The WebElement " + locatorName + " / test value "
					+ value + " is null");
		}
		log.debug("Successfully excited the BasePage: getText Method ");
		return false;
	}

	public void addExplicitWait(WebElement locatorName, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf(locatorName));

	}

	public void addExplicitWaitList(List<WebElement> locatorName,
			AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOfAllElements(locatorName));
	}
}