package com.kocfinans.scoresegment.exception;

import com.kocfinans.scoresegment.exception.enums.ErrorCodeEnum;
import lombok.Getter;


public class ScoreSegmentException extends Exception{
  @Getter
  private final ErrorCodeEnum errorCodeEnum;

  public ScoreSegmentException(ErrorCodeEnum errorCodeEnum){
    this.errorCodeEnum = errorCodeEnum;
  }
}
