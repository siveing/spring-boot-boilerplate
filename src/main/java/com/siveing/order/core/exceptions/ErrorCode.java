package com.siveing.order.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  INTERNAL_SERVER_ERROR("SYS-001", "Internal server error"),
  INVALID_INPUT("VAL-001", "Invalid input parameters"),
  UNAUTHORIZED("AUTH-001", "Authentication failed"),
  ACCESS_DENIED("AUTH-002", "Access denied"),
  RESOURCE_NOT_FOUND("RES-001", "Resource not found");

  private final String code;
  private final String defaultMessage;
}