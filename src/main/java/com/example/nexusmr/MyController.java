package com.example.nexusmr;

import com.example.nexusmr.Models.OktaGroup;
import com.example.nexusmr.Models.OktaUser;
import com.example.nexusmr.Models.User;
import com.example.nexusmr.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Requirements
 *
 * An endpoint that can page though okta users, writing/updating the users email, status, and groups to a
 * database.
 */


@RestController
public class MyController {
  private static String getUsersUrl = "https://dev-564329.oktapreview.com/api/v1/users?limit=25";
  private static String getUserGroupsUrl = "https://dev-564329.oktapreview.com/api/v1/users/{userId}/groups";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @GetMapping("users")
  public List<User> getUsers() throws URISyntaxException, JsonProcessingException {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Authorization", "SSWS 00E9PV0YTyIx2TJ6JWWIqQ-v3qSTAV05yeku0buC9I");
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    ResponseEntity<List<OktaUser>> result = restTemplate.exchange(getUsersUrl,
        HttpMethod.GET,
        httpEntity,
        new ParameterizedTypeReference<List<OktaUser>>() {});

    List<OktaUser> oktaUsers = result.getBody();

    List<User> users = new ArrayList<>();

    for (int i = 0; i <= oktaUsers.size(); i++) {
      OktaUser u = oktaUsers.get(i);

      var groups = getUsersGroups(u.id);

      String groupIds =
          objectMapper.writeValueAsString(
              groups.stream()
                  .map(OktaGroup::getId)
                  .collect(Collectors.toList()));

      users.add(User.builder()
              .id(u.id)
              .status(u.status)
              .groups(groupIds)
          .build());
    }

    for (int i = 0; i < oktaUsers.size(); i++) {
      User user = users.get(i);
      userRepository.save(user);
    }

    return users;
  }

  @GetMapping("groups")
  public List<OktaGroup> getUsersGroups(String userId) {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Authorization", "SSWS 00E9PV0YTyIx2TJ6JWWIqQ-v3qSTAV05yeku0buC9I");
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    String url = getUserGroupsUrl.replace("{userId}", userId);
    var result = restTemplate.exchange(
        url,
        HttpMethod.GET,
        httpEntity,
        new ParameterizedTypeReference<List<OktaGroup>>() {});


    return result.getBody();
  }
}
