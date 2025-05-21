package com.qa.automationexercise.tests;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:parallel",
		glue = "stepDefinitions",
		plugin = {
				"pretty",
				"html:target/cucumber-reports.html",
				"json:target/cucumber-reports.json"
		},
		monochrome = true
		)
public class TestRunner {

}
