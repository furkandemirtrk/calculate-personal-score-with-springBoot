package com.kocfinans.cityscore.util;

import com.kocfinans.cityscore.entity.City;
import com.kocfinans.cityscore.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class DBInitializerUtil{
  private final CityRepository cityRepository;

  @PostConstruct
  private void initDb(){
    if (cityRepository.count() <=0){
      createCity();
    }
  }

  private void createCity(){
    City city1 = City.builder().code(1).name("Adana").score(10000).build();
    City city2 = City.builder().code(2).name("Adıyaman").score(6000).build();
    City city3 = City.builder().code(3).name("Afyonkarahisar").score(7500).build();
    City city4 = City.builder().code(4).name("Ağrı").score(9000).build();
    City city5 = City.builder().code(5).name("Aksaray").score(8000).build();
    City city6 = City.builder().code(6).name("Ankara").score(17500).build();
    City city7 = City.builder().code(34).name("İstanbul").score(18000).build();
    City city8 = City.builder().code(35).name("İzmir").score(16000).build();
    cityRepository.saveAll(Arrays.asList(city1,city2,city3,city4,city5,city6,city7,city8));
  }
}
