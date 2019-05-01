package com.game.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BasePage {

	TouchAction touchAction;
	AppiumDriver driver;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='HAVE AN ACCOUNT? SIGN IN']")
	public static WebElement signInElement;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='HAVE AN ACCOUNT? SIGN IN']")
	public static MobileElement pointsForSignIn;

	public WelcomePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		touchAction = new TouchAction(driver);
	}

	public void clickSigninLink() {
		int x = signInElement.getSize().getWidth();
		int y = pointsForSignIn.getCenter().getY();
		tapByCoordinates(touchAction, x, y);
	}

}
