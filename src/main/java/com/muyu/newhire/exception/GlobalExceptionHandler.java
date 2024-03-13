package com.muyu.newhire.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        ErrorResponse error = new ErrorResponse(e.getStatus(), e.getCode(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(Throwable e) {
        log.error(e.getMessage(), e);
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse error = new ErrorResponse(status.value(), status.name(), e.getMessage());
        return new ResponseEntity<>(error, status);
    }

}
