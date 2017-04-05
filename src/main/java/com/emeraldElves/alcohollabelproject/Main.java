package com.emeraldElves.alcohollabelproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.soap.Detail;
import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static Database database;

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

    public void loadSearchPage(String searchTerm){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Search.fxml"));
        try {
            Parent root = loader.load();
            SearchController controller = loader.getController();
            controller.init(this, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDetailedSearchPage(SubmittedApplication application, String searchTerm){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailedPage.fxml"));
        try {
            Parent root = loader.load();
            DetailedSearchController controller = loader.getController();
            controller.init(this, application, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLoginPage(){

    }

    public void loadHomepage(UserType userType, String username){

    }

    public void loadProfilePage(String username){

    }

    public void loadNewApplicationPage(String username){

    }

    public void loadUpdateApplicationPage(SubmittedApplication application, String Username){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UpdateApplication.fxml"));
        try {
            Parent root = loader.load();
            UpdateApplicationController controller = loader.getController();
            controller.init(this, application, Username);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadWorkflowPage(String username){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UpdateApplication.fxml"));
        try {
            Parent root = loader.load();
            WorkflowController controller = loader.getController();
            controller.init(this, username);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWorkflowActionsPage(String username, SubmittedApplication application){

    }


    public static void main(String[] args) {
        launch(args);
    }
}
