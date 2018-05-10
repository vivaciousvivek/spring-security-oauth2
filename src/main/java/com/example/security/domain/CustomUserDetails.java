package com.example.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
/**
 * We define a Custom UserDetails class that will implement UserDetails interface, and
 * return the CustomUserDetails object from loadUserByUsername() method in {@link com.example.security.service.CustomUserDetailService}.
 *
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 17:09:42)
 */
public class CustomUserDetails extends User implements UserDetails {

  public CustomUserDetails(final User user) {
    super(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
