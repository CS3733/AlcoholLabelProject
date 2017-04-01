package com.emeraldElves.alcohollabelproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kylec on 3/31/2017.
 */
public class AuthenticatedUsersDatabaseTest {


    AuthenticatedUsersDatabase authenticatedUsersDatabase;
    Database db;


    @Before
    public void setup() {
        db = new Database("testDB");
        authenticatedUsersDatabase = new AuthenticatedUsersDatabase(db);
    }

    @Test
    public void testIsValidTTBAgent() {
        assertTrue(authenticatedUsersDatabase.isValidTTBAgent("Admin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidTTBAgent("Admin", "NotAdmin1"));
        assertFalse(authenticatedUsersDatabase.isValidTTBAgent("NotAdmin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidTTBAgent("NotAdmin", "NotAdmin1"));
    }

    @Test
    public void testIsValidApplicant() {
        assertTrue(authenticatedUsersDatabase.isValidApplicant("Admin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidApplicant("Admin", "NotAdmin1"));
        assertFalse(authenticatedUsersDatabase.isValidApplicant("NotAdmin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidApplicant("NotAdmin", "NotAdmin1"));
    }
}