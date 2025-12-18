package com.siveing.order.core.responses;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Global Response Interceptor (ResponseBodyAdvice).
 * Automatically wraps every controller response into the ApiResponse structure
 * unless it's already wrapped or is an error.
 */
@RestControllerAdvice(basePackages = "com.siveing.order.module")
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    // Apply to all methods in the module package
    return true;
  }

  @Override
  public Object beforeBodyWrite(
      Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    log.info("================ beforeBodyWrite {} ===============", MDC.get("correlationId"));

    // 1. If the body is null (e.g., void return type), wrap it in a success envelope
    if (body == null) {
      return ApiResponse.success(null);
    }

    // 2. If it is already an ApiResponse (wrapped manually) or an ErrorResponse, do not wrap again
    if (body instanceof ApiResponse || body instanceof ErrorResponse) {
      return body;
    }

    // 3. Automatically wrap the return value in a success ApiResponse
    return ApiResponse.success(body);
  }
}