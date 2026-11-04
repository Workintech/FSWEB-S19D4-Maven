package com.workintech.s19d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleException(ApiException apiException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse( apiException.getMessage(),apiException.getStatus().value(),java.time.LocalDateTime.now());
        log.error(exceptionResponse.toString());
        return new ResponseEntity<>(exceptionResponse, apiException.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),java.time.LocalDateTime.now());
        log.error(exceptionResponse.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
