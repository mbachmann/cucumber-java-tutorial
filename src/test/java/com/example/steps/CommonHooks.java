package com.example.steps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;

import com.example.config.DriverFactoryExtended;
import com.example.utils.HasLogger;

import io.cucumber.java.*;

public class CommonHooks implements HasLogger {

	protected WebDriver driver;

	@Before
	public void beforeScenario(Scenario scenario) {
		// driver = DriverFactoryExtended.initDriver("chrome", "http://localhost:4444/wd/hub");
		// driver = DriverFactoryExtended.initDriver("firefox", "");
		driver = DriverFactoryExtended.initDriver();
		getLogger().debug("Before Scenario: {}", scenario.getName());
	}

	@After
	public void afterSenario(Scenario scenario) {

		getLogger().debug("After Scenario: {}", scenario.getName());

		if (scenario.isFailed()) {
			DriverFactoryExtended.printBrowserLogs();
			String filename = getScreenshotFilename(scenario.getName());
			DriverFactoryExtended.saveScreenshot(filename);
		}
		DriverFactoryExtended.quitDriver();
	}

	@AfterAll
	public static void afterFeature() {
		// This method can be used for cleanup after all tests have run, if needed.
		DriverFactoryExtended.quitDriverAndService();
	}

	private String getScreenshotFilename(String name) {
		return name +
			   LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyyyMMdd-HHmmss"));
	}
}
