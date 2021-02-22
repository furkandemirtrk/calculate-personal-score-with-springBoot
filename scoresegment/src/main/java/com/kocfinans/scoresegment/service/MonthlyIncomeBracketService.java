package com.kocfinans.scoresegment.service;

import com.kocfinans.scoresegment.entity.MonthlyIncomeBracket;
import com.kocfinans.scoresegment.exception.ScoreSegmentException;
import com.kocfinans.scoresegment.exception.enums.ErrorCodeEnum;
import com.kocfinans.scoresegment.model.MonthlyIncomeBracketDto;
import com.kocfinans.scoresegment.repository.MonthlyIncomeBracketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class MonthlyIncomeBracketService{
  private final MonthlyIncomeBracketRepository monthlyIncomeBracketRepository;

  public List<MonthlyIncomeBracketDto> getAllMonthlyIncomeBracket(){
    List<MonthlyIncomeBracket> monthlyIncomeBracketList  =  monthlyIncomeBracketRepository.findAll();
    List<MonthlyIncomeBracketDto> monthlyIncomeBracketDtoList = new ArrayList<>();
    monthlyIncomeBracketList.forEach(monthlyIncomeBracket -> monthlyIncomeBracketDtoList.add(MonthlyIncomeBracketDto.mapToMonthlyIncomeBracketDto(monthlyIncomeBracket)));
    return monthlyIncomeBracketDtoList;
  }

  /**
   *
   * @param code
   * @return
   * @throws ScoreSegmentException
   */
  public MonthlyIncomeBracket monthlyIncomeBracketByCode(int code) throws ScoreSegmentException{
    MonthlyIncomeBracket monthlyIncomeBracket = monthlyIncomeBracketRepository.findByCode(code);
    if (monthlyIncomeBracket == null){
      throw new ScoreSegmentException(ErrorCodeEnum.FIELD_VALIDATION_ERROR);
    }
    return monthlyIncomeBracket;
  }
}
