package com.kocfinans.scoresegment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kocfinans.scoresegment.exception.ScoreSegmentException;
import com.kocfinans.scoresegment.exception.enums.ErrorCodeEnum;
import com.kocfinans.scoresegment.model.CityCodeRequest;
import com.kocfinans.scoresegment.model.CityDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Data
@Component
@Slf4j
public class CityScoreRestClient{
  private static final String BASE_URL = "http://localhost:8091/api";
  private static final String CITY_URL = BASE_URL + "/city";
  private ObjectMapper objectMapper = new ObjectMapper();

  public CityScoreRestClient(){
  }

  public int getCityScoreByCode(int cityCode) throws ScoreSegmentException{
    try{
      Map<String, String> params = new HashMap<>();
      params.put("code", String.valueOf(cityCode));

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(org.springframework.http.MediaType.valueOf("application/json;charset=UTF-8"));

      HttpEntity<String> request = new HttpEntity<>(null, headers);

      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(CITY_URL + "/{code}");
      String url = builder.buildAndExpand(params).toUri().toString();

      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<Object> objectResponse = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
      if (objectResponse.getStatusCode() != HttpStatus.OK){
        throw new ScoreSegmentException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
      } else {
        log.info("get city score ok");
        return (int)objectResponse.getBody();
      }
    }
    catch (Exception e){
      log.error("getCityDtoByCode error : ",e);
      throw new ScoreSegmentException(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
    }
  }
}
