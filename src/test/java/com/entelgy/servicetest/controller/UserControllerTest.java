package com.entelgy.servicetest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.entelgy.servicetest.service.IUserService;
import com.entelgy.servicetest.service.model.User;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IUserService iUserService;

  @Test
  void testUser() throws Exception {

    String user1 = "1|Bluth|george.bluth@reqres.in";
    String user2 = "2|Weaver|janet.weaver@reqres.in";
    String user3 = "3|Wong|emma.wong@reqres.in";
    String user4 = "4|Holt|eve.holt@reqres.in";
    String user5 = "5|Morris|charles.morris@reqres.in";
    String user6 = "6|Ramos|tracey.ramos@reqres.in";

    List<String> listUser = Arrays.asList(user1, user2, user3, user4, user5, user6);
    User user = new User();
    user.setData(listUser);

    Mockito.when(iUserService.user()).thenReturn(user);
    mockMvc.perform(post("/service/v1/user").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.data").isArray());
  }
}