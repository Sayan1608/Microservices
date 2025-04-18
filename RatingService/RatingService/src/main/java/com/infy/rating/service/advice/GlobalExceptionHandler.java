package com.infy.rating.service.advice;

import com.infy.rating.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
