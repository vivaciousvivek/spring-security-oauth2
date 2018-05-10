package com.example.security.service;

import com.example.security.domain.CustomUserDetails;
import com.example.security.domain.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * To authenticate a User or perform various role-based checks, Spring security needs to load users
 * details somehow.
 *
 * <p>For this purpose, It consists of an interface called UserDetailsService which has a single
 * method that loads a user based on username
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 17:02:06)
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
  @Autowired UserRepository userRepository;

  /**
   * @param username
   * @return UserDetails(We also implement this interface in) {@link CustomUserDetails} ) object that Spring Security uses for performing various authentication and
   *     role based validations
   * @throws UsernameNotFoundException
   */
  @Transactional
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(username);

    user.orElseThrow(() -> new UsernameNotFoundException("Username: \"" + username + "\" not Found!!!"));
    return user.map(CustomUserDetails::new).get();
  }
}
