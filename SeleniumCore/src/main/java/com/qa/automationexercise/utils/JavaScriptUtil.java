package com.qa.automationexercise.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void getPageTitle() {
        String title = js.executeScript("return document.title;").toString();
        System.out.println("Page Title: " + title);
    }
    public void getPageURL() {
        String url = js.executeScript("return document.URL;").toString();
        System.out.println("Page URL: " + url);
    }

    public void generateJsAlert(String message) {
        js.executeScript("alert('" + message + "');");
    }
    public String getPageInnerText() {
        return js.executeScript("return document.documentElement.innerText;").toString();
    }
    public void goBackWithJS() {
        js.executeScript("history.go(-1);");
    }

    public void goForwardWithJS() {
        js.executeScript("history.go(1)");
    }

    /**
     * example: zoomFirefox("1.5") will zoom the page to 150%
     * @param zoomLevel
     * note: this method is specific to Firefox, and we could optimize it by adding
     *  "document.body.style.transform =' scale(" + zoomLevel + ") '"  is used for Chrome and other browsers.
     */

    public void zoomFirefox(String zoomLevel) {
        String zoom  = "document.body.style.MozTransform =' scale(" + zoomLevel + ") '";
        js.executeScript(zoom);
    }

    public void scrollMiddlePageDown() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
    }

    public void scrollPageDown() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollPageUp() {
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }

    public void scrollIntoView(By locator) {
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public void scrollPagedown(String height) {
        js.executeScript("window.scrollBy(0," + height + ");");
    }

    public void drawBorder(By locator) {
        js.executeScript("arguments[0].style.border='3px solid red'", driver.findElement(locator));
    }

    /**
     * Flashes a web element by changing its background color to yellow temporarily
     * and then restoring its original style.
     *
     * @param locator The `By` locator used to find the web element to flash.
     *
     * Steps:
     * 1. Retrieves the original style of the web element.
     * 2. Changes the background color of the web element to yellow using JavaScript.
     * 3. Pauses execution for 1 second to make the flash visible.
     * 4. Restores the original style of the web element using JavaScript.
     */
    public void flash(WebElement element) {
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].style.backgroundColor = 'green'", element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    private void  changeBackgroundColor(By locator, String color) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", driver.findElement(locator));
    }
 }
