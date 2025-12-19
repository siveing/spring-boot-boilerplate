package com.siveing.order.module.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request model for user login")
public class LoginDTO {
  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  @Schema(description = "User's email address", example = "john.doe@example.com")
  private String email;

  @NotBlank(message = "Password is required")
  @Schema(description = "User's password", example = "password123")
  private String password;
}
