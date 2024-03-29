package com.kocfinans.scoresegment.repository;

import com.kocfinans.scoresegment.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String>{
  /**
   * Find user by tc
   * @param tcNo
   * @return
   */
  User findByTcNo(String tcNo);
}
