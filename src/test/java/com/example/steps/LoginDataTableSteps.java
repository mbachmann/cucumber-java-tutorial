package com.example.steps;

import java.util.*;

import org.assertj.core.api.Assertions;

import com.example.pages.LoginPage;
import com.example.utils.TestBase;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class LoginDataTableSteps extends TestBase {


private LoginPage login;
private final List<String> actualMessages = new ArrayList<>();
private List<Map<String,String>> attempts;

private final String url = "https://the-internet.herokuapp.com/login";

public LoginDataTableSteps() {
	login = new LoginPage(driver);
}

@Given("this user is on the login page")
public void onLoginPage() {
	robustGet(url);
	// login.open(url);
	Assertions.assertThat(login.isFormVisible()).isTrue();
}

@When("this user tries to login with:")
public void theUserTriesToLoginWith(DataTable table) {
	attempts = table.asMaps(String.class, String.class);
	for (Map<String,String> row : attempts) {
		String u = row.get("username");
		String p = row.get("password");
		login.login(u, p);
		actualMessages.add(login.getFlashMessage());
		if (login.getFlashMessage().contains("secure")) {
			login.logout();
		}
	}
}

@Then("all login attempts produced the expected messages")
public void allLoginAttemptsProducedTheExpectedMessages() {
	for (int i = 0; i < attempts.size(); i++) {
		String expected = attempts.get(i).get("expectedMessage");
		Assertions.assertThat(actualMessages.get(i)).contains(expected);
	}
}
}