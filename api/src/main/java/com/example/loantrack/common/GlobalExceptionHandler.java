package com.example.loantrack.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Errpr", ex);
        return new ResponseEntity<>(new ApiResponse<>(false, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {
        logger.error("Errpr", ex);
        return new ResponseEntity<>(new ApiResponse<>(false, "An unexpected error occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {
        logger.error("Errpr", ex);
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return new ResponseEntity<>(new ApiResponse<>(false, errorMessage, null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthorizationDeniedException(AuthorizationDeniedException ex) {
        logger.debug("Authorization denied: {}", ex.getMessage());

        return new ResponseEntity<>(new ApiResponse<>(false, "Access denied. You don't have permission to perform this action.", null), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDeniedException(AccessDeniedException ex) {
        logger.debug("Access denied: {}", ex.getMessage());

        return new ResponseEntity<>(new ApiResponse<>(false, "Access denied. You don't have permission to perform this action.", null), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {
        logger.debug("Authentication failed: {}", ex.getMessage());

        return new ResponseEntity<>(new ApiResponse<>(false, "Authentication failed. Please check your credentials.", null), HttpStatus.UNAUTHORIZED);
    }
}