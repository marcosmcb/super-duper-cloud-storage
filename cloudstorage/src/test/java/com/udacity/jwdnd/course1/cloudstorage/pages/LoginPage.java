package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class LoginPage extends Page {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "btnLogin")
    private WebElement buttonLogin;

    @FindBy(id = "btnLogOut")
    private WebElement buttonLogout;

    private static String URL = "/login";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public String logInUser(String baseUrl, User user) {
        this.webDriver.get(baseUrl + URL);
        this.inputUsername.sendKeys(user.getUsername());
        this.inputPassword.sendKeys(user.getPassword());
        this.buttonLogin.click();

        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        return this.webDriver.getTitle();
    }

    public String logOutUser(String baseUrl) {
        this.webDriver.get(baseUrl + "/home");
        this.buttonLogout.click();
        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        return this.webDriver.getTitle();
    }
}
