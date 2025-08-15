package com.example.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.example.utils.OsCheck;

public abstract class BasePage {
	protected final WebDriver driver;
	private final WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	protected boolean isVisible(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	protected String getText(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator).getText().trim();
	}

	protected void jsClick(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement el = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	protected void clearAndType(By locator, String text) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement el = driver.findElement(locator);
		el.clear();
		setValue(driver,  el, text);
	}

	/**
	 * Set the value of a WebElement using sendKeys() or JavaScript.

	 * @param element input element
	 * @param value the value to set
	 */
	public void setValue(WebDriver driver,  WebElement element, String value) {
		try {
			// First try to set the value using sendKeys)
			element.clear();
			element.sendKeys(value);

			// check if the value was set correctly
			if (!value.equals(element.getAttribute("value"))) {
				throw new RuntimeException("sendKeys hat nicht den gewünschten Wert gesetzt.");
			}

		} catch (Exception e) {
			// Fallback to JavaScript if sendKeys fails or does not set the value correctly
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].value = arguments[1];" +
					"arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
					"arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
					element, value
			);
		}
	}

	/**
	 * Paste text into a WebElement using the clipboard or JavaScript. Not pasting in headless mode.
	 * Instead, it sets the value directly via JavaScript.
	 *
	 * @param driver the WebDriver instance
	 * @param element the WebElement to paste text into
	 * @param text the text to paste
	 */
	protected void pasteText(WebDriver driver, WebElement element, String text) {

		boolean headless = ((JavascriptExecutor) driver)
								   .executeScript("return navigator.userAgent.toLowerCase().includes('headless')") != null;
		if (!headless) {
			// copy Text in Clipboard
			Toolkit.getDefaultToolkit().getSystemClipboard()
				   .setContents(new StringSelection(text), null);

			// Focus to Element and paste
			element.click();
			if (OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows) {
				element.sendKeys(Keys.CONTROL, "v"); // CTRL + V auf Windows
			} else if (OsCheck.getOperatingSystemType() == OsCheck.OSType.Linux) {
				element.sendKeys(Keys.CONTROL, "v"); // CTRL + V auf Linux
			} else if (OsCheck.getOperatingSystemType() == OsCheck.OSType.MacOS) {
				element.sendKeys(Keys.COMMAND, "v"); // CMD + V auf macOS
			}
		} else {
			// Headless- oder Fallback-Option: Set value directly via JavaScript
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].value = arguments[1];", element, text);
		}
	}

}
