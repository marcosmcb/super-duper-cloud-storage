package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.ResultStatusEnum;
import com.udacity.jwdnd.course1.cloudstorage.models.SuperDuperErrors;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ResultsService {

    private Validator validator;

    public ResultsService() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();;
    }

    public void setAttributes(Model model, ResultStatusEnum resultStatusEnum) {
        model.addAttribute("status", resultStatusEnum.toString());
    }

    public void checkForErrors(BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            this.setAttributes(model, ResultStatusEnum.ERROR);
            model.addAttribute("errors", bindingResult.getFieldErrors());
        }
    }

    public void validateModel(File model) throws BindException {
        Set<ConstraintViolation<File>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            throw new BindException(new SuperDuperErrors(violations));
        }
    }
}
