/*
 * 
 */
package main.java.com.dbyl.appiumCore.pageActions;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

/**
 * The Class SignInPage.
 *
 * @author young
 */
public class SignInPage {

	/** The driver. */
	IOSDriver<?> driver;

	/** The sign in view. */
	@iOSFindBy(id = "Ruby_China.SignInView")
	IOSElement signInView;

	/** The cancel. */
	@iOSFindBy(id = "Cancel")
	IOSElement cancel;

	/** The sign up. */
	@iOSFindBy(id = "Sign up")
	IOSElement signUp;

	/** The user name. */
	@iOSFindBy(className = "XCUIElementTypeTextField")
	IOSElement userName;

	/** The password. */
	@iOSFindBy(className = "XCUIElementTypeSecureTextField")
	IOSElement passwordTextField;

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	public IOSDriver<?> getDriver() {
		return driver;
	}

	/**
	 * Sets the driver.
	 *
	 * @param driver
	 *            the new driver
	 */
	public void setDriver(IOSDriver<?> driver) {
		this.driver = driver;
	}

	/**
	 * Instantiates a new sign in page.
	 *
	 * @param driver
	 *            the driver
	 */
	public SignInPage(IOSDriver<?> driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/**
	 * Checks if is sign in page present.
	 *
	 * @return true, if is sign in page present
	 */
	public boolean isSignInPagePresent() {
		return signInView.isDisplayed();
	}

	/**
	 * Type user name.
	 *
	 * @param name
	 *            the name
	 */
	public void typeUserName(String name) {
		userName.clear();
		userName.sendKeys(name);
	}

	/**
	 * Type password.
	 *
	 * @param password
	 *            the password
	 */
	public void typePassword(String password) {
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
	}

}
