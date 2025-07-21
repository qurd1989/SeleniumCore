package com.qa.automationexercise.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.automationexercise.factory.DriverFactory;
import com.qa.automationexercise.pages.AccountPage;
import com.qa.automationexercise.pages.LoginPage;
import com.qa.automationexercise.pages.ProductCategoryPage;
import com.qa.automationexercise.pages.ProductPage;
import com.qa.automationexercise.pages.SignUpPage;
import com.qa.automationexercise.pages.TestCasesPage;

public class BaseTest {
	DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected TestCasesPage testcasesPage;
	protected ProductPage productPage;
	protected SignUpPage signUpPage;
	protected ProductCategoryPage productCategoryPage;
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("chrome") String browserName) throws NumberFormatException, Exception {
		df = new DriverFactory();
		prop = df.initProp();
		
		//check if browser param comes from testng.xml
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
