package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class NotePage extends Page {

    private static final String notesTab = "nav-notes-tab";
    private final String btnSaveNoteModal = "btnSaveNoteModal";
    private final String btnNotesAdd = "btnNotesAdd";

    @FindBy(id="note-title")
    private WebElement noteTitle;

    @FindBy(id="note-description")
    private WebElement noteDescription;

    @FindBy(id="btnSaveNoteModal")
    private WebElement buttonSaveNote;

    @FindBy(id="notesTable")
    private WebElement tableNotes;

    public NotePage(WebDriver webDriver) {
        super(webDriver, notesTab);
        PageFactory.initElements(webDriver, this);
    }


    private void fillInForm(Note note) {
        this.noteTitle.clear();
        this.noteDescription.clear();

        this.noteTitle.sendKeys(note.getNoteTitle());
        this.noteDescription.sendKeys(note.getNoteDescription());

        // Click on Save Note
        this.waitForElementToBeClickableAndClick(this.btnSaveNoteModal);
    }

    public String createNote(Note note, String baseUrl, String endpoint) {
        // Go to notes tab
        this.goHomeAndGoToTab(baseUrl, endpoint);

        // Click on Add new Note
        this.waitForElementToBeClickableAndClick(this.btnNotesAdd);

        this.fillInForm(note);

        return getPageTitleAndGoHome(baseUrl, endpoint);
    }

    public List<String> getNoteDescriptions() {
        return this.getTableData(this.tableNotes);
    }

    public String editNote(Note existingNote, Note updatedNote, String baseUrl, String endpoint) {
        List<WebElement> rows = this.getTableRows(this.tableNotes);
        findButton(rows, existingNote.getNoteDescription(), GREEN).click();
        this.fillInForm(updatedNote);

        return this.getPageTitleAndGoHome(baseUrl, endpoint);
    }

    public String deleteNote(Note note, String baseUrl, String endpoint) {
        List<WebElement> rows = this.getTableRows(this.tableNotes);
        findButton(rows, note.getNoteDescription(), RED).click();

        return this.getPageTitleAndGoHome(baseUrl, endpoint);
    }
}
