package com.UnayShah.Authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UnayShah.Authenticator.dao.UserInDB;
import com.UnayShah.Authenticator.repository.UserInDBRepository;

@Service
public class UserInDBService {
	@Autowired
	private UserInDBRepository userInDBRepository;

	public UserInDB newUser(String username, String password) {
		if (!userInDBRepository.existsById(username))
			return userInDBRepository.save(new UserInDB(username, password));
		else
			return null;
	}

	public Boolean findUser(String username, String password) {
		return userInDBRepository.findByCredentials(username, password).isPresent();
	}

	public Boolean registeredUser(String username, String password, String websiteId) {
		return findUser(username, password)
				&& userInDBRepository.findWebsiteOptional(username, password, websiteId).isPresent();
	}

	public Boolean addWebsite(String username, String password, String websiteId) {
		if (findUser(username, password) && !registeredUser(username, password, websiteId)) {
			UserInDB userInDB = userInDBRepository.findByCredentials(username, password).get();
			userInDB.addWebsiteId(websiteId);
			userInDBRepository.save(userInDB);
			return true;
		}
		return false;
	}

	public Boolean removeWebsite(String username, String password, String websiteId) {
		if (registeredUser(username, password, websiteId)) {
			UserInDB userInDB = userInDBRepository.findByCredentials(username, password).get();
			userInDB.removeWebsiteId(websiteId);
			userInDBRepository.save(userInDB);
			return true;
		}
		return false;
	}

	public Boolean editUser(String username, String password, String newPassword) {
		if (findUser(username, password)) {
			UserInDB userInDB = userInDBRepository.findByCredentials(username, password).get();
			userInDB.setPassword(newPassword);
			userInDBRepository.save(userInDB);
			return true;
		}
		return false;
	}

	public Boolean removeUser(String username, String password) {
		if (findUser(username, password)) {
			userInDBRepository.deleteById(username);
			return !userInDBRepository.findById(username).isPresent();
		} else
			return false;
	}
}