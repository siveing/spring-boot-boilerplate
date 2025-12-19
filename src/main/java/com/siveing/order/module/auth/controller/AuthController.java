package com.siveing.order.module.auth.controller;

import com.siveing.order.core.responses.ApiResponse;
import com.siveing.order.module.auth.dto.LoginDTO;
import com.siveing.order.module.auth.dto.RegisterDTO;
import com.siveing.order.module.auth.response.LoginResponse;
import com.siveing.order.module.auth.response.RegisterResponse;
import com.siveing.order.module.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

  // SERVICE
  private final AuthService service;

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<RegisterResponse>> register(
      @RequestBody @Valid RegisterDTO request
  ) {
    log.debug("Received registration request for: {}", request.getEmail());
    return ResponseEntity.ok(ApiResponse.success(service.register(request)));
  }

  @Operation(summary = "Authenticate user", description = "Authenticates a user by email and password, returning a JWT token.")
  @PostMapping("/authenticate")
  public ResponseEntity<ApiResponse<LoginResponse>> authenticate(
      @RequestBody @Valid LoginDTO request
  ) {
    log.debug("Received authentication request for: {}", request.getEmail());
    return ResponseEntity.ok(ApiResponse.success(service.authenticate(request)));
  }
}
