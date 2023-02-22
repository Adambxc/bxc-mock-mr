package com.example.nexusmr.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OktaUser {

  @JsonProperty("id")
  public String id;

  @JsonProperty("status")
  public String status;

  @JsonProperty("created")
  public String created;

  @JsonProperty("activated")
  public Object activated;

  @JsonProperty("statusChanged")
  public String statusChanged;

  @JsonProperty("lastLogin")
  public String lastLogin;

  @JsonProperty("lastUpdated")
  public String lastUpdated;

  @JsonProperty("passwordChanged")
  public String passwordChanged;

}
