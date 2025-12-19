package com.siveing.order.module.user.controller;

import com.siveing.order.core.responses.ApiResponse;
import com.siveing.order.core.security.CheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
@Tag(name = "Admin - Users")
public class UserController {

  @GetMapping()
  @CheckPermission("ROLE_ADMIN")
  public ResponseEntity<ApiResponse<String>> test(){
    return ResponseEntity.ok(ApiResponse.success("Hello"));
  }
}
