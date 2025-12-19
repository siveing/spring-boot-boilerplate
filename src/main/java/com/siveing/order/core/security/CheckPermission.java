package com.siveing.order.core.security;

import java.lang.annotation.*;

/**
 * Target for:
 * Retention for:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
  /**
   * The unique code of the permission required to execute this method.
   */
  String value();
}