package com.siveing.order.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {

  @Schema(description = "Co Relation Id")
  @Builder.Default()
  private String correlationId = MDC.get("correlationId");

  @Schema(description = "Indicates if the operation was successful", example = "true")
  private boolean success;

  @Schema(description = "Response message", example = "Operation successful")
  private String message;

  @Schema(description = "Response payload")
  private T data;

  @Schema(description = "Error details, present only if success is false")
  private ErrorResponse error;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .success(true)
        .message("Operation successful")
        .data(data)
        .build();
  }

  public static <T> ApiResponse<T> failure(ErrorResponse error) {
    return ApiResponse.<T>builder()
        .success(false)
        .message(error.getErrorMessage())
        .error(error)
        .build();
  }
}