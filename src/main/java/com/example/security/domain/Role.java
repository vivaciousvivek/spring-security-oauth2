package com.example.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author VIVEK KUMAR SINGH
 * @since (2018-04-30 16:51:31)
 */
@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int rollId;

  private String role;

  public Role() {}

  public int getRollId() {
    return rollId;
  }

  public void setRollId(int rollId) {
    this.rollId = rollId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "Role{" + "rollId=" + rollId + ", role='" + role + '\'' + '}';
  }
}
