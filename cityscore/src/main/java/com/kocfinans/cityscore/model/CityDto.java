package com.kocfinans.cityscore.model;

import com.kocfinans.cityscore.entity.City;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CityDto{
  private String name;
  private Integer code;
  private Integer score;

  public static CityDto mapToCityDTO(City city) {
    return CityDto.builder()
        .code(city.getCode())
        .name(city.getName())
        .score(city.getScore())
        .build();
  }
}
