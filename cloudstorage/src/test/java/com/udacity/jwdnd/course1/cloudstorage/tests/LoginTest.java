package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.CloudStorageApplicationTests;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.Util;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static com.udacity.jwdnd.course1.cloudstorage.utils.TitleEnum.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest extends CloudStorageApplicationTests {

    @Test
    public void getLoginPage() {
        this.driver.get(this.baseUrl + "/login");
        Assertions.assertEquals(LOGIN.toString(), this.driver.getTitle());
    }

    @Test
    public void shouldLoginUser() {
        User newUser = new Util().createUser(15);
        this.signupPage.createAndConfirmUser(this.baseUrl, newUser);
        String title = this.loginPage.logInUser(this.baseUrl, newUser);
        Assertions.assertEquals(HOME.toString(), title);
    }

    @Test
    public void shouldLogoutUserAndNotAllowedToAccessHome() {
        User newUser = new Util().createUser(15);
        this.signupPage.createAndConfirmUser(this.baseUrl, newUser);
        String logInTitle = this.loginPage.logInUser(this.baseUrl, newUser);
        String logOutTitle = this.loginPage.logOutUser(this.baseUrl, HOME_ENDPOINT);

        String blockedPageTitle = this.signupPage.tryToAccessAuthenticatedPage(this.baseUrl);

        Assertions.assertEquals(HOME.toString(), logInTitle);
        Assertions.assertEquals(LOGIN.toString(), logOutTitle);
        Assertions.assertEquals(LOGIN.toString(), blockedPageTitle);
    }
}
