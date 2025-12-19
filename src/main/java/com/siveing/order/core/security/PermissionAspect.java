package com.siveing.order.core.security;

import com.siveing.order.module.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PermissionAspect {

  @Before("@annotation(checkPermission)")
  public void verifyPermission(CheckPermission checkPermission) {
    var authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
      throw new AccessDeniedException("User is not authenticated");
    }

    String requiredPermission = checkPermission.value();
    log.debug("Checking permission '{}' for user '{}'", requiredPermission, user.getEmail());

    // Dynamic check: Does the user's role contain this permission?
    boolean hasPermission = user.getRole().getCode()
        .equals(requiredPermission);

    if (!hasPermission) {
      log.warn("Access Denied: User '{}' missing required permission '{}'", user.getEmail(), requiredPermission);
      throw new AccessDeniedException("Missing required permission: " + requiredPermission);
    }
  }
}