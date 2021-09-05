package com.UnayShah.Authenticator.repository;

import com.UnayShah.Authenticator.dao.AttemptLogin;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptLoginRepository extends MongoRepository<AttemptLogin, String> {
}
