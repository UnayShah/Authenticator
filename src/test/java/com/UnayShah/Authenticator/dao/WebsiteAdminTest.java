package com.UnayShah.Authenticator.dao;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
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
public class WebsiteAdminTest {

    @Mock
    WebsiteAdmin websiteAdmin;

    private static String username;
    private static Website website1;
    private static Website website2;

    @BeforeAll
    public static void initialize() {
        username = UUID.randomUUID().toString();
        website1 = new Website(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        website2 = new Website(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    @Order(1)
    @Test
    public void constructorTest() {
        websiteAdmin = new WebsiteAdmin(username);
        assertNotNull(websiteAdmin);
    }

    @Order(2)
    @Test
    public void usernameTest() {
        websiteAdmin = new WebsiteAdmin(username);
        assertEquals(username, websiteAdmin.getUsername());
    }

    @Order(3)
    @Test
    public void addWebsitesTest() {
        websiteAdmin = new WebsiteAdmin(username);
        assertTrue(websiteAdmin.addWebsite(website1));
        assertFalse(websiteAdmin.addWebsite(website1));
        assertTrue(websiteAdmin.addWebsite(website2));
        assertFalse(websiteAdmin.addWebsite(website1));
    }

    @Order(4)
    @Test
    public void websiteListTest() {
        websiteAdmin = new WebsiteAdmin(username);
        assertTrue(websiteAdmin.addWebsite(website1));
        assertTrue(websiteAdmin.addWebsite(website2));
        Set<Website> websiteList = new HashSet<>();
        websiteList.add(website1);
        websiteList.add(website2);
        assertEquals(websiteAdmin.getListWebsites(), websiteList);
    }

    @Order(5)
    @Test
    public void removeWebsitesTest() {
        websiteAdmin = new WebsiteAdmin(username);
        assertTrue(websiteAdmin.addWebsite(website1));
        assertTrue(websiteAdmin.addWebsite(website2));
        assertTrue(websiteAdmin.removeWebsite(website1));
        assertFalse(websiteAdmin.removeWebsite(website1));
        assertTrue(websiteAdmin.removeWebsite(website2));
        assertFalse(websiteAdmin.removeWebsite(website2));
    }

}
