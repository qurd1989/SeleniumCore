package com.qa.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;

import io.cucumber.java.Before;


public class SignUpPageTest extends BaseTest{
	
	@BeforeMethod
	public void signUpSetup() {
		signUpPage = loginPage.navigateToSignUpPage();
	}
	
	public String getRandomEmail() { 
		return "uiautomation" + System.currentTimeMillis()+"@open.com";
	}
	@Test
	public void userSignUpTest() {
		Assert.assertTrue(signUpPage.userSignUp(getRandomEmail(), "elmar"));
	}
}
