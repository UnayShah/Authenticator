package com.UnayShah.Authenticator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class AttemptLoginTest {
    @Mock
    AttemptLogin attemptLogin;
    private static String websiteId;

    @BeforeAll
    public static void initialize() {
        websiteId = UUID.randomUUID().toString();
    }

    @Order(1)
    @Test
    public void constructorTest() {
        attemptLogin = new AttemptLogin(websiteId);
        assertNotNull(attemptLogin);
    }

    @Order(2)
    @Test
    public void getSessionIdTest() {
        attemptLogin = new AttemptLogin(websiteId);
        assertNotNull(attemptLogin.getSessionId());
    }

    @Order(3)
    @Test
    public void getWebsiteIdTest() {
        attemptLogin = new AttemptLogin(websiteId);
        assertEquals(attemptLogin.getWebsiteId(), websiteId);
    }

}
