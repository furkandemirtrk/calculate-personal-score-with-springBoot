package com.kocfinans.scoresegment.service;

import com.kocfinans.scoresegment.entity.User;
import com.kocfinans.scoresegment.model.UserDto;
import com.kocfinans.scoresegment.model.UserScoreCalculateRequest;
import com.kocfinans.scoresegment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;

  public List<UserDto> getAllUsers(){
    List<User> users =  userRepository.findAll();
    List<UserDto> userDtoList = new ArrayList<>();
    users.forEach(user -> userDtoList.add(UserDto.mapToUserDTO(user)));
    return userDtoList;
  }

  public UserDto createUser(UserDto userDto){
    User createdUser = userRepository.save(User.builder()
        .cityCode(userDto.getCityCode())
        .name(userDto.getName())
        .surname(userDto.getSurname())
        .phoneNumber(userDto.getPhoneNumber())
        .scoreSegment(userDto.getScoreSegment())
        .tcNo(userDto.getTcNo())
        .transactionDate(LocalDateTime.now())
        .build());
    UserDto response = UserDto.mapToUserDTO(createdUser);
    return response;
  }

  public UserDto updateUser(UserScoreCalculateRequest userScoreCalculateRequest){
    User user = userRepository.findByTcNo(userScoreCalculateRequest.getTcNo());
    UserDto newUserDto ;
    user.setCityCode(userScoreCalculateRequest.getCityCode());
    user.setMonthlyIncomeBracketCode(userScoreCalculateRequest.getMonthlyIncomeBracketCode());
    user.setTransactionDate(LocalDateTime.now());
    newUserDto = UserDto.mapToUserDTO(userRepository.save(user));
    return newUserDto;
  }

  public User findUserById(String id){
    return userRepository.findById(id).get();
  }

  public UserDto findUserByTcNo(String tc){
    User user = userRepository.findByTcNo(tc);
    if (user != null){
      return UserDto.mapToUserDTO(user);
    }
    return null;
  }
}
