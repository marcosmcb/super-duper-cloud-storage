package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Page {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    private String tabId;

    protected static String GREEN = "green";
    protected static String RED = "red";

    protected static int MAX_TIMEOUT = 300;
    protected static long TIMEOUT = 30;

    public Page(WebDriver webDriver, String tabId) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(this.webDriver, MAX_TIMEOUT);
        this.tabId = tabId;
    }

    protected void waitForElementToBeClickableAndClick(String elementId) {
        WebElement element = this.webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.click();
    }

    protected void goHomeAndGoToTab(String baseUrl, String endpoint) {
        this.webDriver.get(baseUrl + endpoint);
        this.waitForElementToBeClickableAndClick(this.tabId);
    }


    private boolean isCorrectRow(WebElement row, String itemToBeFound) {
        for (WebElement data : row.findElements(By.tagName("td"))) {
            if (data.getText().equals(itemToBeFound)) {
                return true;
            }
        }
        return false;
    }

    protected WebElement findButton(List<WebElement> tableRows, String elementDescription, String className) {
        for(WebElement row : tableRows) {
            if (isCorrectRow(row, elementDescription)) {
                return row.findElement(By.className(className));
            }
        }
        return null;
    }

    protected String getPageTitleAndGoHome(String baseUrl, String endpoint) {
        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        String resultTitle = this.webDriver.getTitle();

        // Go back to Notes tab
        this.goHomeAndGoToTab(baseUrl, endpoint);

        return resultTitle;
    }

    public List<String> getTableData(WebElement tableElement) {
        WebElement element = this.webDriverWait.until(ExpectedConditions.visibilityOf(tableElement));
        List<WebElement> notes = element.findElements(By.tagName("td"));
        return notes.stream().map(note -> note.getText()).collect(Collectors.toList());
    }


    protected List<WebElement> getTableRows(WebElement tableElement) {
        WebElement element = this.webDriverWait.until(ExpectedConditions.visibilityOf(tableElement));
        List<WebElement> rows = element.findElements(By.tagName("tr"));
        return rows;
    }
}
