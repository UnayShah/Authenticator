package com.UnayShah.Authenticator.repository;

import java.util.Optional;

import com.UnayShah.Authenticator.model.UserActive;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActiveRepository extends MongoRepository<UserActive, String> {

    /**
     * find username and session id combination for active user
     * 
     * @param sessionId
     * @param username
     * @return
     */
    @Query(value = "{$and :[{_id: ?0},{sessionId: ?1}] }")
    public Optional<UserActive> findByAllParameters(String username, String sessionId);
}
