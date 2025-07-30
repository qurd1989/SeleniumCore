package com.qa.automationexercise.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    /**
     * This method initializes ChromeOptions based on properties defined in the config file.
     * It sets options like headless mode, incognito mode, and other browser settings.
     */
    public ChromeOptions getChromeOptions() {
        chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            chromeOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            chromeOptions.addArguments("--incognito");
        }
        /*if (Boolean.parseBoolean(prop.getProperty("disable-notifications"))) {
            chromeOptions.addArguments("--disable-notifications");
        }
        if (Boolean.parseBoolean(prop.getProperty("disable-popup-blocking"))) {
            chromeOptions.addArguments("--disable-popup-blocking");
        }*/
        return chromeOptions;
    }

    /**
     * This method initializes FirefoxOptions based on properties defined in the config file.
     * It sets options like headless mode, incognito mode, and other browser settings.
     */
    public FirefoxOptions getFirefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            firefoxOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            firefoxOptions.addArguments("--private-window");
        }
        /*if (Boolean.parseBoolean(prop.getProperty("disable-notifications"))) {
            firefoxOptions.addArguments("--disable-notifications");
        }
        if (Boolean.parseBoolean(prop.getProperty("disable-popup-blocking"))) {
            firefoxOptions.addArguments("--disable-popup-blocking");
        }*/
        return firefoxOptions;
    }

    /**
     * This method initializes EdgeOptions based on properties defined in the config file.
     * It sets options like headless mode, incognito mode, and other browser settings.
     */
    public EdgeOptions getEdgeOptions() {
        edgeOptions = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            edgeOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            edgeOptions.addArguments("--incognito");
        }
        /*if (Boolean.parseBoolean(prop.getProperty("disable-notifications"))) {
            edgeOptions.addArguments("--disable-notifications");
        }
        if (Boolean.parseBoolean(prop.getProperty("disable-popup-blocking"))) {
            edgeOptions.addArguments("--disable-popup-blocking");
        }*/

    return edgeOptions;
    }
}
