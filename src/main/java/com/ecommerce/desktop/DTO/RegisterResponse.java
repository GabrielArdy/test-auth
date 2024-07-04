package com.ecommerce.desktop.DTO;

public class RegisterResponse {

  private String statusCode;
  private String message;
  private UserDTO user;

  public RegisterResponse(String statusCode, String message, UserDTO user) {
    this.statusCode = statusCode;
    this.message = message;
    this.user = user;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

}
