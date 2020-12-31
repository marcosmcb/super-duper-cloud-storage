package com.udacity.jwdnd.course1.cloudstorage.tests;


import com.udacity.jwdnd.course1.cloudstorage.CloudStorageApplicationTests;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static com.udacity.jwdnd.course1.cloudstorage.utils.TitleEnum.RESULT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest extends CloudStorageApplicationTests {

    private User newUser;
    private Credential newCredential;

    private void setNewUserAndCredential() {
        this.newUser = new Util().createUser(15);
        this.newCredential = new Util().createCredential(10);
    }


    @Test
    public void shouldLogInAndCreateCredential() {
        this.setNewUserAndCredential();

        this.signUpAndLogInUser(this.newUser);
        String resultTitle = this.credentialPage.createCredential(this.newCredential, this.baseUrl, HOME_ENDPOINT);

        Assertions.assertTrue(this.credentialPage.getCredentialUsernames().contains(this.newCredential.getUsername()));
        Assertions.assertEquals(RESULT.toString(), resultTitle);
    }


    @Test
    public void shouldEditExistingNote() {
        this.setNewUserAndCredential();

        Credential editedCredential = new Util().createCredential(15);

        this.signUpAndLogInUser(this.newUser);
        this.credentialPage.createCredential(this.newCredential, this.baseUrl, HOME_ENDPOINT);
        String resultTitle = this.credentialPage.editCredential(this.newCredential, editedCredential, this.baseUrl, HOME_ENDPOINT);

        Assertions.assertTrue(this.credentialPage.getCredentialUsernames().contains(editedCredential.getUsername()));
        Assertions.assertEquals(RESULT.toString(), resultTitle);
    }

    @Test
    public void shouldDeleteExistingNote() {
        this.setNewUserAndCredential();
        this.signUpAndLogInUser(this.newUser);

        this.credentialPage.createCredential(this.newCredential, this.baseUrl, HOME_ENDPOINT);
        String resultTitle = this.credentialPage.deleteCredential(this.newCredential, this.baseUrl, HOME_ENDPOINT);

        Assertions.assertFalse(this.credentialPage.getCredentialUsernames().contains(this.newCredential.getUsername()));
        Assertions.assertEquals(RESULT.toString(), resultTitle);
    }
}
