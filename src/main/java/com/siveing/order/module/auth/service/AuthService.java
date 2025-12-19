package com.siveing.order.module.auth.service;


import com.siveing.order.infrastructure.security.JwtService;
import com.siveing.order.module.auth.dto.LoginDTO;
import com.siveing.order.module.auth.dto.RegisterDTO;
import com.siveing.order.module.auth.response.LoginResponse;
import com.siveing.order.module.auth.response.RegisterResponse;
import com.siveing.order.module.role.repository.RoleRepository;
import com.siveing.order.module.user.entity.User;
import com.siveing.order.module.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public LoginResponse authenticate(LoginDTO request) {
    log.info("Attempting to authenticate user: {}", request.getEmail());
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getEmail(),
              request.getPassword()
          )
      );
      var user = userRepository.findByEmail(request.getEmail())
          .orElseThrow();
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = "TESTING_REFRESH";
      log.info("User authenticated successfully: {}", request.getEmail());
      return LoginResponse.builder()
          .token(jwtToken)
          .refreshToken(refreshToken)
          .build();
    } catch (Exception e) {
      log.error("Authentication failed for {}: {}", request.getEmail(), e.getMessage());
      throw e;
    }
  }

  @Transactional
  public RegisterResponse register(RegisterDTO request) {
    log.info("Attempting to register user with email: {}", request.getEmail());
    try {
      var userRole = roleRepository.findByCode("ROLE_ADMIN")
          .orElseThrow(() -> new RuntimeException("Default role ROLE_ADMIN not found"));

      log.info("ROLE DATA: {}", userRole);
      var user = User.builder()
          .firstname(request.getFirstname())
          .lastname(request.getLastname())
          .email(request.getEmail())
          .password(passwordEncoder.encode(request.getPassword()))
          .role(userRole)
          .build();

      userRepository.save(user);
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = "TEST_REFRESH";
      log.info("User registered successfully: {}", request.getEmail());
      return RegisterResponse.builder()
          .token(jwtToken)
          .refreshToken(refreshToken)
          .build();
    } catch (Exception e) {
      log.error("Registration failed for {}: {}", request.getEmail(), e.getMessage());
      throw e;
    }
  }
}
