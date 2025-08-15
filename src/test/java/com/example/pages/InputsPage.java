package com.example.pages;

import org.openqa.selenium.*;

public class InputsPage extends BasePage {
	private final By input = By.tagName("input");

	public InputsPage(WebDriver driver) { super(driver); }

	public void open() { driver.get("https://the-internet.herokuapp.com/inputs"); }

	public void typeNumber(String value) { clearAndType(input, value); }

	public String value() { return driver.findElement(input).getAttribute("value"); }
}