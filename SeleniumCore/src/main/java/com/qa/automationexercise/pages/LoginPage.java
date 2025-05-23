package com.qa.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver driver;
	// 1. private By locators: page obbjects
 	private By username = By.xpath("(//*[@name='email'])[1]");
 	private By password = By.xpath("//*[@name='password']");
 	private By loginBtn = By.xpath("(//*[contains(@class,'btn btn-default')])[1]");
 	private By forgotPwdLink = By.xpath("");
 	private By logo = By.xpath("//*[contains(@src,'/static/images/home/logo.png')]");
 	
 	
 	//2. Public Page Constants
 	public LoginPage(WebDriver driver) {
 		this.driver = driver;
 	}
 	
 	// 3. Public Page Methods
 	public String getLoginTitle() {
 		String title = driver.getTitle();
 		System.out.println("login page title: " + title);
 		return title;
 	}
 	
 	public String getLoginPageURL() {
 		String url = driver.getCurrentUrl();
 		System.out.println(" URL is: " + url);
 		return url;
 	}
 	public boolean isForgotPwdLinkExist() {
 		return driver.findElement(forgotPwdLink).isDisplayed();
 	}
 	
 	public boolean isLogoExist() {
 		return driver.findElement(logo).isDisplayed();
 	}
 	
 	public String doLogin(String userName, String pwd) {
 		driver.findElement(username).sendKeys(userName);
 		driver.findElement(password).sendKeys(pwd);
 		driver.findElement(loginBtn).click();
 		String accountPageTitle = driver.getTitle();
 		System.out.println("Account Page Title: " + accountPageTitle);
 		return driver.getTitle();
 		
 	}
}
