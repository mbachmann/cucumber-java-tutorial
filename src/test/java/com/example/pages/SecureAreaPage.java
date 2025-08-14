package com.example.pages;

import org.openqa.selenium.*;

public class SecureAreaPage extends BasePage {

	private final By header = By.cssSelector("div.example h2"); // "Secure Area"
	private final By logoutButton = By.cssSelector("a.button.secondary.radius");
	private final By flash = By.id("flash");

	public SecureAreaPage(WebDriver driver) {
		super(driver);
	}

	public boolean isLoaded() {
		return isVisible(header);
	}

	public void clickLogout() {
		jsClick(logoutButton);
	}

	public String getFlashMessage() {
		return getText(flash);
	}
}