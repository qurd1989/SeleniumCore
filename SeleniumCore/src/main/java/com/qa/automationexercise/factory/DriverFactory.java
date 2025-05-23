package com.qa.automationexercise .factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automationexercise.exceptions.BrowserExeption;
import com.qa.automationexercise.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	  Properties prop;
	
	  public DriverFactory() {}

	    public static WebDriver getDriver() {
	        return driver.get();
	    }
	    /**
	     * this method is used to init the driver on the basis of given browsername
	     * @param browsername
	     * @returnit returns driver
	     * @throws NumberFormatException
	     * @throws Exception
	     */
	    public WebDriver initDriver(Properties prop) {
	    	
	    	String  browsername = prop.getProperty("browser");
	    	
	        

	        switch (browsername.toLowerCase().trim()) {
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                driver.set(new ChromeDriver());
	                break;
	            case "edge":
	            	WebDriverManager.edgedriver().setup();
	                driver.set(new EdgeDriver());
	                break;
	            default:
	                throw new BrowserExeption("Unsupported browser: " + browsername);
	        }

	        getDriver().manage().window().maximize();
	        try {
				getDriver(). manage().timeouts().implicitlyWait(Duration.ofSeconds(
				        Long.parseLong(ConfigReader.get("impnlicitWait"))));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        getDriver().get(prop.getProperty("url"));
	        
	        return  driver.get();
	    }
	    
	    /**
	     * this methid is used to init properties the config file 
	     * @return
	     */
	    public Properties initProp()  {
	    	prop = new Properties();
	    	try {
				FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
				prop.load(ip);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return prop;
	    }
	    
	    public static void quitDriver() {
	        if (driver.get() != null) {
	            getDriver().quit();
	            driver.remove(); // avoid memory leaks
	        }
	    }
}