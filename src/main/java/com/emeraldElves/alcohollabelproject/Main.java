package com.emeraldElves.alcohollabelproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static Database database;

    @Override
    public void start(Stage primaryStage) throws Exception {
        database = new Database("ttbDB");
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


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainGUI.fxml"));
        primaryStage.setTitle("Alcohol Label Project");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
        stage = primaryStage;
    }

    /**
     * Load an FXML file and set the stage to the new UI.
     *
     * @param path The relative path to the FXML file.
     */
    public static void loadFXML(String path) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Main.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.getScene().setRoot(root);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
