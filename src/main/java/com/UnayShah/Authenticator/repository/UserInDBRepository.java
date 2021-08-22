package com.UnayShah.Authenticator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.UnayShah.Authenticator.dao.UserInDB;

@Repository
public interface UserInDBRepository extends MongoRepository<UserInDB, String> {

	@Query(value = "{$and :[{_id: ?0},{password: ?1},{websiteId: ?2}] }")
	public UserInDB findByAllParameters(String username, String password, String websiteId);

}