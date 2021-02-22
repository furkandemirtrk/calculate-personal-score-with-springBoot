package com.kocfinans.scoresegment.contoller;

import com.kocfinans.scoresegment.model.MonthlyIncomeBracketDto;
import com.kocfinans.scoresegment.service.MonthlyIncomeBracketService;
import com.kocfinans.scoresegment.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@Data
@RequestMapping(ApiPaths.MonthlyIncomeBracketController.CONTROLLER)
@Api(value = ApiPaths.MonthlyIncomeBracketController.CONTROLLER, tags = { "Monthly Income Bracket APIs" })
@AllArgsConstructor
public class MonthlyIncomeBracketController{

  private final MonthlyIncomeBracketService monthlyIncomeBracketService;

  @ApiOperation(value = "Getting all Monthly Income Bracket", response = MonthlyIncomeBracketDto.class)
  @GetMapping
  public ResponseEntity<List<MonthlyIncomeBracketDto>> getAllMonthlyIncomeBracket() {
    log.info("Getting all Monthly Income Bracket.");
    return ResponseEntity.ok(monthlyIncomeBracketService.getAllMonthlyIncomeBracket());
  }
}
