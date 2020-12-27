package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.ResultsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SuperDuperExceptionController {

    private ResultsService resultsService;

    public SuperDuperExceptionController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @ExceptionHandler(value = { BindException.class, IllegalArgumentException.class })
    public String exception(BindException exception, Model model) {
        this.resultsService.checkForErrors(exception, model);
        return "result";
    }
}
