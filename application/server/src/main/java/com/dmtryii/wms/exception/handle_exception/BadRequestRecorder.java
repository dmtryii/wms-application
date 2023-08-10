package com.dmtryii.wms.exception.handle_exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class BadRequestRecorder {
    public String getRecordedErrors(BindingResult bindingResult) {

        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error: errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }
}
