package com.qa.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;

public class SignUpPageTest extends BaseTest{
	
	@BeforeMethod
	public void signUpSetup() {
		signUpPage = loginPage.navigateToSignUpPage();
	}
	@DataProvider
	public Object[][] userCredentials() {
		return new Object[][] {
			{"1auiautomation", "edu"},
			{"2auiautomation", "eduhard"},
			{"3auiautomation", "edusoft"}
		};
	}
		
	
	public String getRandomEmail(String preFix) { 
		return preFix + System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test(dataProvider = "userCredentials")
	public void userSignUpTest(String preFix, String name) {
		String email = getRandomEmail(preFix);
		System.out.println("Generated: " + email + " | Name " + name);
		Assert.assertTrue(signUpPage.userSignUp(email, name));
	}
}
 