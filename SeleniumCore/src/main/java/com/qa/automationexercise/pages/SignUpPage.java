package com.qa.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automationexercise.constants.AppConstants;
import com.qa.automationexercise.utils.ElementUtils;

public class SignUpPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

 	private By name = By.xpath("//*[@data-qa='signup-name']");
 	private By email = By.xpath("//*[@data-qa='signup-email']");
 	private By loginBtn = By.xpath("(//*[contains(@class,'btn btn-default')])[1]");
 	
 	private By signUp = By.xpath("//*[@data-qa='signup-button']");
 	private By loginandsignupTap = By.xpath("//*[@id='header']//*[contains(text(),'Signup / Login')]");
 	private By accountInformation = By.xpath("//*[contains(text(),'Enter Account Information')]");
 

 	public SignUpPage(WebDriver driver) {
 		this.driver = driver;
 		eleUtil = new ElementUtils(driver);
 	}
 	
 	public boolean userSignUp(String email, String name) {
 		eleUtil.waitForElementVisible(this.name, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(name);
 		eleUtil.doSendKeys(this.email, email);
 		eleUtil.doClick(signUp);
 		String accountInfomationText = eleUtil.getElementText(accountInformation); 
 		System.out.println(accountInfomationText + " dfsdfdsfdsf");
 				
 		if (accountInfomationText.contains(AppConstants.USER_ACCOUNT_INFORMATION)) {
 			
			return true;
		}else {
			return false;
		}
 	}
}
