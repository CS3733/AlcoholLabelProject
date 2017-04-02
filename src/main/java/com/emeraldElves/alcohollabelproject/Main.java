package com.emeraldElves.alcohollabelproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainGUI.fxml"));
        primaryStage.setTitle("Alcohol Label Project");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
        stage = primaryStage;
    }

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
