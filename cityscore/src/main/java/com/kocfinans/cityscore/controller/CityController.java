package com.kocfinans.cityscore.controller;

import com.kocfinans.cityscore.exception.CityScoreException;
import com.kocfinans.cityscore.exception.enums.ErrorCodeEnum;
import com.kocfinans.cityscore.model.CityDto;
import com.kocfinans.cityscore.service.CityService;
import com.kocfinans.cityscore.util.ApiPaths;
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
@RequestMapping(ApiPaths.CityController.CONTROLLER)
@Api(value = ApiPaths.CityController.CONTROLLER, tags = { "City APIs" })
@AllArgsConstructor
public class CityController{
  private final CityService cityService;

  @ApiOperation(value = "Get all cities", response = CityDto.class)
  @GetMapping
  public ResponseEntity<List<CityDto>> getAllCities(){
    log.info("Getting All Cities");
    return ResponseEntity.ok(cityService.getAllCities());
  }

  @ApiOperation(value = "Get city score By Code", response = CityDto.class)
  @GetMapping(value = "/{code}")
  public ResponseEntity<Integer> getCityScore(@PathVariable int code) throws CityScoreException{
    log.info("Getting City Score By Code");
    return ResponseEntity.ok(cityService.findCityDtoByCode(code).getScore());
  }
}
