package com.example.runner;

import org.junit.platform.suite.api.*;

import io.cucumber.junit.platform.engine.Constants;

/**
 * @Suite - annotation from JUnit 5 to make this class a run configuration for test suite.
 * @IncludeEngines("cucumber") - tells JUnit 5 to use Cucumber test engine to run features.
 * @SelectClasspathResource("features") - to change the location of your feature files (if you do not add this annotation classpath of the current class will be used).
 * @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.cucumber") - this annotation specifies the path to steps and config files - i.e. the top package name.
 * @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/selenium.html") - this annotation specifies the path to any plugin.
 */

@Suite
@IncludeEngines("cucumber")
// @SelectClasspathResource("features")
@SelectClasspathResource("features/login.feature")
@SelectClasspathResource("features/logout.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "com.example.steps")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME,value = "@smoke or @regression")
@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME,value = "false")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,
		value = "pretty, html:target/selenium.html, "
				+ "json:target/selenium-json-report.json, "
				+ "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class RunCucumberJunit5Test {
}
