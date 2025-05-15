package example.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import example.utils.ConfigReader;

public class DriverFactory {

	  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	  private DriverFactory() {}

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void initDriver() throws NumberFormatException, Exception {
	    	if (driver != null) {
				return;
			}
	        String browser = ConfigReader.get("browser").toLowerCase();

	        switch (browser) {
	            case "chrome":
	                WebDriverManager.chromedriver().setup();
	                driver.set(new ChromeDriver());
	                break;
	            case "edge":
	            	WebDriverManager.edgedriver().setup();
	                driver.set(new EdgeDriver());
	                break;
	            default:
	                throw new RuntimeException("Unsupported browser: " + browser);
	        }

	        getDriver().manage().window().maximize();
	        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
	                Long.parseLong(ConfigReader.get("implicitWait"))));
	    }

	    public static void quitDriver() {
	        if (driver.get() != null) {
	            getDriver().quit();
	            driver.remove(); // avoid memory leaks
	        }
	    }
}