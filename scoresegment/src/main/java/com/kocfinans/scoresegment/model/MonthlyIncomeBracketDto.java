package com.kocfinans.scoresegment.model;

import com.kocfinans.scoresegment.entity.MonthlyIncomeBracket;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class MonthlyIncomeBracketDto{
  private Integer code;
  private String description;
  private Integer multiplier;

  public static MonthlyIncomeBracketDto mapToMonthlyIncomeBracketDto(MonthlyIncomeBracket monthlyIncomeBracket) {
    return MonthlyIncomeBracketDto.builder()
        .code(monthlyIncomeBracket.getCode())
        .description(monthlyIncomeBracket.getDescription())
        .multiplier(monthlyIncomeBracket.getMultiplier())
        .build();
  }
}
