package com.example.security.domain.security;

import javax.validation.constraints.NotBlank;
/**
 * To map the credentials coming from the client
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-05-02 12:20:34)
 */
public class LoginUser {
  @NotBlank private String username;

  @NotBlank private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
