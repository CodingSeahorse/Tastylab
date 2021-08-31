package com.codingseahorse.tastylab.utils;

import com.codingseahorse.tastylab.exception.BadRequestException;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@NoArgsConstructor
public class ExceptionUtils {

    public static void createErrorMessageAndThrowEntityValidationException(BindingResult result) {
        FieldError fieldError = result.getFieldErrors().get(0);
        String errorMessage = "The field " + fieldError.getField() +
                " with value " + fieldError.getRejectedValue() + " does not meet requirements. ";
        throw new BadRequestException(errorMessage + fieldError.getDefaultMessage());
    }
}
