package com.kocfinans.scoresegment.contoller;

import com.kocfinans.scoresegment.exception.ScoreSegmentException;
import com.kocfinans.scoresegment.exception.enums.ErrorCodeEnum;
import com.kocfinans.scoresegment.model.UserDto;
import com.kocfinans.scoresegment.model.UserScoreCalculateRequest;
import com.kocfinans.scoresegment.service.UserScoreProcessService;
import com.kocfinans.scoresegment.service.UserService;
import com.kocfinans.scoresegment.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@Data
@RequestMapping(ApiPaths.UserController.CONTROLLER)
@Api(value = ApiPaths.UserController.CONTROLLER, tags = { "User APIs" })
@AllArgsConstructor
public class UserController{

  private final UserService userService;
  private final UserScoreProcessService userScoreProcessService;


  @ApiOperation(value = "Get all users", response = UserDto.class)
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    log.info("Getting all users.");
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @ApiOperation(value = "User Score Calculate", response = UserDto.class)
  @PostMapping
  public ResponseEntity<Integer> userScoreCalculate(@RequestBody UserScoreCalculateRequest userScoreCalculateRequest) throws ScoreSegmentException{
    log.info("createUser");
    UserDto userDto = userService.findUserByTcNo(userScoreCalculateRequest.getTcNo());
    if (userDto == null){
      throw new ScoreSegmentException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    if (!(userDto.getName().equals(userScoreCalculateRequest.getName()) && userDto.getSurname().equals(userScoreCalculateRequest.getSurname()) && userDto.getPhoneNumber().equals(userScoreCalculateRequest.getPhoneNumber()))){
      throw new ScoreSegmentException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }else {
      userDto = userService.updateUser(userScoreCalculateRequest);
      int totalScore = userScoreProcessService.userScoreProcess(userDto);
      return ResponseEntity.ok(totalScore);
    }
  }
}
