package com.example.steps;



import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pages.*;
import com.example.utils.TestBase;

public class CheckboxesDataTableSteps extends TestBase {

	private final String url = "https://the-internet.herokuapp.com/checkboxes";

	private CheckboxesPage page;

	@Given("the user is on the checkboxes page")
	public void onCheckboxesPage() {
		page = new CheckboxesPage(driver);
		robustGet(url);
	}

	@When("the user sets the checkboxes to:")
	public void theUserSetsTheCheckboxesTo(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			int index = Integer.parseInt(row.get("index"));
			boolean desired = new ParameterTypes().state(row.get("state"));
			page.setStateByIndex(index, desired);
		}
	}

	@Then("the checkboxes should be:")
	public void theCheckboxesShouldBe(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			int index = Integer.parseInt(row.get("index"));
			boolean expected = new ParameterTypes().state(row.get("state"));
			assertThat(page.isChecked(index)).isEqualTo(expected);
		}
	}
}