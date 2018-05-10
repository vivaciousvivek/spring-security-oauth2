package com.example.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 16:46:39)
 */
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotBlank
  @Size(max = 40)
  @Email
  private String email;

//  @JsonIgnore
  @NotBlank
  @Size(max = 100)
  private String password;

  @NotBlank
  @Size(min = 2, max = 30)
  private String name;

  private long mobile;

  private boolean active;

  //  private Set<Role> roles;

  public User() {}

  public User(
      @NotBlank @Size(max = 40) @Email String email,
      @NotBlank @Size(min = 8, max = 30) String password,
      @NotBlank @Size(min = 2, max = 30) String name,
      long mobile,
      boolean active) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.mobile = mobile;
    this.active = active;
  }

  public User(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getName();
    this.mobile = user.getMobile();
    this.active = user.isActive();
    //    this.roles = user.getRoles();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getMobile() {
    return mobile;
  }

  public void setMobile(long mobile) {
    this.mobile = mobile;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  //  public Set<Role> getRoles() {
  //    return roles;
  //  }
  //
  //  public void setRoles(Set<Role> roles) {
  //    this.roles = roles;
  //  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", email='"
        + email
        + '\''
        + ", password="
        + password
        + ", name='"
        + name
        + '\''
        + ", mobile="
        + mobile
        + ", active="
        + active
        //        + ", roles="
        //        + roles
        + '}';
  }
}
