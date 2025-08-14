package com.example.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.config.DriverFactoryExtended;

import io.qameta.allure.Allure;


public class TestBase implements HasLogger {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected String DOWNLOAD_DIR;

	protected void setup() {
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

	protected void saveScreenshot(TestInfo info)  {
		if (info.getTestClass().isPresent() || info.getTestMethod().isPresent()) {
			String filename = getScreenshotFilename(info.getTestClass().get().getSimpleName(), info.getTestMethod().get().getName());
			DriverFactoryExtended.saveScreenshot(filename);
		}
	}

	private String getScreenshotFilename(ExtensionContext context) {
		return context.getRequiredTestClass().getSimpleName() + "-" +
						  context.getRequiredTestMethod().getName() +
						  LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyyyMMdd-HHmmss"));
	}

	private String getScreenshotFilename(String className, String methodName) {
		return className + "-" +
			   methodName +
			   LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyyyMMdd-HHmmss"));
	}

	protected void printStep(String stepName) {
		Allure.step(stepName);
		getLogger().info("Step: " + stepName);
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

