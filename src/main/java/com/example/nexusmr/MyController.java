package com.example.nexusmr;

import Models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;


@RestController
public class MyController {


  private static String url = "https://api.sampleapis.com/switch/games";
  private static String getUsersUrl = "https://dev-564329.oktapreview.com/api/v1/users?limit=25";
//  private static String getUsersUrl = "https://dev-564329.oktapreview.com/api/v1/users/00u1ipagvvfKW0opj0h8";

  private static String getUserGroupsUrl = "https://dev-564329.oktapreview.com/api/v1/users/{userId}/groups";



  @GetMapping("my/games")
  public String getGames() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    var result = restTemplate.getForObject(url, String.class);

    return result;
  }
  @GetMapping("users")
  public ResponseEntity<List<User>> getUsers() throws URISyntaxException, JsonProcessingException {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Authorization", "SSWS 00E9PV0YTyIx2TJ6JWWIqQ-v3qSTAV05yeku0buC9I");
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);
//    var result = restTemplate.exchange(getUsersUrl, HttpMethod.GET, httpEntity, User[].class);



    var result = restTemplate.exchange(getUsersUrl, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<User>>() {});




    return result;
  }

  public String getUsersGroups(String userId) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("Authorization", "SSWS 00E9PV0YTyIx2TJ6JWWIqQ-v3qSTAV05yeku0buC9I");
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);
    String url = getUserGroupsUrl.replace("{userId}", userId);
    var result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);


    return result.getBody();
  }
}
