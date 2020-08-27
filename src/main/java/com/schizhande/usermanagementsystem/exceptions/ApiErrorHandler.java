package com.schizhande.usermanagementsystem.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RecordNotFoundException.class})
    public ResponseEntity<ErrorBody> handleNotFound(RecordNotFoundException ex) {
        ex.printStackTrace();
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage()).debugMessage(new RecordNotFoundException(ex.getMessage()).toString()).build();
        return new ResponseEntity(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<ErrorBody> handleNotFound(InvalidRequestException ex) {
        ex.printStackTrace();
        ErrorBody errorBody =
                ErrorBody.builder().message(ex.getMessage()).debugMessage(new InvalidRequestException(ex.getMessage()).toString()).build();
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<ErrorBody> handleInvalidToken(InvalidTokenException ex) {
        ex.printStackTrace();
        ErrorBody errorBody =
                ErrorBody.builder().message(ex.getMessage()).debugMessage(new InvalidTokenException(ex.getMessage()).toString()).build();
        return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({RecordExistsException.class})
    public ResponseEntity<ErrorBody> handleRecordExistsException(RecordExistsException ex) {
        ex.printStackTrace();
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage()).debugMessage(ex.getLocalizedMessage()).build();
        return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MissingRecordsException.class})
    public ResponseEntity<ErrorBody> handleRecordMissingRecordsException(MissingRecordsException ex) {
        ex.printStackTrace();
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage())
                .debugMessage(ex.getClass().getName() + " : " + ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorBody> handleRecordMissingRecordsException(EntityNotFoundException ex) {
        ex.printStackTrace();
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage())
                .debugMessage(ex.getClass().getName() + " : " + ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<ErrorBody> handleRecordMissingRecordsException(IllegalStateException ex) {
        ex.printStackTrace();
        ErrorBody errorBody = ErrorBody.builder().message(ex.getMessage())
                .debugMessage(ex.getClass().getName() + " : " + ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        ex.printStackTrace();
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiResponse apiResponse =
                new ApiResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(
                apiResponse, new HttpHeaders(), apiResponse.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleDataIntegrityViolationException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        String message = getRootException(exception).getLocalizedMessage();
        if (message.contains("Column")) {
            message = message.replace("Column", "Field");
        }
        if (message.contains("Duplicate")) {
            message = message.replace(message.substring(message.indexOf("for"), message.length()), ". This value must be unique.");
        }
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), apiResponse.getStatus());

    }

    /*@ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorBody> handleInternalServerError(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        String message = "Unexpected error occured.";
        ErrorBody errorBody = ErrorBody.builder()
                .message(message)
                .debugMessage(exception.getMessage())
                .build();
        String type = exception.getClass().getName();
        HttpStatus status;
        if (exception instanceof InvalidDataAccessResourceUsageException) {
            status = HttpStatus.FAILED_DEPENDENCY;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return new ResponseEntity(errorBody, status);
    }*/

    public static Throwable getRootException(Throwable exception) {
        Throwable rootException = exception;
        while (rootException.getCause() != null) {
            rootException = rootException.getCause();
        }
        return rootException;
    }
}
