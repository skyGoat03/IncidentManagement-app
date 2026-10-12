package com.roshan.monitoringplatform.Exception;

import com.roshan.monitoringplatform.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(serivceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>   handleServicealreadyExists(serivceAlreadyExistsException serviceExeption){

        ErrorResponse response = new ErrorResponse();
        response.setStatus(HttpStatus.CONFLICT);
        response.setTimestamp(Instant.now());
        response.setErrorMessage(serviceExeption.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException exception){

        String message =exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(","));

        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(message);
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setTimestamp(Instant.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFound(resourceNotFoundException resEx){

        ErrorResponse error = new ErrorResponse();

        error.setErrorMessage(resEx.getMessage());
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
