package com.qa.automationexercise.pages;

import java.lang.annotation.ElementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automationexercise.utils.ElementUtils;

public class AccountPage {
	private WebDriver driver;
	private ElementUtils elementUtils;
	private By loggedInAs = By.xpath("//*[contains(text(), 'Logged in as')]");
	private By testCaseLink = By.xpath("//*[contains(text(), 'Logged in as')]");
	private By apisListForPraciteLink = By.xpath("//*[contains(text(), 'Logged in as')]");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new ElementUtils(driver);
	}
	
	 
}
