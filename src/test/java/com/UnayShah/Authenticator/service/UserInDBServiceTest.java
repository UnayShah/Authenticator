package com.UnayShah.Authenticator.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UnayShah.Authenticator.dao.UserInDB;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserInDBServiceTest {
	@Autowired
	UserInDBService userInDBService;
	String username = UUID.randomUUID().toString();
	String password = UUID.randomUUID().toString();
	String websiteId = UUID.randomUUID().toString();
	String newPassword = UUID.randomUUID().toString();
	String newWebsiteId = UUID.randomUUID().toString();

	@Test
	@Order(1)
	public void registerUserTest() {
		assertEquals(userInDBService.newUser(username, password, websiteId),
				new UserInDB(username, password, websiteId));
	}

	@Test
	@Order(2)
	public void editUserTest() {
		assertEquals(userInDBService.editUser(username, password, websiteId, newPassword, newWebsiteId),
				new UserInDB(username, password, websiteId));
	}

	@Test
	@Order(3)
	public void removeUserTest() {
		assertTrue(userInDBService.removeUser(username, newPassword, newWebsiteId));
	}
}
