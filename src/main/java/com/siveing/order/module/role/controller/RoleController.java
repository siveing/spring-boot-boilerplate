package com.siveing.order.module.role.controller;

import com.siveing.order.core.responses.ApiResponse;
import com.siveing.order.module.role.dto.CreateRoleDto;
import com.siveing.order.module.role.entity.Role;
import com.siveing.order.module.role.repository.RoleRepository;
import com.siveing.order.module.role.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin Role management apis")
public class RoleController {

  private final RoleService service;

  @GetMapping("list")
  @Operation(summary = "List all roles", description = "Retrieves all available roles.")
  public ResponseEntity<ApiResponse<List<Role>>> getListRole() {
    List<Role> roleList = this.service.getListRoles();
    return ResponseEntity.ok(ApiResponse.success(roleList));
  }

  @PostMapping()
  @Operation(summary = "Create Role")
  public ResponseEntity<ApiResponse<Role>> createRole(
      @RequestBody @Valid CreateRoleDto request
  ) {
    log.debug("Received registration request for: {}", request.getName());
    return ResponseEntity.ok(ApiResponse.success(service.createRole(request)));
  }
}
