package com.entelgy.servicetest.service.model;

import java.util.List;

public class User {

  List<String> data;

  public User() {
  }

  public User(List<String> data) {
    this.data = data;
  }

  public List<String> getData() {
    return data;
  }

  public void setData(List<String> data) {
    this.data = data;
  }
}
