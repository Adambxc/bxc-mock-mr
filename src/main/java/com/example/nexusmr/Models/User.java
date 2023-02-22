package com.example.nexusmr.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  public String id;

  public String status;

  @Column(name = "user_groups")
  public String groups;
}
