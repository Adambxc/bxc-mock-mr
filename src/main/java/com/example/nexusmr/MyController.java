package com.example.nexusmr;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

@RestController
public class MyController {


  private static String url = "https://api.sampleapis.com/switch/games";

  @GetMapping("my/games")
  public String getGames() throws URISyntaxException {
    RestTemplate restTemplate = new RestTemplate();
    var result = restTemplate.getForObject(url, String.class);

    return result;

  }
}
