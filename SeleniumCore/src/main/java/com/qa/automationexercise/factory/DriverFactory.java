package com.qa.automationexercise .factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.automationexercise.errors.AppError;
import com.qa.automationexercise.exceptions.BrowserExeption;
import com.qa.automationexercise.exceptions.FrameworkException;
import com.qa.automationexercise.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	  Properties prop;
	  public static String isEleHighlight;
	
	  public DriverFactory() {}

	    public static WebDriver getDriver() {
	        return driver.get();
	    }
	    /**
	     * this method is used to init the driver on the basis of given browsername
	     * @param prop 
	     * @returnit returns driver
	     * @throws NumberFormatException
	     * @throws Exception
	     */
	    public WebDriver initDriver(Properties prop) {
	    	
	    	String  browsername = prop.getProperty("browser");
			isEleHighlight = prop.getProperty("highlight");
	        switch (browsername.toLowerCase().trim()) {
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                driver.set(new ChromeDriver());
	                break;
	            case "edge":
	            	WebDriverManager.edgedriver().setup();
	                driver.set(new EdgeDriver());
	                break;
	            case "firefox":
	            	WebDriverManager.firefoxdriver().setup();
	            	driver.set(new FirefoxDriver());
	            	break;
	            default:
	            	System.out.println(AppError.INVALID_BROWSER_MSG + browsername + " is Invalid");
	                throw new BrowserExeption(AppError.INVALID_BROWSER_MSG);
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
	     * this method is used to init properties the config file 
	     * @return
	     * @throws FileNotFoundException 
	     */
	    public Properties initProp() throws FileNotFoundException  { 
	    	prop = new Properties();
			FileInputStream ip = null;

	    	String envName = System.getProperty("env");
	    	System.out.println("running test on env " + envName);
	    	
	    	if (envName == null) {
				System.out.println("env is null.... hence running test on QA env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}else {
                ip = switch (envName.toLowerCase().trim()) {
                    case "qa" -> new FileInputStream("./src/test/resources/config/qa.config.properties");
                    case "dev" -> new FileInputStream("./src/test/resources/config/dev.config.properties");
                    case "uat" -> new FileInputStream("./src/test/resources/config/uat.config.properties");
                    case "stage" -> new FileInputStream("./src/test/resources/config/config.properties");
                    default -> {
                        System.out.println("please right env name... " + envName);
                        throw new FrameworkException("INVALID ENV NAME");
                    }
                };
			}	
	    	
	    	try {
				prop.load(ip);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
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