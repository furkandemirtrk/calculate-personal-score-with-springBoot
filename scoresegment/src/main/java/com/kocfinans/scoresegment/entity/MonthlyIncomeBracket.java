package com.kocfinans.scoresegment.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Builder
@Data
@Document(collection = "MonthlyIncomeBracket")
public class MonthlyIncomeBracket{
  @Id
  private String id;
  private Integer code;
  private String description;
  private Integer multiplier;
}
