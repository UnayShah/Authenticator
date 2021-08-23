package com.UnayShah.Authenticator.dao;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class UserInDBTest {
	@Mock
	UserInDB userInDB;

	private static String username;
	private static String password;
	private static String website1;
	private static String website2;

	@BeforeAll
	public static void initialize() {
		username = UUID.randomUUID().toString();
		password = UUID.randomUUID().toString();
		website1 = UUID.randomUUID().toString();
		website2 = UUID.randomUUID().toString();
	}

	@Test
	@Order(1)
	public void constructor1Test() {
		userInDB = new UserInDB(username, password);
		assertEquals(username, userInDB.getUsername());
	}

	@Test
	@Order(2)
	public void constructor2SingleWebsiteTest() {
		userInDB = new UserInDB(username, password, website1);
		assertEquals(username, userInDB.getUsername());
	}

	@Test
	@Order(3)
	public void constructor2MultipleWebsiteTest() {
		userInDB = new UserInDB(username, password, website1, website2);
		assertEquals(username, userInDB.getUsername());
	}

	@Test
	@Order(4)
	public void containsWebsiteIdTest() {
		userInDB = new UserInDB(username, password, website1);
		assertTrue(userInDB.containsWebsiteId(website1));
		assertFalse(userInDB.containsWebsiteId(website2));
	}

	@Test
	@Order(5)
	public void addSingleWebsiteIdTest() {
		userInDB = new UserInDB(username, password);
		assertFalse(userInDB.containsWebsiteId(website1));
		assertFalse(userInDB.containsWebsiteId(website2));
		userInDB.addWebsiteId(website1);
		assertTrue(userInDB.containsWebsiteId(website1));
		assertFalse(userInDB.containsWebsiteId(website2));
	}

	@Test
	@Order(6)
	public void addMultipleWebsiteIdTest() {
		userInDB = new UserInDB(username, password);
		assertFalse(userInDB.containsWebsiteId(website1));
		assertFalse(userInDB.containsWebsiteId(website2));
		userInDB.addWebsiteId(website1, website2);
		assertTrue(userInDB.containsWebsiteId(website1));
		assertTrue(userInDB.containsWebsiteId(website2));
	}

	@Test
	@Order(6)
	public void removeWebsiteIdTest() {
		userInDB = new UserInDB(username, password, website1, website2);
		assertTrue(userInDB.containsWebsiteId(website1));
		assertTrue(userInDB.containsWebsiteId(website2));
		userInDB.removeWebsiteId(website1);
		assertFalse(userInDB.containsWebsiteId(website1));
		assertTrue(userInDB.containsWebsiteId(website2));
	}
}
