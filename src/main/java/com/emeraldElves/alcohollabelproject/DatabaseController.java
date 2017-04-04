package com.emeraldElves.alcohollabelproject;

import java.sql.SQLException;

/**
 * Created by Kylec on 4/3/2017.
 */
public class DatabaseController {
    private static DatabaseController instance = null;

    private DatabaseController() {
    }

    public static synchronized DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }

    public Database initDatabase(String dbName) {
        Database database = new Database(dbName);
        database.connect();
        try {
            database.createTable("TTBAgentLogin", new Database.TableField("username", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"));
            Log.console("Created new TTBAgentLogin table");
        } catch (SQLException e) {
            Log.console("Used existing TTBAgentLogin table");
        }

        try {
            database.createTable("ApplicantLogin", new Database.TableField("username", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"));
            Log.console("Created new ApplicantLogin table");
        } catch (SQLException e) {
            Log.console("Used existing ApplicantLogin table");
        }

        try {
            database.createTable("SubmittedApplications", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("applicantID", "INTEGER NOT NULL"),
                    new Database.TableField("status", "INTEGER NOT NULL"),
                    new Database.TableField("statusMsg", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("submissionTime", "BIGINT NOT NULL"),
                    new Database.TableField("expirationDate", "BIGINT"),
                    new Database.TableField("agentName", "VARCHAR (255)"),
                    new Database.TableField("approvalDate", "BIGINT"),
                    new Database.TableField("TTBUsername", "VARCHAR (255)"));
            Log.console("Created new SubmittedApplications table");
        } catch (SQLException e) {
            Log.console("Used existing SubmittedApplications table");
        }

        try {
            database.createTable("ManufacturerInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
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
            database.createTable("AlcoholInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("alcoholContent", "INTEGER NOT NULL"),
                    new Database.TableField("fancifulName", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("brandName", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("origin", "INTEGER NOT NULL"),
                    new Database.TableField("type", "INTEGER NOT NULL"),  //only beer or wine?
                    new Database.TableField("pH", "INTEGER"),
                    new Database.TableField("vintageYear", "VARCHAR (255)"));
            Log.console("Created new AlcoholInfo table");
        } catch (SQLException e) {
            Log.console("Used existing AlcoholInfo table");
        }

        return database;
    }


}
