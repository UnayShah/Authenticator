package com.UnayShah.Authenticator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AttemptLoginServiceTest {
    @Autowired
    AttemptLoginService attemptLoginService;

    private static String websiteId;
    private static String sessionId;

    @BeforeAll
    public static void initialize() {
        websiteId = UUID.randomUUID().toString();
    }

    @Order(1)
    @Test
    public void attemptLoginTest() {
        sessionId = attemptLoginService.attemptLogin(websiteId);
        assertNotNull(sessionId);
    }

    @Order(2)
    @Test
    public void getWebsiteIdTest() {
        assertEquals(websiteId, attemptLoginService.getWebsiteId(sessionId));
        ;
    }

    @Order(3)
    @Test
    public void removeAttemptLoginTest() {
        assertTrue(attemptLoginService.removeAttemptLogin(sessionId));
    }
}
