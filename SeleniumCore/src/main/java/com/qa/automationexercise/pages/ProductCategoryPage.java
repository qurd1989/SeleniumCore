package com.qa.automationexercise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductCategoryPage {

	 private WebDriver driver;

	    private By header = By.xpath("//h1[text()='Women']");

	    public ProductCategoryPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public boolean isHeaderDisplayed() {
	        return driver.findElement(header).isDisplayed();
	    }

	
}
