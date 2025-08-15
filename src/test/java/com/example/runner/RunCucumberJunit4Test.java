package com.example.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;

/**
 * JUnit4 Cucumber Runner
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "summary", "html:target/selenium.html", "json:target/selenium-json-report.json","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" },
		tags = "@smoke or @regression",
		features = {
				"src/test/resources/features/login.feature",
				"src/test/resources/features/logout.feature",
				"src/test/resources/features/forgot_password.feature",
				"src/test/resources/features/login_datatable.feature",
				"src/test/resources/features/inputs_money_paramtype.feature",
				"src/test/resources/features/checkboxes_datatable.feature",
				"src/test/resources/features/login_outline.feature"
		},
		monochrome = true,
		glue = {"com.example.steps"}
)
public class RunCucumberJunit4Test {
}
