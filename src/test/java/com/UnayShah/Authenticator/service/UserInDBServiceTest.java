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

import com.UnayShah.Authenticator.core.CommonConstants;
import com.UnayShah.Authenticator.model.UserActive;
import com.UnayShah.Authenticator.model.UserInDB;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserInDBServiceTest {
	@Autowired
	UserInDBService userInDBService;
	@Autowired
	UserActiveService userActiveService;

	private static UserInDB userInDB;
	private static UserActive userActive;
	private static String username;
	private static String password;
	private static String websiteId1;
	private static String newPassword;
	private static String websiteId2;

	@BeforeAll
	public static void initialize() {
		username = UUID.randomUUID().toString();
		password = UUID.randomUUID().toString();
		websiteId1 = CommonConstants.AUTHENTICATOR_WEBSITE_ID;
		newPassword = UUID.randomUUID().toString();
		websiteId2 = UUID.randomUUID().toString();
		userInDB = new UserInDB(username, password);
	}

	@Test
	@Order(1)
	public void newUserTest() {
		assertTrue(new ReflectionEquals(userInDBService.newUser(username, password), new String[0]).matches(userInDB));
	}

	@Test
	@Order(2)
	public void newUserFailTest() {
		assertNull(userInDBService.newUser(username, password));
	}

	@Test
	@Order(3)
	public void findUserTest() {
		assertTrue(userInDBService.findUser(username, password));
		assertFalse(userInDBService.findUser(username, newPassword));
	}

	@Test
	@Order(4)
	public void addWebsiteTest() {
		userActive = userActiveService.login(username, password, websiteId1);
		assertFalse(userInDBService.addWebsite(username, password, websiteId1, userActive.getSessionId()));
		assertTrue(userInDBService.addWebsite(username, password, websiteId2, userActive.getSessionId()));
	}

	@Test
	@Order(5)
	public void registeredUserTest() {
		assertTrue(userInDBService.registeredUser(username, password, websiteId1));
		assertFalse(userInDBService.registeredUser(username, newPassword, websiteId1));
		assertTrue(userInDBService.registeredUser(username, password, websiteId2));
	}

	@Test
	@Order(6)
	public void removeWebsiteTest() {
		assertTrue(userInDBService.removeWebsite(username, password, websiteId1, userActive.getSessionId()));
		assertFalse(userInDBService.removeWebsite(username, password, websiteId1, userActive.getSessionId()));
		assertTrue(userInDBService.removeWebsite(username, password, websiteId2, userActive.getSessionId()));
	}

	@Test
	@Order(7)
	public void editUserTest() {
		assertTrue(userInDBService.editUser(username, password, newPassword, userActive.getSessionId()));
		assertTrue(userInDBService.findUser(username, newPassword));
	}

	@Test
	@Order(8)
	public void editUserFailTest() {
		assertFalse(userInDBService.editUser(username, password, newPassword, userActive.getSessionId()));
	}

	@Test
	@Order(9)
	public void removeUserTest() {
		assertTrue(userInDBService.removeUser(username, newPassword, userActive.getSessionId()));
	}

	@Test
	@Order(10)
	public void removeUserFailTest() {
		assertFalse(userInDBService.removeUser(username, newPassword, userActive.getSessionId()));
	}
}
