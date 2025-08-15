package com.example.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pages.InputsPage;
import com.example.utils.TestBase;

public class InputsMoneySteps extends TestBase {

	private InputsPage page;

	@Given("the user is on the numeric inputs page")
	public void onNumericInputsPage() {
		page = new InputsPage(driver);
		page.open();
	}

	@When("the user types amount {money}")
	public void theUserTypesAmount(Money money) {
		// Only numeric part goes into this page
		page.typeNumber(money.amount().toPlainString());
	}

	@Then("the input value should be {double}")
	public void theInputValueShouldBe(double expected) {
		assertThat(Double.parseDouble(page.value())).isEqualTo(expected);
	}
}