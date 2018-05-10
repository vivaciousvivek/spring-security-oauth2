package com.example.security.service;

import com.example.security.domain.User;
import com.example.security.domain.security.LoginUser;

import java.util.List;
import java.util.Optional;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018 - 05 - 02 14 : 34 : 39)
 */
public interface UserService {
  String processUsername(LoginUser loginUser);

  User saveUser(User user);

  Optional<User> findByEmail(String email);

  List<User> findAll();
}
