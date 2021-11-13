package com.entelgy.servicetest.service;

import com.entelgy.servicetest.exception.BusinessClientException;
import com.entelgy.servicetest.rest.UserClient;
import com.entelgy.servicetest.rest.model.ResponseService;
import com.entelgy.servicetest.service.model.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  private final UserClient userClient;

  public UserServiceImpl(UserClient userClient) {
    this.userClient = userClient;
  }

  @Override
  public User user() {

    ResponseService responseService = userClient.getUser();
    User user = new User();
    if (responseService != null) {
      List<String> listUser = userClient.getUser().getData().stream()
          .map(userDto -> userDto.getId() + "|" + userDto.getLast_name() + "|" + userDto.getEmail())
          .collect(Collectors.toList());
      user.setData(listUser);
      return user;
    } else {
      throw new BusinessClientException("Error en el servicio User");
    }
  }

}
