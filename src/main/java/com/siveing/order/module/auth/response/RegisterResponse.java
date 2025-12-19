package com.siveing.order.module.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response model containing authentication tokens")
public class RegisterResponse {
  @Schema(description = "JWT Access Token", example = "eyJhbGciOiJIUzI1NiJ9...")
  private String token;

  @Schema(description = "Refresh Token", example = "d9b2d63d-...")
  private String refreshToken;
}
