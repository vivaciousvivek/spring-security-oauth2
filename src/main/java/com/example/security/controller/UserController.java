package com.example.security.controller;

import com.example.security.domain.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-05-02 14:44:32)
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public List<User> getAllUsers() {
    return userService.findAll();
  }

  @GetMapping("/{username}")
  public User getAllUsers(@PathVariable String username) {
    return userService.findByEmail(username).orElse(null);
  }
}
