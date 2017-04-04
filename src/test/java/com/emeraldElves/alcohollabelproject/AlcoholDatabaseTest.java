package com.emeraldElves.alcohollabelproject;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class AlcoholDatabaseTest {
    AlcoholDatabase alcoholDatabase;
    Database db;

    @Before
    public void setup() {
        db = new Database("testDB");
        db.connect();
        try {
            db.createTable("TTBAgentLogin", new Database.TableField("username", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"));
            Log.console("Created new TTBAgentLogin table");
        } catch (SQLException e) {
            Log.console("Used existing TTBAgentLogin table");
        }

        try {
            db.createTable("ApplicantLogin", new Database.TableField("username", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"));
            Log.console("Created new ApplicantLogin table");
        } catch (SQLException e) {
            Log.console("Used existing ApplicantLogin table");
        }

        try {
            db.createTable("SubmittedApplications", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("applicantID", "INTEGER NOT NULL"),
                    new Database.TableField("status", "INTEGER NOT NULL"),
                    new Database.TableField("statusMsg", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("submissionTime", "TIMESTAMP NOT NULL"),
                    new Database.TableField("expirationDate", "TIMESTAMP NOT NULL"),
                    new Database.TableField("agentName", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("approvalDate", "TIMESTAMP NOT NULL"),
                    new Database.TableField("TTBUsername", "VARCHAR (255) NOT NULL"));
            Log.console("Created new SubmittedApplications table");
        } catch (SQLException e) {
            Log.console("Used existing SubmittedApplications table");
        }

        try {
            db.createTable("ManufacturerInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("authorizedName", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("physicalAddress", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("company", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("representativeID", "INTEGER NOT NULL"),
                    new Database.TableField("permitNum", "INTEGER NOT NULL"),
                    new Database.TableField("phoneNum", "VARCHAR (255) NOT NULL"), //check with kyle
                    new Database.TableField("emailAddress", "VARCHAR (255) NOT NULL"));
            Log.console("Created new ManufacturerInfo table");
        } catch (SQLException e) {
            Log.console("Used existing ManufacturerInfo table");
        }

        try {
            db.createTable("AlcoholInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("alcoholContent", "DOUBLE NOT NULL"),
                    new Database.TableField("fancifulName", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("brandName", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("origin", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("type", "INTEGER NOT NULL"),  //only beer or wine?
                    new Database.TableField("pH", "DOUBLE"),
                    new Database.TableField("vintageYear", "VARCHAR (255)"));
            Log.console("Created new AlcoholInfo table");
        } catch (SQLException e) {
            Log.console("Used existing AlcoholInfo table");
        }
        alcoholDatabase = new AlcoholDatabase(db);
    }

    @Test
    public void testUnapproved(){
        assertTrue(alcoholDatabase.getMostRecentUnapproved(10).isEmpty());
    }


}
