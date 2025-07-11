package com.qa.automationexercise.tests;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;


public class TestCasesPageTest extends BaseTest {

	
	@BeforeClass
	public void testCasesSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		testcasesPage = accPage.navigateToTestCasesPage();
	}
	
	@Test(priority = 1)
	public void verifyTestCasesHeaderisDisplayedTest() {
	    Assert.assertEquals(testcasesPage.getTestCaseHeaderText(), "Below is the list of test Cases for you to practice the Automation. Click on the scenario for detailed Test Steps:");	
	}
	
	@Test(priority = 2)
	public void verifyTestCasesTabActiveStatusTest() {
		String actualStyle = testcasesPage.getTestCasesTabActiveStatus("Test Cases");
		Assert.assertEquals(actualStyle.trim() ,"color: orange;");
	}
	
	@Test(priority = 3)
	public void verifyTestCasesFieldTextTest() {
		String expectedText = "Test Cases";
		Assert.assertTrue(testcasesPage.getTestCasesFieldText().trim().equalsIgnoreCase(expectedText));
	}
	
	
	@Test(priority = 4)
	public void verifyTestCasesHyperLinkTextTest() {
		Map<String, String>  actTestCasesData = testcasesPage.getTestCasesData();
		softAssert.assertEquals(actTestCasesData.get("Test Case 1"), "Register User");
		softAssert.assertEquals(actTestCasesData.get("Test Case 2"), "Login User with correct email and password");
		softAssert.assertEquals(actTestCasesData.get("Test Case 3"), "Login User with incorrect email and password");
		softAssert.assertEquals(actTestCasesData.get("Test Case 4"), "Logout User");
		softAssert.assertAll();
	}
	
}
