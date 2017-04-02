package com.emeraldElves.alcohollabelproject;

/**
 * Created by keionbis on 4/2/17.
 */

import javafx.application.Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UpdateApplication extends Application {
    public static String user = "";
    public static Stage stage;

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TTB COLA");
        primaryStage.setScene(new Scene(root, 640, 480));
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
