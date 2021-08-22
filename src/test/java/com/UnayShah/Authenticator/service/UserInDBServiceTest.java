package com.UnayShah.Authenticator.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
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

	private static UserInDB userInDB;
	private static UserInDB editedUserInDB;
	private static String username;
	private static String password;
	private static String websiteId;
	private static String newPassword;
	private static String newWebsiteId;

	@BeforeAll
	public static void initialize() {
		username = UUID.randomUUID().toString();
		password = UUID.randomUUID().toString();
		websiteId = UUID.randomUUID().toString();
		newPassword = UUID.randomUUID().toString();
		newWebsiteId = UUID.randomUUID().toString();
		userInDB = new UserInDB(username, password, websiteId);
		editedUserInDB = new UserInDB(username, newPassword, newWebsiteId);
	}

	@Test
	@Order(1)
	public void registerUserTest() {
		assertTrue(new ReflectionEquals(userInDBService.newUser(username, password, websiteId), new String[0])
				.matches(userInDB));
	}

	@Test
	@Order(2)
	public void registerUserFailTest() {
		assertNull(userInDBService.newUser(username, password, websiteId));
	}

	@Test
	@Order(3)
	public void editUserTest() {
		assertTrue(
				new ReflectionEquals(userInDBService.editUser(username, password, websiteId, newPassword, newWebsiteId),
						new String[0]).matches(editedUserInDB));
	}

	@Test
	@Order(4)
	public void editUserFailTest() {
		assertNull(userInDBService.editUser(username, password, websiteId, newPassword, newWebsiteId));
	}

	@Test
	@Order(5)
	public void removeUserTest() {
		assertTrue(userInDBService.removeUser(username, newPassword, newWebsiteId));
	}

	@Test
	@Order(6)
	public void removeUserFailTest() {
		assertFalse(userInDBService.removeUser(username, newPassword, newWebsiteId));
	}
}
