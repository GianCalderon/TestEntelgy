package com.entelgy.servicetest.controller;

import com.entelgy.servicetest.service.IUserService;
import com.entelgy.servicetest.service.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "service/v1/")
public class UserController {

  private final IUserService iUserService;

  public UserController(IUserService iUserService) {
    this.iUserService = iUserService;
  }

  @PostMapping(value = "user")
  public User user() {
    System.out.println("ingresando");
    return iUserService.user();
  }


}
