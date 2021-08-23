package com.UnayShah.Authenticator.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.dao.UserActive;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserActiveServiceTest {

    @Autowired
    UserActiveService userActiveService;

    @Autowired
    UserInDBService userInDBService;

    private static UserActive userActive;
    private static String sessionId;
    private static String username;
    private static String password;
    private static String websiteId;

    @BeforeAll
    public static void initialize() {
        username = UUID.randomUUID().toString();
        password = UUID.randomUUID().toString();
        websiteId = UUID.randomUUID().toString();

    }

    @BeforeEach
    public void initializeUserInDB() {
        assertNotNull(userInDBService.newUser(username, password, websiteId));
    }

    @AfterEach
    public void terminateUserInDB() {
        assertTrue(userInDBService.removeUser(username, password, websiteId));
    }

    @Test
    @Order(1)
    public void loginTest() {
        userActive = userActiveService.login(username, password, websiteId);
        assertNotNull(userActive.getSessionId());
        sessionId = userActive.getSessionId();
    }

    @Test
    @Order(2)
    public void loginFailTest() {
        assertNull(userActiveService.login(username, password, UUID.randomUUID().toString()));
    }

    @Test
    @Order(3)
    public void refreshSessionTest() {
        assertNotNull(userActiveService.refreshSession(sessionId, username));
        assertEquals(userActiveService.refreshSession(sessionId, username).getSessionId(), sessionId);
    }

    @Test
    @Order(4)
    public void refreshSessionFailTest() {
        assertNull(userActiveService.refreshSession(sessionId, UUID.randomUUID().toString()));
    }

    @Test
    @Order(5)
    public void logoutTest() {
        assertTrue(userActiveService.logout(sessionId, username));
    }

    @Test
    @Order(6)
    public void logoutFailTest() {
        assertFalse(userActiveService.logout(sessionId, username));
    }

}
