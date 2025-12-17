package com.siveing.order.core.exceptions;

import com.siveing.order.core.responses.ApiResponse;
import com.siveing.order.core.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    log.warn("Validation error: {}", ex.getMessage());
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode(ErrorCode.INVALID_INPUT.getCode())
        .errorMessage("Input validation failed")
        .details(errors)
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.failure(errorResponse));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse<Void>> handleBadCredentials(BadCredentialsException e) {
    log.warn("Authentication failed: {}", e.getMessage());
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode(ErrorCode.UNAUTHORIZED.getCode())
        .errorMessage(ErrorCode.UNAUTHORIZED.getDefaultMessage())
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(ApiResponse.failure(errorResponse));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiResponse<Void>> handleAccessDenied(AccessDeniedException e) {
    log.warn("Access denied: {}", e.getMessage());
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode(ErrorCode.ACCESS_DENIED.getCode())
        .errorMessage(ErrorCode.ACCESS_DENIED.getDefaultMessage())
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(ApiResponse.failure(errorResponse));
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleUserNotFound(UsernameNotFoundException e) {
    log.warn("User not found: {}", e.getMessage());
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode(ErrorCode.RESOURCE_NOT_FOUND.getCode())
        .errorMessage(e.getMessage())
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ApiResponse.failure(errorResponse));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException e) {
    log.warn("Resource not found: {}", e.getMessage());
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode(ErrorCode.RESOURCE_NOT_FOUND.getCode())
        .errorMessage(e.getMessage())
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ApiResponse.failure(errorResponse));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
    log.error("An unexpected error occurred: ", e);
    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
        .errorMessage("An unexpected error occurred. Please contact support.")
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ApiResponse.failure(errorResponse));
  }
}