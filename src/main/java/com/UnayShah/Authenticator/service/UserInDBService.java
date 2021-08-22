package com.UnayShah.Authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UnayShah.Authenticator.dao.UserInDB;
import com.UnayShah.Authenticator.repository.UserInDBRepository;

@Service
public class UserInDBService {
	@Autowired
	UserInDBRepository userInDBRepository;

	public UserInDB newUser(String username, String password, String websiteId) {
		if (!userInDBRepository.existsById(username))
			return userInDBRepository.save(new UserInDB(username, password, websiteId));
		else
			return null;
	}

	public UserInDB editUser(String username, String password, String websiteId, String newPassword,
			String newWebsiteId) {
		System.out.println(userInDBRepository.findByAllParameters(username, password, websiteId));
		if (userInDBRepository.findByAllParameters(username, password, websiteId) != null) {
			return userInDBRepository.save(new UserInDB(username, newPassword, newWebsiteId));
		}
		return null;
	}

	public Boolean removeUser(String username, String password, String websiteId) {
		if (userInDBRepository.findByAllParameters(username, password, websiteId) != null) {
			userInDBRepository.deleteById(username);
			return userInDBRepository.findById(username).isEmpty();
		} else
			return false;
	}
}