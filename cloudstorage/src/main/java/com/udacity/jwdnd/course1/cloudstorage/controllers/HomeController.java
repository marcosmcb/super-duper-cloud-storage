package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.ResponseFile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NotesController notesController;
    private CredentialsController credentialsController;
    private FilesController filesController;

    public HomeController(NotesController notesController, CredentialsController credentialsController, FilesController filesController) {
        this.notesController = notesController;
        this.credentialsController = credentialsController;
        this.filesController = filesController;
    }

    @GetMapping
    public String fillHomePage(Authentication authentication, Model model) {
        List<Note> notes = this.notesController.getNotesByUsername(authentication.getName());
        List<Credential> credentials = this.credentialsController.getCredentialsByUsername(authentication.getName());
        List<ResponseFile> files = this.filesController.getAllFilesByUsername(authentication.getName());

        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("files", files);
        return "home";
    }
}
