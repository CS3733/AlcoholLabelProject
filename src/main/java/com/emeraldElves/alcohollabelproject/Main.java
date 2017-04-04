package com.emeraldElves.alcohollabelproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static Database database;
    public static Applicant applier;

    @Override
    public void start(Stage primaryStage) throws Exception {
        database = DatabaseController.getInstance().initDatabase("ttbDB");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Search.fxml"));
        primaryStage.setTitle("Alcohol Label Project");
        primaryStage.setScene(new Scene(root));
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
