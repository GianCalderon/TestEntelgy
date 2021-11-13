package com.entelgy.servicetest.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.entelgy.servicetest.data.Data;
import com.entelgy.servicetest.exception.BusinessClientException;
import com.entelgy.servicetest.rest.UserClient;
import com.entelgy.servicetest.rest.model.ResponseService;
import com.entelgy.servicetest.service.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

  @Mock
  UserClient userClient;

  @InjectMocks
  UserServiceImpl userServiceImpl;

  @Test
  void testTrasnsformationUserSucces() {

    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    mapper.registerModule(module);

    String json = Data.RESPONSE;
    ResponseService service = null;
    try {
      service = mapper.readValue(json, ResponseService.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    Mockito.when(userClient.getUser()).thenReturn(service);
    User newUser = userServiceImpl.user();

    assertNotNull(newUser);
    assertTrue(newUser.getData().size() > 0);
  }
  @Test
  void testTrasnsformationUserFailed() {

    Mockito.when(userClient.getUser()).thenReturn(null);
    Exception exception = assertThrows(BusinessClientException.class,()->{
      userServiceImpl.user();
    });
   assertEquals(BusinessClientException.class,exception.getClass());
  }
}