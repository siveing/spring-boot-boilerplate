package com.siveing.order.module.health;

import com.siveing.order.core.exceptions.ErrorCode;
import com.siveing.order.core.responses.ApiResponse;
import com.siveing.order.core.responses.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/health")
@Tag(name = "Health", description = "Endpoints for Health")
public class HealthController {
  @GetMapping
  @Operation(summary = "Health Check")
  public ResponseEntity<ApiResponse<String>> sayHello() {
    Boolean isFromClient = false;

    // Constructing the ErrorResponse object manually
    ErrorResponse error = ErrorResponse.builder()
        .errorCode(ErrorCode.INVALID_INPUT.getCode()) // e.g., VAL-001
        .errorMessage("This is a manual bad request error response.")
        .details("Detailed explanation of why this request is bad.")
        .timestamp(LocalDateTime.now())
        .build();

    if(isFromClient){
      // Returning ResponseEntity with 400 status and the failure envelope
      return ResponseEntity
          .badRequest()
          .body(ApiResponse.failure(error));
    }
    return ResponseEntity.ok(ApiResponse.success("It's work"));

  }

  @GetMapping("failed")
  @Operation(summary = "Health Failed")
  public ResponseEntity<ApiResponse<Void>> healthFailed(){

    ErrorResponse errorResponse = ErrorResponse.builder()
        .errorCode("TEST-ERROR-01")
        .errorMessage("Error hz prerng mes....")
        .details("Hmmm")
        .build();

    return ResponseEntity.badRequest().body(ApiResponse.failure(errorResponse));
  }
}
