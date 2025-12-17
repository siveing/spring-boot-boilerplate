package com.siveing.order.module.demo.controller;

import com.siveing.order.core.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@Tag(name = "Demo", description = "Endpoints for testing RBAC")
public class DemoController {

  @GetMapping
  @Operation(summary = "General endpoint", description = "Accessible by any authenticated user")
  public ResponseEntity<ApiResponse<String>> sayHello() {
    return ResponseEntity.ok(ApiResponse.success("Hello! You are authenticated."));
  }

  @GetMapping("/admin-only")
  @Operation(summary = "Admin endpoint", description = "Accessible only by ADMIN role")
  // Restriction is enforced in SecurityConfig
  public ResponseEntity<ApiResponse<String>> sayHelloAdmin() {
    return ResponseEntity.ok(ApiResponse.success("Hello Admin! You have special access."));
  }
}