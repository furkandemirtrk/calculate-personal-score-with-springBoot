package com.kocfinans.scoresegment.model;

import com.kocfinans.scoresegment.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class UserDto{
  private String id;
  private String tcNo;
  private String name;
  private String surname;
  private String phoneNumber;
  private Integer cityCode;
  private Integer scoreSegment;
  private Integer monthlyIncomeBracketCode;
  private LocalDateTime transactionDate;

  public static UserDto mapToUserDTO(User user) {
    return UserDto.builder()
        .id(user.getId())
        .name(user.getName())
        .surname(user.getSurname())
        .cityCode(user.getCityCode())
        .phoneNumber(user.getPhoneNumber())
        .scoreSegment(user.getScoreSegment())
        .tcNo(user.getTcNo())
        .transactionDate(user.getTransactionDate())
        .monthlyIncomeBracketCode(user.getMonthlyIncomeBracketCode())
        .build();
  }

  public String getFullName(){
    return this.name.concat(" ").concat(this.surname);
  }
}
