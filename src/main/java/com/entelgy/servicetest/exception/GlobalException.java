package com.entelgy.servicetest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({BusinessClientException.class})
  @ResponseBody
  public Error businessException(BusinessClientException businessClientException) {

    Error messageError = new Error();

    messageError.setCodigo("E03");
    messageError.setExito(Boolean.FALSE);
    messageError.setMensaje(businessClientException.getMessage());
    return messageError;
  }
}
