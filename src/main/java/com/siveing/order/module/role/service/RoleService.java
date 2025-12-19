package com.siveing.order.module.role.service;

import com.siveing.order.module.role.dto.CreateRoleDto;
import com.siveing.order.module.role.entity.Role;
import com.siveing.order.module.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

  //  Repository
  private final RoleRepository repository;

  // Get List Role
  public List<Role> getListRoles() {
    return repository.findAll();
  }

  // Create Role
  public Role createRole(CreateRoleDto request) {
    try {
      var role = Role.builder()
          .name(request.getName())
          .code(request.getCode())
          .build();
      return repository.save(role);
      
    } catch (Exception e) {
      log.error("Registration failed for {}: {}", request.getCode(), e.getMessage());
      throw e;
    }
  }
}
