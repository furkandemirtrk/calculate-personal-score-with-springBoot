package com.kocfinans.scoresegment.repository;

import com.kocfinans.scoresegment.entity.MonthlyIncomeBracket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MonthlyIncomeBracketRepository extends MongoRepository<MonthlyIncomeBracket, String>{
  MonthlyIncomeBracket findByCode(int code);
}
