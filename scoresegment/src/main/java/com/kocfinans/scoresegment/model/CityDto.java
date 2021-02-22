package com.kocfinans.scoresegment.model;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CityDto{
  private String name;
  private Integer code;
  private Integer score;

}
