package com.qa.automationexercise.tests;

import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.automationexercise.base.BaseTest;
import com.qa.automationexercise.pages.ProductCategoryPage;
import com.qa.automationexercise.pages.ProductPage;

public class ProductCategoryPageTest extends BaseTest {

	 private ProductPage productPage;
	   private ProductCategoryPage productCategoryPage;

	    @BeforeClass
	    public void setup() {
	    	accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	        productPage = accPage.navigateToProductPage();
	        productCategoryPage = productPage.navigateToProductCategory();
	    }

	    @Test
	    public void verifyWomenHeaderIsDisplayed() {
	        Assert.assertTrue(productCategoryPage.isHeaderDisplayed());
	    }
	}
	
