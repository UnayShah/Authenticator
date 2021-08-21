package com.UnayShah.Authenticator.repository;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.UnayShah.Authenticator.dao.UserInDB;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserInDBRepositoryTest {
	@Mock
	static UserInDB userInDB;

	@Autowired
	UserInDBRepository userInDBRepository;

	String username = UUID.randomUUID().toString();
	String password = UUID.randomUUID().toString();
	String websiteId = UUID.randomUUID().toString();

	@BeforeAll
	public void initialize() {
		userInDB = new UserInDB(username, password, websiteId);
		assertEquals(username, userInDB.getUsername());
	}

	@Test
	@Order(1)
	public void saveTest() {
		assertEquals(userInDB, userInDBRepository.save(userInDB));
	}

	@Test
	@Order(2)
	public void findTest() {
		assertEquals(userInDB, userInDBRepository.findById(username));
	}

	@Test
	@Order(3)
	public void removeTest() {
		assertTrue(userInDBRepository.findById(username).isPresent());
		userInDBRepository.deleteById(username);
		assertTrue(userInDBRepository.findById(username).isEmpty());
	}

}
