package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CredentialPage extends Page {


    private final String credentialsTab = "nav-credentials-tab";


    @FindBy(id="btnCredentialAdd")
    private WebElement buttonAddCredential;

    @FindBy(id="credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id="credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id="credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id="btnSaveCredentialModal")
    private WebElement buttonSaveCredential;

    @FindBy(id="credentialTable")
    private WebElement tableCredentials;


    public CredentialPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void goToTab(String baseUrl, String endpoint) {
        this.goHomeAndGoToTab(baseUrl, endpoint, this.credentialsTab);
    }

    private String getPageTitleAndGoHome(String baseUrl, String endpoint) {
        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        String resultTitle = this.webDriver.getTitle();

        // Go back to Notes tab
        this.goToTab(baseUrl, endpoint);

        return resultTitle;
    }

    public List<String> getCredentialUsernames() {
        WebElement element = this.webDriverWait.until(ExpectedConditions.visibilityOf(tableCredentials));
        List<WebElement> nodes = element.findElements(By.tagName("td"));
        return nodes.stream().map(node -> node.getText()).collect(Collectors.toList());
    }


    private void fillInForm(Credential credential) {
        this.inputCredentialUrl.clear();
        this.inputCredentialUsername.clear();
        this.inputCredentialPassword.clear();

        this.inputCredentialUrl.sendKeys(credential.getUrl());
        this.inputCredentialUsername.sendKeys(credential.getUsername());
        this.inputCredentialPassword.sendKeys(credential.getPassword());

        // Click on Save Note
        this.waitForElementToBeClickableAndClick("btnSaveCredentialModal");
    }

    public String createCredential(Credential credential, String baseUrl, String endpoint) {
        // Go to notes tab
        this.goToTab(baseUrl, endpoint);

        // Click on Add new Note
        this.waitForElementToBeClickableAndClick("btnCredentialAdd");

        this.fillInForm(credential);

        return getPageTitleAndGoHome(baseUrl, endpoint);
    }


    public String editCredential(Credential existingCredential, Credential updatedCredential, String baseUrl, String endpoint) {
        WebElement element = this.webDriverWait.until(ExpectedConditions.visibilityOf(tableCredentials));
        List<WebElement> rows = element.findElements(By.tagName("tr"));

        WebElement editButton = findButton(rows, existingCredential.getUsername(), "green");
        editButton.click();

        this.fillInForm(updatedCredential);

        return this.getPageTitleAndGoHome(baseUrl, endpoint);
    }

    public String deleteCredential(Credential credential, String baseUrl, String endpoint) {
        WebElement element = this.webDriverWait.until(ExpectedConditions.visibilityOf(tableCredentials));
        List<WebElement> rows = element.findElements(By.tagName("tr"));

        WebElement deleteButton = findButton(rows, credential.getUsername(), "red");
        deleteButton.click();

        return this.getPageTitleAndGoHome(baseUrl, endpoint);
    }
}
