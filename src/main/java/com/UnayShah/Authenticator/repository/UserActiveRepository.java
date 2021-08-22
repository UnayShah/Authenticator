package com.UnayShah.Authenticator.repository;

import java.util.Optional;

import com.UnayShah.Authenticator.dao.UserActive;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActiveRepository extends MongoRepository<UserActive, String> {

    @Query(value = "{$and :[{_id: ?0},{username: ?1}] }")
    public Optional<UserActive> findByAllParameters(String sessionId, String username);
}
