package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.CloudStorageApplicationTests;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest extends CloudStorageApplicationTests {

    private User newUser;
    private Note newNote;

    private void setNewUserAndNote() {
        this.newUser = new Util().createUser(10);
        this.newNote = new Util().createNote(15);
    }

    @Test
    public void shouldLogInAndCreateNote() {
        this.setNewUserAndNote();

        this.signUpAndLogInUser(this.newUser);
        String resultTitle = this.notePage.createNote(this.newNote, this.baseUrl, "/home");

        Assertions.assertTrue(this.notePage.getNoteTitles().contains(newNote.getNoteDescription()));
        Assertions.assertEquals("Result", resultTitle);
    }


//    @Test
//    public void shouldEditExistingNote() {
//        this.setNewUserAndNote();
//
//        this.signUpAndLogInUser(this.newUser);
//        this.notePage.createNote(this.newNote, this.baseUrl, "/home");
//        this.notePage.editNote();
//    }
}
