package org.sivakamiveerapathiran.onlinenursery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class USerExceptionController {
    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<Object> exception(CustomerAlreadyExistsException exception) {
        return new ResponseEntity<>("Customer Already Exists", HttpStatus.NOT_FOUND);
    }
}

