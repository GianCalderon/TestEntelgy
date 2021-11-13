package com.entelgy.servicetest.rest;

import com.entelgy.servicetest.rest.model.ResponseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserClient {

  @Value("${app.rest.user.url}")
  private String wsUserUrl;

  private final RestTemplate restTemplate = new RestTemplate();

  public ResponseService getUser() {

    ResponseEntity<ResponseService> responseService = restTemplate.getForEntity(wsUserUrl, ResponseService.class);
    return responseService.getStatusCode().equals(HttpStatus.OK) ? responseService.getBody() : null;

  }
}
