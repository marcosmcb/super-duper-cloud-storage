package com.udacity.jwdnd.course1.cloudstorage.pages;

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

public class NotePage extends Page {

    @FindBy (id="nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id="btnNotesAdd")
    private WebElement buttonNotes;

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="btnSaveNoteModal")
    private WebElement buttonSaveNote;

    @FindBy(id="notesTable")
    private WebElement tableNotes;

    public NotePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }



    public String createNote(Note note, String baseUrl, String endpoint) {
        // Go to notes tab
        this.goToTab(baseUrl, endpoint);

        // Click on Add new Note
        this.waitForElementToBeClickableAndClick("btnNotesAdd");

        this.noteTitle.sendKeys(note.getNoteTitle());
        this.noteDescription.sendKeys(note.getNoteDescription());

        // Click on Save Note
        this.waitForElementToBeClickableAndClick("btnSaveNoteModal");

        this.webDriverWait.withTimeout(Duration.ofSeconds(TIMEOUT));
        String resultTitle = this.webDriver.getTitle();

        // Go back to Notes tab
        this.goToTab(baseUrl, endpoint);

        return resultTitle;
    }


    public List<String> getNoteTitles() {
        WebElement element = this.webDriverWait.until(ExpectedConditions.visibilityOf(tableNotes));
        List<WebElement> notes = element.findElements(By.tagName("td"));
        return notes.stream().map(note -> note.getText()).collect(Collectors.toList());
    }

    public void goToTab(String baseUrl, String endpoint) {
        this.goHomeAndGoToTab(baseUrl, endpoint, "nav-notes-tab");
    }

    public String editNote() {
        return "";
    }
}
