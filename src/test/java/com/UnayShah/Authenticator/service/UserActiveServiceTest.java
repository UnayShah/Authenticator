package com.UnayShah.Authenticator.service;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.core.CommonConstants;
import com.UnayShah.Authenticator.model.UserActive;

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
    private static String websiteId1;
    private static String websiteId2;

    @BeforeAll
    public static void initialize() {
        username = UUID.randomUUID().toString();
        password = UUID.randomUUID().toString();
        websiteId1 = CommonConstants.AUTHENTICATOR_WEBSITE_ID;
        websiteId2 = UUID.randomUUID().toString();
    }

    @BeforeEach
    public void initializeUserInDB() {
        assertNotNull(userInDBService.newUser(username, password));
        // userActive = userActiveService.login(username, password, websiteId1);
        // assertNotNull(userActive);
        // assertTrue(userInDBService.addWebsite(username, password, websiteId2,
        // userActive.getSessionId()));
        userActive = userActiveService.login(username, password, websiteId1);
    }

    @AfterEach
    public void terminateUserInDB() {
        if (!sessionId.equals(""))
            assertTrue(userInDBService.removeUser(username, password, sessionId));
        else
            assertTrue(userInDBService.removeUser(username, password,
                    userActiveService.login(username, password, websiteId1).getSessionId()));
        sessionId = "";
    }

    @Test
    @Order(1)
    public void loginTest() {
        assertNotNull(userActive.getSessionId());
        sessionId = userActive.getSessionId();
    }

    @Test
    @Order(2)
    public void loginFailTest() {
        assertNull(userActiveService.login(username, password, websiteId2));
    }

    @Test
    @Order(3)
    public void refreshSessionTest() {
        assertNotNull(userActiveService.refreshSession(username, userActive.getSessionId()));
        assertEquals(userActiveService.refreshSession(username, userActive.getSessionId()).getSessionId(), userActive.getSessionId());
    }

    @Test
    @Order(4)
    public void refreshSessionFailTest() {
        assertNull(userActiveService.refreshSession(username, password));
    }

    @Test
    @Order(5)
    public void checkSessionFailTest() {
        assertTrue(userActiveService.checkSession(username, userActive.getSessionId()));
    }

    @Test
    @Order(6)
    public void logoutTest() {
        assertTrue(userActiveService.logout(username, userActive.getSessionId()));
    }

    @Test
    @Order(7)
    public void logoutFailTest() {
        assertFalse(userActiveService.logout(username, password));
    }
}
