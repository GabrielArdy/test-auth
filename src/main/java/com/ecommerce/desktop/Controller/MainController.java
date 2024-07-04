package com.ecommerce.desktop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.desktop.DTO.LoginRequest;
import com.ecommerce.desktop.DTO.LoginResponse;
import com.ecommerce.desktop.DTO.RegisterResponse;
import com.ecommerce.desktop.DTO.UserDTO;
import com.ecommerce.desktop.Model.User;
import com.ecommerce.desktop.Services.Authentication;

@Controller
@RequestMapping(path = "/api")
public class MainController {

  @Autowired
  private Authentication authService;

  @PostMapping("/register")
  public @ResponseBody RegisterResponse register(@RequestBody User newUser) {
    UserDTO userDTO = new UserDTO(newUser.getEmail(), newUser.getName());
    if (authService.register(newUser)) {
      return new RegisterResponse("200", "User registered successfully", userDTO);
    } else {
      return new RegisterResponse("500", "User registration failed", null);

    }
  }

  @PostMapping("/login")
  public @ResponseBody LoginResponse login(@RequestBody LoginRequest loginRequest) {
    if (authService.login(loginRequest.getEmail(), loginRequest.getPassword())) {
      return new LoginResponse("200", "Login successful", "token");
    } else {
      return new LoginResponse("500", "Login failed", null);
    }
  }

  @GetMapping("/test")
  public @ResponseBody String test() {
    return "Hello World";
  }

}
