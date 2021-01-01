package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.ResultStatusEnum;
import com.udacity.jwdnd.course1.cloudstorage.services.ResultsService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Controller
public class SuperDuperExceptionController implements ErrorController {

    @Autowired
    private Environment env;

    private ResultsService resultsService;

    public SuperDuperExceptionController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            model.addAttribute("errorCode", statusCode);
        }
        return "error";
    }

    @ExceptionHandler(value = { BindException.class, IllegalArgumentException.class })
    public String exception(BindException exception, Model model) {
        this.resultsService.checkForErrors(exception, model);
        return "result";
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public String handleDuplicateFile(ConstraintViolationException exception, Model model) {
        this.resultsService.setAttributes(model, ResultStatusEnum.NOT_SAVED);
        model.addAttribute("error", exception.getMessage());
        return "result";
    }

    @ExceptionHandler(value = {
            MultipartException.class,
            SizeLimitExceededException.class,
            FileSizeLimitExceededException.class,
            MaxUploadSizeExceededException.class
    })
    public String handleFileSizeLimitExceptions(MultipartException exception, Model model) {
        this.resultsService.setAttributes(model, ResultStatusEnum.NOT_SAVED);
        String errorMsg = exception.getMessage().split(";")[0];
        String maxFileSize = this.env.getProperty("spring.servlet.multipart.max-file-size");
        model.addAttribute("error", errorMsg + ".\nMaximum file size is: " + maxFileSize + ".");
        return "result";
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
