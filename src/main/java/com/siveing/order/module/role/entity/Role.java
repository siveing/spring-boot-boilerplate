package com.siveing.order.module.role.entity;

import com.siveing.order.infrastructure.database.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

  private String name;

  @Column(nullable = false, unique = true)
  private String code;
}
