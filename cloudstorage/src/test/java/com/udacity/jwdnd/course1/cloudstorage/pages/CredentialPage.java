package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CredentialPage extends Page {

    private static final String credentialsTab = "nav-credentials-tab";
    private final String btnCredentialAdd = "btnCredentialAdd";
    private final String btnSaveCredentialModal = "btnSaveCredentialModal";

    @FindBy(id="credential-url")
    private WebElement inputCredentialUrl;

    @FindBy(id="credential-username")
    private WebElement inputCredentialUsername;

    @FindBy(id="credential-password")
    private WebElement inputCredentialPassword;

    @FindBy(id="credentialTable")
    private WebElement tableCredentials;


    public CredentialPage(WebDriver webDriver) {
        super(webDriver, credentialsTab);
        PageFactory.initElements(webDriver, this);
    }

    public List<String> getCredentialUsernames() {
        return this.getTableData(this.tableCredentials);
    }

    private void fillInForm(Credential credential) {
        this.inputCredentialUrl.clear();
        this.inputCredentialUsername.clear();
        this.inputCredentialPassword.clear();

        this.inputCredentialUrl.sendKeys(credential.getUrl());
        this.inputCredentialUsername.sendKeys(credential.getUsername());
        this.inputCredentialPassword.sendKeys(credential.getPassword());

        // Click on Save Credential
        this.waitForElementToBeClickableAndClick(this.btnSaveCredentialModal);
    }

    public String createCredential(Credential credential, String baseUrl, String endpoint) {
        // Go to credentials tab
        this.goHomeAndGoToTab(baseUrl, endpoint);

        // Click on Add new Credential
        this.waitForElementToBeClickableAndClick(this.btnCredentialAdd);

        this.fillInForm(credential);

        return getPageTitleAndGoHome(baseUrl, endpoint);
    }


    public String editCredential(Credential existingCredential, Credential updatedCredential, String baseUrl, String endpoint) {
        List<WebElement> rows = this.getTableRows(this.tableCredentials);
        findButton(rows, existingCredential.getUsername(), GREEN).click();
        this.fillInForm(updatedCredential);

        return this.getPageTitleAndGoHome(baseUrl, endpoint);
    }

    public String deleteCredential(Credential credential, String baseUrl, String endpoint) {
        List<WebElement> rows = this.getTableRows(this.tableCredentials);
        findButton(rows, credential.getUsername(), RED).click();

        return this.getPageTitleAndGoHome(baseUrl, endpoint);
    }
}
