package com.qa.automationexercise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.automationexercise.base.BaseTest;


public class LoginPageTest extends BaseTest{

	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginTitle();
		Assert.assertEquals(actualTitle, "Automation Exercise - Signup / Login");
	}
	
	@Test
	public void loginPageURLTest() {
		String actualURl = loginPage.getLoginPageURL();
		System.out.println(actualURl);
		Assert.assertTrue(actualURl.contains("exercise"));
	}
	
	@Test
	public void logoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() throws InterruptedException {
		String actualAccountPageTitle = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(actualAccountPageTitle, "Automation Exercise");
		
	}
}
 