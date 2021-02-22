package com.kocfinans.cityscore.exception;

import com.kocfinans.cityscore.exception.enums.ErrorCodeEnum;
import lombok.Getter;


public class CityScoreException extends Exception{
  @Getter
  private final ErrorCodeEnum errorCodeEnum;

  public CityScoreException(ErrorCodeEnum errorCodeEnum){
    this.errorCodeEnum = errorCodeEnum;
  }
}
