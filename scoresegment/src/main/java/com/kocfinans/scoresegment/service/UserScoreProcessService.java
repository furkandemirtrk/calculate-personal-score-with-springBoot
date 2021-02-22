package com.kocfinans.scoresegment.service;

import com.kocfinans.scoresegment.entity.MonthlyIncomeBracket;
import com.kocfinans.scoresegment.exception.ScoreSegmentException;
import com.kocfinans.scoresegment.exception.enums.ErrorCodeEnum;
import com.kocfinans.scoresegment.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserScoreProcessService{
  private final MonthlyIncomeBracketService monthlyIncomeBracketService;
  private final CityScoreRestClient cityScoreRestClient;

  /**
   * Calculate user score
   * @param userDto
   * @return
   * @throws ScoreSegmentException
   */
  public int userScoreProcess(UserDto userDto) throws ScoreSegmentException{
    log.info("userScoreProcess start");
    if (userDto == null || userDto.getCityCode() == null || userDto.getMonthlyIncomeBracketCode() == null || userDto.getId() == null){
      log.error("userDto validation error");
      throw new ScoreSegmentException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    MonthlyIncomeBracket monthlyIncomeBracket = monthlyIncomeBracketService.monthlyIncomeBracketByCode(userDto.getMonthlyIncomeBracketCode());
    int cityScore = cityScoreRestClient.getCityScoreByCode(userDto.getCityCode());
    int totalScore =  userDto.getScoreSegment() * monthlyIncomeBracket.getMultiplier() + cityScore ;
    sendSMS(totalScore, userDto);
    return totalScore;
  }

  /**
   * Send SMS
   * @param totalScore
   * @param user
   */
  private void sendSMS(int totalScore, UserDto user){
    log.info("Sending SMS...");
    log.info(String.format("Sayın %s toplam skorunuz : %s",user.getFullName(), totalScore));
  }
}
