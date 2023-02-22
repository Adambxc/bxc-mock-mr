package com.example.nexusmr.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OktaGroup {
  @JsonProperty("id")
  public String id;

  @JsonProperty("created")
  public String created;

  @JsonProperty("lastUpdated")
  public String lastUpdated;

  @JsonProperty("lastMembershipUpdated")
  public String lastMembershipUpdated;

  @JsonProperty("type")
  public String type;

}
