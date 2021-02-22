package com.kocfinans.scoresegment.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;


@Builder
@Data
@Document(collection = "Users")
public class User{
  @Id
  private String id;

  @Indexed(unique=true)
  private String tcNo;

  private String name;
  private String surname;
  private String phoneNumber;
  private Integer cityCode;
  private Integer scoreSegment;
  private Integer monthlyIncomeBracketCode;
  private LocalDateTime transactionDate;

  public String getFullName(){
    return this.name.concat(" ").concat(this.surname);
  }
}
