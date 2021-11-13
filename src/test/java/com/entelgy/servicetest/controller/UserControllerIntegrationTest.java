package com.entelgy.servicetest.controller;

import com.entelgy.servicetest.service.model.User;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIntegrationTest {

  @Autowired
  private TestRestTemplate client;

  @LocalServerPort
  private int puerto;

  @Test
  void testUser() {

    ResponseEntity<User> response = client.postForEntity(crearUri("/service/v1/user"), null, User.class);
    List<String> listUsers = response.getBody().getData();

    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    Assert.assertEquals("1|Bluth|george.bluth@reqres.in", listUsers.get(0));

  }

  private String crearUri(String uri) {
    return "http://localhost:" + puerto + uri;
  }
}