package com.example.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import com.example.config.DriverFactoryExtended;
import com.example.pages.LoginPage;

import io.cucumber.java.en.*;

public class LoginSteps {

	private final LoginPage loginPage;
	private final String url = "https://the-internet.herokuapp.com/login";
	private final WebDriver driver;

	public LoginSteps() {
		driver = DriverFactoryExtended.getDriver();
		loginPage = new LoginPage(driver);
	}

	@Given("the user is on the login page")
	public void theUserIsOnTheLoginPage(String url) {
		driver.get(url);
		Assertions.assertThat(loginPage.isOnLoginPage()).isTrue();
	}

	@When("the user logs in with username {string} and password {string}")
	public void theUserLogsInWithUsernameAndPassword(String username, String password) {
		loginPage.login(username, password);
	}

	@Then("the dashboard is displayed")
	public void theDashboardIsDisplayed() {
		// On the demo site it is called Secure Area
		Assertions.assertThat(loginPage.getHeader()).containsIgnoringCase("Secure Area");
		Assertions.assertThat(loginPage.getSuccessMessage()).contains("You logged into a secure area!");
		// DriverFactoryExtended.quitDriverAndService();
	}

	@Then("an error message is shown containing {string}")
	public void anErrorMessageIsShownContaining(String expected) {
		// Write code here that turns the phrase above into concrete actions
		String flash = loginPage.getFlashMessage();
		Assertions.assertThat(flash).contains(expected);
	}
}