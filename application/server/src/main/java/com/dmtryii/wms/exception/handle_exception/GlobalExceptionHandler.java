package com.dmtryii.wms.exception.handle_exception;

import com.dmtryii.wms.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DoAssemblyProductException.class)
    public ResponseEntity<ErrorObject> handle(DoAssemblyProductException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(ResourceNotCreatedException.class)
    public ResponseEntity<ErrorObject> handle(ResourceNotCreatedException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(ResourceNotUpdatedException.class)
    public ResponseEntity<ErrorObject> handle(ResourceNotUpdatedException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handle(ResourceNotFoundException ex,
                                              WebRequest request) {
        return handle_NOT_FOUND(ex, request);
    }

    @ExceptionHandler(CityNotUpdatedException.class)
    public ResponseEntity<ErrorObject> handle(CityNotUpdatedException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(CityNotCreatedException.class)
    public ResponseEntity<ErrorObject> handle(CityNotCreatedException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorObject> handle(UserNotFoundException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorObject> handle(InvalidEmailException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorObject> handle(InvalidPasswordException ex,
                                              WebRequest request) {
        return handle_BAD_REQUEST(ex, request);
    }

    private ResponseEntity<ErrorObject> handle_NOT_FOUND(RuntimeException ex,
                                                         WebRequest request) {
        return handleMethod(ex, request,
                HttpStatus.NOT_FOUND
        );
    }

    private ResponseEntity<ErrorObject> handle_BAD_REQUEST(RuntimeException ex,
                                                           WebRequest request) {
        return handleMethod(ex, request,
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
