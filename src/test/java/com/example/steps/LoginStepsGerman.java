package com.example.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.config.DriverFactoryExtended;
import com.example.pages.LoginPage;

import io.cucumber.java.de.*;
import io.cucumber.java.en.When;

public class LoginStepsGerman {


	private final LoginPage loginPage;
	private final String url = "https://the-internet.herokuapp.com/login";
	private final WebDriver driver;

	public LoginStepsGerman() {
		driver = DriverFactoryExtended.getDriver();
		loginPage = new LoginPage(driver);
	}

	@Angenommen("Benutzer ist auf der Login-Seite")
	public void userOnLoginPage() {
		driver.get(url);
		Assertions.assertThat(loginPage.isOnLoginPage()).isTrue();
	}

	@When("Benutzer {string} mit Passwort {string} einloggt")
	public void userLogsIn(String username, String password) {
		loginPage.login(username, password);
	}

	@Dann("wird das Dashboard angezeigt")
	public void dashboardShown() {
		// On the demo site it is called Secure Area
		Assertions.assertThat(loginPage.getHeader()).containsIgnoringCase("Secure Area");
		Assertions.assertThat(loginPage.getSuccessMessage()).contains("You logged into a secure area!");
		// DriverFactoryExtended.quitDriverAndService();
	}
}