package com.qa.automationexercise.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


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
public class TestRunner extends AbstractTestNGCucumberTests{

}
