package com.qa.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;
import com.qa.automationexercise.constants.AppConstants;
import com.qa.automationexercise.utils.ExcelUtil;

public class SignUpPageTest extends BaseTest {

	@BeforeMethod
	public void signUpSetup() {
		signUpPage = loginPage.navigateToSignUpPage();
	}

	@DataProvider
	public Object[][] userCredentials() {
		return new Object[][] { { "1auiautomation", "edu" }, { "2auiautomation", "eduhard" },
				{ "3auiautomation", "edusoft" } };
	}

	//method below ONLY works with  valid Excel file 
	/*
	 * @DataProvider public Object[][] getSignUp() { return
	 * ExcelUtil.getTestData(AppConstants.SIGNUP_SHEET_NAME); }
	 */

	public String getRandomEmail(String preFix) {
		return preFix + System.currentTimeMillis() + "@gmail.com";
	}

	@Test(dataProvider = "userCredentials")
	public void userSignUpTest(String preFix, String name) {
		String email = getRandomEmail(preFix);
		System.out.println("Generated: " + email + " | Name " + name);
		Assert.assertTrue(signUpPage.userSignUp(email, name));
	}
}
