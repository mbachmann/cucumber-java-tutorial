package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.*;

public class ForgotPasswordPage extends BasePage {

	private final By emailInput = By.id("email");
	private final By submitButton = By.id("form_submit");
	private final By content = By.id("content");

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}

	public boolean isLoaded() {
		return isVisible(emailInput);
	}

	public void requestReset(String email) {
		driver.findElement(emailInput).clear();
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(submitButton).click();
	}

	public String getConfirmation() {
		return getText(content);
	}
}