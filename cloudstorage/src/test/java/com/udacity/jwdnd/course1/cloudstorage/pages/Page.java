package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;


    protected static int MAX_TIMEOUT = 60;
    protected static long TIMEOUT = 30;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(this.webDriver, MAX_TIMEOUT);
    }

    protected void clickOnElementWithJS(WebElement element) {
        JavascriptExecutor jse =(JavascriptExecutor) this.webDriver;
        jse.executeScript("arguments[0].click()", element);
    }

    protected void waitForElementToBeClickableAndClick(String elementId) {
        WebElement element = this.webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.click();
    }

    protected void goHomeAndGoToTab(String baseUrl, String endpoint, String tabId) {
        this.webDriver.get(baseUrl + endpoint);
        this.waitForElementToBeClickableAndClick(tabId);
    }
}
