package com.kocfinans.scoresegment.util;

import com.kocfinans.scoresegment.entity.MonthlyIncomeBracket;
import com.kocfinans.scoresegment.entity.User;
import com.kocfinans.scoresegment.repository.MonthlyIncomeBracketRepository;
import com.kocfinans.scoresegment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class DBInitializerUtil{
  private final UserRepository userRepository;
  private final MonthlyIncomeBracketRepository monthlyIncomeBracketRepository;

  @PostConstruct
  private void initDb(){
    if (userRepository.count() <= 0){
      creteUser();
    }
    if (monthlyIncomeBracketRepository.count() <= 0){
      createMonthlyIncomeBracket();
    }
  }

  private void creteUser(){
    User user1 = User.builder()
        .name("Furkan")
        .surname("Demirtürk")
        .cityCode(1)
        .phoneNumber("000000")
        .scoreSegment(1)
        .transactionDate(LocalDateTime.now())
        .monthlyIncomeBracketCode(1)
        .tcNo("11111111111").build();
    User user2 = User.builder()
        .name("Ali")
        .surname("Yılmaz")
        .cityCode(2)
        .phoneNumber("000000")
        .scoreSegment(5)
        .transactionDate(LocalDateTime.now())
        .monthlyIncomeBracketCode(2)
        .tcNo("22222222222").build();
    User user3 = User.builder()
        .name("Ayşe")
        .surname("Öztürk")
        .cityCode(3)
        .phoneNumber("000000")
        .scoreSegment(9)
        .transactionDate(LocalDateTime.now())
        .monthlyIncomeBracketCode(3)
        .tcNo("33333333333").build();

    userRepository.saveAll(Arrays.asList(user1,user2,user3));
  }

  private void createMonthlyIncomeBracket(){
    MonthlyIncomeBracket monthlyIncomeBracket1 = MonthlyIncomeBracket.builder().code(1).description("0-2999TL").multiplier(800).build();
    MonthlyIncomeBracket monthlyIncomeBracket2 = MonthlyIncomeBracket.builder().code(2).description("3000TL-4999TL").multiplier(1000).build();
    MonthlyIncomeBracket monthlyIncomeBracket3 = MonthlyIncomeBracket.builder().code(3).description("5000TL-7999TL").multiplier(1200).build();
    MonthlyIncomeBracket monthlyIncomeBracket4 = MonthlyIncomeBracket.builder().code(4).description("8000TL-11999TL").multiplier(1500).build();
    MonthlyIncomeBracket monthlyIncomeBracket5 = MonthlyIncomeBracket.builder().code(5).description("12000TL ve üzeri ").multiplier(2000).build();

    monthlyIncomeBracketRepository.saveAll(Arrays.asList(monthlyIncomeBracket1,monthlyIncomeBracket2,monthlyIncomeBracket3,monthlyIncomeBracket4,monthlyIncomeBracket5));
  }

}
