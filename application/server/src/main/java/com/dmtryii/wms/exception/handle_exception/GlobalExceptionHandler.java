package com.dmtryii.wms.exception.handle_exception;

import com.dmtryii.wms.exception.InvalidEmailException;
import com.dmtryii.wms.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorObject> handleInvalidEmailException(InvalidEmailException ex,
                                                                   WebRequest request) {
        return handle_BAD_REQUEST(
                ex,
                request
        );
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorObject> handleInvalidPasswordException(InvalidPasswordException ex,
                                                                      WebRequest request) {
        return handle_BAD_REQUEST(
                ex,
                request
        );
    }

    private ResponseEntity<ErrorObject> handle_BAD_REQUEST(RuntimeException ex,
                                                           WebRequest request) {
        return handleMethod(
                ex,
                request,
                HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<ErrorObject> handleMethod(RuntimeException ex,
                                                     WebRequest request,
                                                     HttpStatus httpStatus) {
        ErrorObject errorObject = ErrorObject.builder()
                .timestamp(new Date())
                .status(httpStatus.value())
                .error(ex.getMessage())
                .path(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorObject, httpStatus);
    }
}
