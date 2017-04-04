package com.emeraldElves.alcohollabelproject;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

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
        db.connect();
    }

    @Test
    public void testIsValidTTBAgent() {

        try {
            db.dropTable("TTBAgentLogin");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            db.createTable("TTBAgentLogin", new Database.TableField("username", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"));
            Log.console("Created new table");
            db.insert("'Admin', 'Admin1'", "TTBAgentLogin");
            db.insert("'Admin2', 'Admin20'", "TTBAgentLogin");
            Log.console("Inserted Admin user");
        } catch (SQLException e) {
            Log.console("Using existing table.");
        }
        authenticatedUsersDatabase = new AuthenticatedUsersDatabase(db);
        assertTrue(authenticatedUsersDatabase.isValidTTBAgent("Admin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidTTBAgent("Admin", "NotAdmin1"));
        assertFalse(authenticatedUsersDatabase.isValidTTBAgent("NotAdmin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidTTBAgent("NotAdmin", "NotAdmin1"));
        assertEquals(2, authenticatedUsersDatabase.getAllAgents().size());
    }

    @Test
    public void testIsValidApplicant() {

        try {
            db.dropTable("ApplicantLogin");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            db.createTable("ApplicantLogin", new Database.TableField("username", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"));
            Log.console("Created new table");
            db.insert("'Admin', 'Admin1'", "ApplicantLogin");
            Log.console("Inserted Admin user");
        } catch (SQLException e) {
            Log.console("Using existing table.");
        }
        authenticatedUsersDatabase = new AuthenticatedUsersDatabase(db);
        assertTrue(authenticatedUsersDatabase.isValidApplicant("Admin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidApplicant("Admin", "NotAdmin1"));
        assertFalse(authenticatedUsersDatabase.isValidApplicant("NotAdmin", "Admin1"));
        assertFalse(authenticatedUsersDatabase.isValidApplicant("NotAdmin", "NotAdmin1"));
    }
}