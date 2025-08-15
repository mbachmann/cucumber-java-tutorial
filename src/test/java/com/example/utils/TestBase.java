package com.example.utils;

import java.time.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.config.DriverFactoryExtended;


public class TestBase implements HasLogger {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected String DOWNLOAD_DIR;

	public TestBase() {
		driver = DriverFactoryExtended.getDriver();
		DOWNLOAD_DIR = DriverFactoryExtended.getDownloadDir();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	protected void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected void robustGet(String url) {
		try {
			driver.get(url);
		} catch (WebDriverException e) {
			// one quick retry
			try { driver.navigate().to(url); } catch (Exception ignored) {}
		}
		// small readiness check (doesn’t wait for all subresources)
		((JavascriptExecutor) driver).executeScript("return document.readyState");
	}
}

