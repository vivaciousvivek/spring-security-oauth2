package com.example.security.service;

import com.example.security.domain.User;
import com.example.security.domain.security.LoginUser;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-05-02 14:01:02)
 */
@Service
public class UserServiceImpl implements UserService {
  private AuthenticationManager authenticationManager;
//  private JwtTokenProvider jwtTokenProvider;
  private PasswordEncoder passwordEncoder;
  private UserRepository userRepository;
  //    private RoleRepository roleRepository;

  @Autowired
  public UserServiceImpl(
      AuthenticationManager authenticationManager,
//      JwtTokenProvider jwtTokenProvider,
      PasswordEncoder passwordEncoder,
      UserRepository userRepository) {
    this.authenticationManager = authenticationManager;
//    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  public String processUsername(LoginUser loginUser) {
    final Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final Optional<User> user = findByEmail(loginUser.getUsername());
    if (user.isPresent()) {
//      return jwtTokenProvider.generateToken(user.get());
    }
    return null;
  }

  @Override
  public User saveUser(User signUpUser) {

    User newUser =
        new User(
            signUpUser.getEmail(),
            signUpUser.getPassword(),
            signUpUser.getName(),
            signUpUser.getMobile(),
            true);
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

    /** Set Roles */
    /*Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new AppException("User Role not set."));

    user.setRoles(Collections.singleton(userRole));*/
    return userRepository.save(newUser);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
