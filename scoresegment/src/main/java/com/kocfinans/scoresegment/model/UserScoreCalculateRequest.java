package com.kocfinans.scoresegment.model;

import lombok.*;


@Builder
@Data
public class UserScoreCalculateRequest{
  private int cityCode;
  private int monthlyIncomeBracketCode;
  private String phoneNumber;
  private String name;
  private String surname;
  private String tcNo;

}
