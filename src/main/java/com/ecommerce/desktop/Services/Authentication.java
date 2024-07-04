package com.ecommerce.desktop.Services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.desktop.Model.User;
import com.ecommerce.desktop.Repository.UserRepository;

@Service
public class Authentication {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public @ResponseBody boolean register(User newUser) {
    newUser.setId(UUID.randomUUID().toString());
    newUser.setPassword(hashPassword(newUser.getPassword()));
    newUser.setRole("USER");
    newUser.setCreatedAt(new Date().toString());
    newUser.setUpdatedAt(new Date().toString());
    userRepository.save(newUser);
    return true;
  }

  public boolean login(String email, String password) {
    User user = userRepository.findByEmail(email);
    if (user != null) {
      return checkPassword(password, user.getPassword());
    }
    return false;
  }

  public String hashPassword(String password) {
    return passwordEncoder.encode(password);
  }

  public boolean checkPassword(String password, String hashedPassword) {
    return passwordEncoder.matches(password, hashedPassword);
  }

}
