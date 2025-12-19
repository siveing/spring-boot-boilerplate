package com.siveing.order.module.role.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Create role payload")
public class CreateRoleDto {
  @NotBlank(message = "Name is required")
  @Schema(description = "Role Name", example = "Admin")
  private String name;

  @NotBlank(message = "Code is required")
  @Schema(description = "Role Code", example = "ADMIN")
  private String code;
}
