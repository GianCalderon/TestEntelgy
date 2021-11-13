package com.entelgy.servicetest.rest;

import com.entelgy.servicetest.data.Data;
import com.entelgy.servicetest.rest.model.ResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserClientTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private UserClient userClient;

  @Value("${app.rest.user.url}")
  private String url;


  @Test
  public void testUser() throws JsonProcessingException {

    ReflectionTestUtils.setField(userClient, "wsUserUrl", url);
    String json = Data.RESPONSE;
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseService responseService = objectMapper.readValue(json, ResponseService.class);

    Mockito.when(restTemplate.getForEntity(url, ResponseService.class))
        .thenReturn(new ResponseEntity(responseService, HttpStatus.OK));

    ResponseService serviceUser = userClient.getUser();
    Assert.assertEquals(responseService.getPage(), serviceUser.getPage());
  }
}