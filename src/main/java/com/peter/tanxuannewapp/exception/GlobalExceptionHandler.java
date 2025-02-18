package com.peter.tanxuannewapp.exception;

import com.peter.tanxuannewapp.domain.resposne.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class, ResourceAlreadyExistsException.class, BadCredentialsException.class})
    public ResponseEntity<ApiResponse<Object>> handleResourceInValidException(Exception e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setError("Exception occurs...");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(Exception e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiResponse.setMessage(e.getMessage());
        apiResponse.setError("Resource not found...");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
