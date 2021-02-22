package com.kocfinans.cityscore.service;

import com.kocfinans.cityscore.entity.City;
import com.kocfinans.cityscore.model.CityDto;
import com.kocfinans.cityscore.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class CityService{
  private final CityRepository cityRepository;

  public List<CityDto> getAllCities(){
    List<City> cityList = cityRepository.findAll();
    List<CityDto> cityDtoList = new ArrayList<>();
    cityList.forEach(city -> cityDtoList.add(CityDto.mapToCityDTO(city)));
    return cityDtoList;
  }

  public CityDto findCityDtoByCode(int code){
    return CityDto.mapToCityDTO(cityRepository.findByCode(code));
  }
  public City findCityByCode(int code){
    return cityRepository.findByCode(code);
  }
}
