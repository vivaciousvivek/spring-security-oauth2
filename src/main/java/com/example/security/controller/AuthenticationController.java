package com.example.security.controller;

import com.example.security.domain.User;
import com.example.security.domain.security.LoginUser;
import com.example.security.service.UserService;
import com.example.security.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 15:41:27)
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private UserService userService;

  @Autowired
  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/singin")
  public ResponseEntity<?> generateToken(@Valid @RequestBody LoginUser loginUser) {
    final String jwt = userService.processUsername(loginUser);

    if (jwt == null) {
      return new ResponseEntity<>(
          new ApiResponse(false, "Invalid Email/Username!!!"), HttpStatus.NOT_FOUND);
    }

    return ResponseEntity.ok(new User());
//    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @PostMapping("/singup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpUser) {
    if (userService.findByEmail(signUpUser.getEmail()).isPresent()) {
      return new ResponseEntity<>(
          new ApiResponse(false, "Email/Username is already taken!!!"), HttpStatus.BAD_REQUEST);
    }

    /** Creating new user */
    User user = userService.saveUser(signUpUser);

    if (user != null) {
      URI location =
          ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("/users/{username}")
              .buildAndExpand(user.getEmail())
              .toUri();
      return ResponseEntity.created(location)
          .body(new ApiResponse(true, "User Registered Successfully!!!"));
    }

    return new ResponseEntity<>(
        new ApiResponse(false, "User Registrations Failed!!!"), HttpStatus.CONFLICT);
  }
}
