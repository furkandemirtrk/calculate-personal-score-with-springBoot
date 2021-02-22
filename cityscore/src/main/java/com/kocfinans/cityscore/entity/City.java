package com.kocfinans.cityscore.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Builder
@Data
@Document(collection = "City")
public class City{
  @Id
  private String id;
  private String name;
  private Integer code;
  private Integer score;
}
