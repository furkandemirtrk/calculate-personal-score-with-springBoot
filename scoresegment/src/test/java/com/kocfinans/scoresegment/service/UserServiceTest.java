package com.kocfinans.scoresegment.service;

import com.kocfinans.scoresegment.exception.ScoreSegmentException;
import com.kocfinans.scoresegment.model.UserDto;
import com.kocfinans.scoresegment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest{

  @InjectMocks
  UserService userService;

  @Test
  public void createUserTest() throws ScoreSegmentException{
    UserDto userDto = UserDto.builder()
        .cityCode(1)
        .monthlyIncomeBracketCode(1)
        .name("Test")
        .surname("Data")
        .phoneNumber("054000000")
        .tcNo("12345678912")
        .scoreSegment(4)
        .transactionDate(LocalDateTime.now()).build();
    try{
      Assert.assertEquals(userDto, userService.createUser(userDto));
    }catch (Exception e){
      log.error("createUserTest error : ",e);
    }

  }
}
