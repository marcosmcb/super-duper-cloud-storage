package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.ResultStatusEnum;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.ResultsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialsService credentialsService;
    private UserService userService;
    private ResultsService resultsService;
    private EncryptionService encryptionService;

    public CredentialsController(CredentialsService credentialsService,
                                 UserService userService,
                                 ResultsService resultsService,
                                 EncryptionService encryptionService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.resultsService = resultsService;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentialsByUsername(String username) {
        Integer userId = this.userService.getUser(username).getUserId();
        return this
                .credentialsService
                .getCredentialsByUser(userId)
                .stream()
                .map(this::decryptPassword)
                .collect(Collectors.toList());
    }

    private void encryptPassword(Credential credential) {
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        credential.setKey(key);
        credential.setPassword(this.encryptionService.encryptValue(credential.getPassword(), key));
    }

    private Credential decryptPassword(Credential credential) {
        String unscrambledPassword = this.encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        credential.setUnscrambledPassword(unscrambledPassword);
        return credential;
    }

    @PostMapping
    public String createCredential(Authentication authentication, @Valid Credential credentialForm, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        credentialForm.setUserId(userId);
        this.encryptPassword(credentialForm);
        if (credentialForm.getCredentialId() == null) {
            this.credentialsService.insertCredential(credentialForm);
        } else {
            this.credentialsService.updateCredential(credentialForm);
        }
        this.resultsService.setAttributes(model, ResultStatusEnum.SUCCESS);
        return "result";
    }

    @GetMapping("/{credentialId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer credentialId, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        this.credentialsService.deleteCredential(credentialId, userId);
        this.resultsService.setAttributes(model, ResultStatusEnum.SUCCESS);
        return "result";
    }
}
