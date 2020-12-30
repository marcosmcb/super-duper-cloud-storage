package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.CloudStorageApplicationTests;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.Util;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpTest extends CloudStorageApplicationTests {


    @Test
    public void shouldNotAuthorizeAccessToHomePage() {
        String currentTitle = this.signupPage.tryToAccessAuthenticatedPage(this.baseUrl);
        Assertions.assertEquals("Login", currentTitle);
    }

    @Test
    public void shouldCreateNewUserSuccessfully() {
        User newUser = new Util().createUser(15);
        String successMessage = this.signupPage.createAndConfirmUser(this.baseUrl, newUser);
        Assertions.assertNotNull(successMessage);
    }

    @Test
    public void shouldFailToCreateNewUser() {
        User newUser = new Util().createUser(15);
        String errorMessage = this.signupPage.failToCreateExistingUser(this.baseUrl, newUser);
        Assertions.assertNotNull(errorMessage);
    }
}
