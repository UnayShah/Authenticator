package com.UnayShah.Authenticator.dao;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
	String username = UUID.randomUUID().toString();
	String password = UUID.randomUUID().toString();
	String website = UUID.randomUUID().toString();

	@Test
	@Order(1)
	public void usernameTest() {
		userInDB = new UserInDB(username, password, website);
		assertEquals(username, userInDB.getUsername());
	}
}
