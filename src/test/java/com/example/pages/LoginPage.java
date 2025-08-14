package com.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage extends BasePage {

	@FindBy(id = "username")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(css = "button.radius")
	private WebElement loginButton;

	@FindBy(css = ".flash.success")
	private WebElement successMessage;

	@FindBy(css = ".example h2")
	private WebElement header;

	private final By flash = By.id("flash");
	private final By form = By.id("login");

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void open(String baseUrl) {
		driver.get(baseUrl);
	}

	public void login(String user, String pass) {
		usernameInput.sendKeys(user);
		setValue(driver, passwordInput, pass);
		loginButton.click();
	}

	public String getSuccessMessage() {
		return successMessage.getText();
	}

	public String getHeader() {
		try {
			return header.getText();
		} catch (StaleElementReferenceException e) {
			PageFactory.initElements(driver, this);
			return header.getText();
		}

	}

	public boolean isFormVisible() {
		return isVisible(form);
	}

	public String getFlashMessage() {
		return getText(flash);
	}

	public boolean isOnLoginPage() {
		return usernameInput.isDisplayed() && passwordInput.isDisplayed();
	}
}
