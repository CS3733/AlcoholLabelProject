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

    @Override
    public void start(Stage primaryStage) throws Exception {
        database = DatabaseController.getInstance().initDatabase("ttbDB");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Search.fxml"));
        Parent root = loader.load();
        SearchController controller = loader.getController();
        controller.init(this, UserType.BASIC, "", "brand");
//        controller.init(this, UserType.BASIC, "");
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

    public void loadSearchPage(UserType userType, String username, String searchTerm) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Search.fxml"));
        try {
            Parent root = loader.load();
            SearchController controller = loader.getController();
            controller.init(this, userType, username, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDetailedSearchPage(SubmittedApplication application, String searchTerm) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailedSearchPage.fxml"));
        try {
            Parent root = loader.load();
            DetailedSearchController controller = loader.getController();
            controller.init(this, application, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLoginPage() {

    }

    public void loadHomepage(UserType userType, String username) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
//        try {
//            Parent root = loader.load();
//            HomeController controller = loader.getController();
//            controller.init(this, userType, username);
//            stage.getScene().setRoot(root);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void loadProfilePage(String username) {

    }

    public void loadNewApplicationPage(String username) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewApplication.fxml"));
//        try {
//            Parent root = loader.load();
//            ApplicationController controller = loader.getController();
//            controller.init(this, username);
//            stage.getScene().setRoot(root);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void loadUpdateApplicationPage(SubmittedApplication application, String Username) {
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

    public void loadWorkflowPage(String username) {
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

    public void loadWorkflowActionsPage(String username, SubmittedApplication application) {

    }
    public void loadApplicantWorkflowController(String Username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApplicantWorkflowPage.fxml"));
        try {
            Parent root = loader.load();
            ApplicantWorkflowController controller = loader.getController();
            controller.init(Username,this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
