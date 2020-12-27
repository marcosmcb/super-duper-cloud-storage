package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.ResponseFile;
import com.udacity.jwdnd.course1.cloudstorage.models.ResultStatusEnum;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.ResultsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/files")
public class FilesController {

    private FilesService filesService;
    private UserService userService;
    private ResultsService resultsService;

    public FilesController(FilesService filesService, UserService userService, ResultsService resultsService) {
        this.filesService = filesService;
        this.userService = userService;
        this.resultsService = resultsService;
    }

    public List<ResponseFile> getAllFilesByUsername(String username) {
        Integer userId = this.userService.getUser(username).getUserId();
        return this
                .filesService
                .getFilesByUserId(userId)
                .stream()
                .map(file -> {
                    String dataUrl = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(file.getUserId() + "/" + file.getFileId())
                            .toUriString();
                    return this.convertToResponseFile(file, dataUrl);
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/files/{userId}/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable @NotNull Integer userId, @PathVariable @NotNull Integer id) {
        File file = this.filesService.getFileById(id, userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }

    @PostMapping
    public String createFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile fileForm, Model model) throws IOException, BindException {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        File fileData = new File(fileForm, userId, null);
        this.resultsService.validateModel(fileData);
        this.filesService.createFile(fileData);
        this.resultsService.setAttributes(model, ResultStatusEnum.SUCCESS);
        return "result";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFileById(Authentication authentication, @PathVariable @NotNull Integer fileId, Model model) {
        Integer userId = this.userService.getUser(authentication.getName()).getUserId();
        this.filesService.deleteFileById(fileId, userId);
        this.resultsService.setAttributes(model, ResultStatusEnum.SUCCESS);
        return "result";
    }

    private ResponseFile convertToResponseFile(File file, String dataUrl) {
        return new ResponseFile(file.getFileName(), dataUrl, file.getContentType(), file.getFileSize(), file.getFileId());
    }
}
