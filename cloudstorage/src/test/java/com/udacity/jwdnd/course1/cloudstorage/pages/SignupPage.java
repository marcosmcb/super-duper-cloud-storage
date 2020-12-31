package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class SignupPage extends Page {

    @FindBy(id="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id="inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "signUpBtn")
    private WebElement signUpBtn;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    private static String URL = "/signup";

    public SignupPage(WebDriver webDriver) {
        super(webDriver, null);
        PageFactory.initElements(webDriver, this);
    }


    public String tryToAccessAuthenticatedPage(String baseUrl) {
        this.webDriver.get(baseUrl + "/home");
        return this.webDriver.getTitle();
    }


    private void createNewUser(String baseUrl,User user) {
        this.webDriver.get(baseUrl + URL);
        this.inputFirstName.sendKeys(user.getFirstName());
        this.inputLastName.sendKeys(user.getLastName());
        this.inputUsername.sendKeys(user.getUsername());
        this.inputPassword.sendKeys(user.getPassword());
        this.signUpBtn.click();
    }


    public String createAndConfirmUser(String baseUrl, User user) {
        this.createNewUser(baseUrl, user);
        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        return this.successMsg.getText();
    }

    public String failToCreateExistingUser(String baseUrl, User user) {
        this.createNewUser(baseUrl, user);
        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        this.createNewUser(baseUrl, user);
        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        return this.errorMsg.getText();
    }
}
