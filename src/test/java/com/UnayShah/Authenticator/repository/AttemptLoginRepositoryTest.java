package com.UnayShah.Authenticator.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import com.UnayShah.Authenticator.model.AttemptLogin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AttemptLoginRepositoryTest {
    @Autowired
    AttemptLoginRepository attemptLoginRepository;

    private static AttemptLogin attemptLogin;
    private static String websiteId;

    @BeforeAll
    public static void initialize() {
        websiteId = UUID.randomUUID().toString();
        attemptLogin = new AttemptLogin(websiteId);
    }

    @Order(1)
    @Test
    public void saveTest() {
        assertEquals(attemptLoginRepository.save(attemptLogin), attemptLogin);
    }

    @Order(2)
    @Test
    public void findTest() {
        assertTrue(attemptLoginRepository.findById(attemptLogin.getSessionId()).isPresent());
        assertTrue(new ReflectionEquals(attemptLogin, new String[0])
                .matches(attemptLoginRepository.findById(attemptLogin.getSessionId()).get()));
    }

    @Order(3)
    @Test
    public void deleteTest() {
        assertTrue(attemptLoginRepository.findById(attemptLogin.getSessionId()).isPresent());
        attemptLoginRepository.deleteById(attemptLogin.getSessionId());
        assertFalse(attemptLoginRepository.findById(attemptLogin.getSessionId()).isPresent());
    }

}
