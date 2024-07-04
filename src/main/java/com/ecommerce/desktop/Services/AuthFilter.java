package com.ecommerce.desktop.Services;

import com.ecommerce.desktop.Model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authManager;

  public AuthFilter(AuthenticationManager authManager) {
    this.authManager = authManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      BufferedReader reader = request.getReader();
      StringWriter writer = new StringWriter();
      String line;
      while ((line = reader.readLine()) != null) {
        writer.write(line);
      }

      ObjectMapper mapper = new ObjectMapper();
      User user = mapper.readValue(writer.toString(), User.class);

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          user.getEmail(), user.getPassword());

      return authManager.authenticate(authenticationToken);
    } catch (IOException e) {
      throw new RuntimeException("Error reading request", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    String token = JWTService.generateToken(authResult.getName());
    response.addHeader("Authorization", "Bearer " + token);
  }
}
