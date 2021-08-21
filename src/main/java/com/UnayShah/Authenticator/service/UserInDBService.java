package com.UnayShah.Authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UnayShah.Authenticator.dao.UserInDB;
import com.UnayShah.Authenticator.repository.UserInDBRepository;

@Service
public class UserInDBService {
	@Autowired
	UserInDBRepository userInDBRepository;

	public UserInDB test() {
		for (UserInDB user : userInDBRepository.findAll())
			System.out.println(user.toString());
		System.out.println(userInDBRepository.findById("413cfacc-8db1-4075-872f-1cf4c75f7a5d"));
		System.out.println(userInDBRepository.findByAllParameters("413cfacc-8db1-4075-872f-1cf4c75f7a5d",
				"38c03e31-cb09-4afb-8afe-8ed08079c604", "003bf0bb-8847-44be-bba7-37970828f4e4"));
		return userInDBRepository.findByAllParameters("413cfacc-8db1-4075-872f-1cf4c75f7a5d",
				"38c03e31-cb09-4afb-8afe-8ed08079c604", "003bf0bb-8847-44be-bba7-37970828f4e4");
	}

	public UserInDB newUser(String username, String password, String websiteId) {
		return userInDBRepository.save(new UserInDB(username, password, websiteId));
	}

	public UserInDB editUser(String username, String password, String websiteId, String newPassword,
			String newWebsiteId) {
		if (userInDBRepository.findByAllParameters(username, password, websiteId) != null) {
			userInDBRepository.save(new UserInDB(username, newPassword, newWebsiteId));
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