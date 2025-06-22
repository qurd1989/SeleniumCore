package com.qa.automationexercise.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automationexercise.utils.ElementUtils;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eleUtil;
	
	
	// 1. private By locators: page obbjects
 	private By username = By.xpath("(//*[@name='email'])[1]");
 	private By password = By.xpath("//*[@name='password']");
 	private By loginBtn = By.xpath("(//*[contains(@class,'btn btn-default')])[1]");
 	private By forgotPwdLink = By.xpath("");
 	private By logo = By.xpath("//*[contains(@src,'/static/images/home/logo.png')]");
 	private By loginandsignupTap = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a/text()");
 
 	//2. Public Page Constants
 	public LoginPage(WebDriver driver) {
 		this.driver = driver;
 		this.eleUtil = new ElementUtils(driver);
 	}
 	
 	public boolean isOnLoginPage() {
 		
 			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(username)).isDisplayed();
	
 	}
 	
 	public void navigateToLoginPage() {
 		System.out.println("sgfsdfgsdfsdf " + isOnLoginPage());
 		if (!isOnLoginPage()) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(loginandsignupTap));
			driver.findElement(loginandsignupTap).click();
		}
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
 	
 	public boolean isLogoExist() {
 		return driver.findElement(logo).isDisplayed();
 	}
 	
 	public String doLogin(String userName, String pwd) {
 	//	navigateToLoginPage();
 		 
		String text = eleUtil.doElementGetAttribute(username, "placeholder");
		System.out.println(text + " New Text");
 		driver.findElement(username).clear();
 		driver.findElement(username).sendKeys(userName);
		driver.findElement(password).clear();
 		driver.findElement(password).sendKeys(pwd);;
 		driver.findElement(loginBtn).click();
 		String accountPageTitle = driver.getTitle();
 		System.out.println("Account Page Title: " + accountPageTitle);
 		return accountPageTitle;
 		
 	}
}
