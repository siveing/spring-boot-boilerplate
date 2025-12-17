package com.siveing.order.core.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Standard error response format")
public class ErrorResponse {
  @Schema(description = "Error code identifying the error type", example = "VAL-001")
  private String errorCode;

  @Schema(description = "Descriptive error message", example = "Invalid input parameters")
  private String errorMessage;

  @Schema(description = "Additional details about the error", nullable = true)
  private Object details;

  @Schema(description = "Timestamp when the error occurred")
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
}