package com.example.steps;

import org.assertj.core.api.Assertions;

import com.example.pages.*;
import com.example.utils.TestBase;

import io.cucumber.java.en.*;

public class LogoutSteps extends TestBase {

	private LoginPage loginPage;
	private SecureAreaPage secureAreaPage;

	public LogoutSteps() {
	}

	@When("the user clicks logout")
	public void the_user_clicks_logout() {
		secureAreaPage = new SecureAreaPage(driver);
		Assertions.assertThat(secureAreaPage.isLoaded()).isTrue();
		secureAreaPage.clickLogout();
	}

	@Then("the login page is displayed again")
	public void the_login_page_is_displayed_again() {
		loginPage = new LoginPage(driver);
		Assertions.assertThat(loginPage.isFormVisible()).isTrue();
	}

	@Then("a message is shown containing {string}")
	public void a_message_is_shown_containing(String expected) {
		String flash = new LoginPage(driver).getFlashMessage();
		Assertions.assertThat(flash).contains(expected);
	}
}