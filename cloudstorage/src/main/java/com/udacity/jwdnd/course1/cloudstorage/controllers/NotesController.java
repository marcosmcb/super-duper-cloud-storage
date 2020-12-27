package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.ResultStatusEnum;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.ResultsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private NotesService notesService;
    private UserService userService;
    private ResultsService resultsService;

    public NotesController(NotesService notesService, UserService userService, ResultsService resultsService) {
        this.notesService = notesService;
        this.userService = userService;
        this.resultsService = resultsService;
    }

    public List<Note> getNotesByUsername(String username) {
        Integer userId = this.userService.getUser(username).getUserId();
        return this.notesService.getNotesByUserId(userId);
    }

    @PostMapping
    public String createNote(Authentication authentication, Note noteForm, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        noteForm.setUserId(userId);
        if (noteForm.getNoteId() == null) {
            this.notesService.insertNote(noteForm);
        } else {
            this.notesService.updateNote(noteForm);
        }
        this.resultsService.setAttributes(model, ResultStatusEnum.SUCCESS);
        return "result";
    }

    @GetMapping("/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        this.notesService.deleteNote(noteId, userId);
        this.resultsService.setAttributes(model, ResultStatusEnum.SUCCESS);
        return "result";
    }
}
