package com.example.finance7.error;

import com.example.finance7.error.exceptions.ProductNotFoundException;
import com.example.finance7.error.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse response = ErrorResponse.of(Messages.USER_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorResponse response = ErrorResponse.of(Messages.PRODUCT_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
