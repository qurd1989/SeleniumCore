package com.qa.automationexercise.pages;

import org.openqa.selenium.WebDriver;

import com.qa.automationexercise.utils.ElementUtils;

public class ProductPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(driver);
	}
	
	
}
