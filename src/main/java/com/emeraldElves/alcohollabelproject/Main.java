package com.emeraldElves.alcohollabelproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Database database = new Database("ttbDB");
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
            Log.console("Used existing TTBAgentLogin table");
        }


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainGUI.fxml"));
        primaryStage.setTitle("Alcohol Label Project");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
