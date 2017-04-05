package com.emeraldElves.alcohollabelproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {

    public static Stage stage;
    public static Database database;
    public static Applicant applier;

    @Override
    public void start(Stage primaryStage) throws Exception {
        database = DatabaseController.getInstance().initDatabase("ttbDB");

//        database.insert("'Admin', 'Admin'", "TTBAgentLogin");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        controller.init(this, UserType.BASIC, "");
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
        FXMLLoader root = null;
        try {
            root = new FXMLLoader(Main.class.getResource(path));
            stage.getScene().setRoot((Parent) root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadFXML(String path, NewApplicationController controller) {
        FXMLLoader root = null;
        try {
            root = new FXMLLoader(Main.class.getResource(path));
            root.setController(controller);
            stage.getScene().setRoot((Parent) root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void loadDetailedSearchPage(SubmittedApplication application, String searchTerm, UserType userType, String Username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailedSearchPage.fxml"));
        try {
            Parent root = loader.load();
            DetailedSearchController controller = loader.getController();
            controller.init(this, application, searchTerm, userType, Username);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNewUserPage(UserType userType, String username){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewUser.fxml"));
        try {
            Parent root = loader.load();
            NewUserController controller = loader.getController();
            controller.init(this, userType, username);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLoginPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        try {
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHomepage(UserType userType, String username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
        try {
            Parent root = loader.load();
            HomeController controller = loader.getController();
            controller.init(this, userType, username);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProfilePage(String username) {

    }

    public void loadNewApplicationPage(String username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newApplicationPage1.fxml"));
        try {
            Parent root = loader.load();
            NewApplicationController controller = loader.getController();
            controller.init(this, username);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/workflowController.fxml"));
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

    public void loadApplicantWorkflowPage(String Username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApplicantWorkflowPage.fxml"));
        try {
            Parent root = loader.load();
            ApplicantWorkflowController controller = loader.getController();
            controller.init(Username, this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadApprovalProcessController(SubmittedApplication application, String Username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApprovalPage.fxml"));
        try {
            Parent root = loader.load();
            ApprovalProcessController controller = loader.getController();
            controller.init(this, Username, application);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
