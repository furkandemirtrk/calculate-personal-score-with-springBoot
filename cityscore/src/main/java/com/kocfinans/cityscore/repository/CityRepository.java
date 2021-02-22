package com.kocfinans.cityscore.repository;

import com.kocfinans.cityscore.entity.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends MongoRepository<City, String>{
  City findByCode(Integer code);
}
