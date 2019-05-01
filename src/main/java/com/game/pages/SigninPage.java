package com.game.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SigninPage extends BasePage {

	String password;
	String userEmail;
	AppiumDriver driver;
	TouchAction touchAction;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Email Address']")
	public static WebElement emailId;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
	public static WebElement passwd;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in']")
	public static WebElement login;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Got it']")
	public static WebElement gotitpopup;

	private static Logger log = LogManager
			.getLogger(SigninPage.class.getName());

	public SigninPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		touchAction = new TouchAction(driver);
	}

	public void signIn(Map<Object, Object> datamap) throws InterruptedException {
		password = datamap.get("Password").toString();
		userEmail = datamap.get("UserEmail").toString();
		sendKeys(emailId, userEmail, driver);
		log.info("Username is entered succesfully");
		sendKeys(passwd, password, driver);
		log.info("Password is entered succesfully");
		tapByElement(touchAction, login, driver);
		log.info("Login performed successfully");
		addExplicitWait(gotitpopup, driver);
		if (gotitpopup.getText().contains("Got it"))
			gotitpopup.click();
		// Thread.sleep(3000);
	}

}
