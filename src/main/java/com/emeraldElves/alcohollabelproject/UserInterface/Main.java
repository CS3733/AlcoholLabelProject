package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.AlcoholType;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        UISwitcher.getInstance().setMain(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
        Parent root = loader.load();
        UISwitcher.getInstance().setStage(primaryStage);
//        UISwitcher.getInstance().switchToPage(UISwitcher.HOME_PAGE);
//        primaryStage.setResizable(false);
        HomeController controller = loader.getController();
        controller.init(this);
        primaryStage.setTitle("Alcohol Label Project");
        primaryStage.getIcons().add(new Image(("images/logo.png")));
        root.getStylesheets().add("/style/material.css");
        primaryStage.setScene(new Scene(root,1024,768));
        primaryStage.show();
        stage = primaryStage;
    }


    public void printPage(){
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null){
            job.showPrintDialog(stage);
            PageLayout pageLayout = job.getPrinter().getDefaultPageLayout();
            double scaleX = pageLayout.getPrintableWidth() / stage.getWidth();
            double scaleY = pageLayout.getPrintableHeight() / stage.getHeight();
            double minimumScale = Math.min(scaleX, scaleY);
            Scale scale = new Scale(minimumScale, minimumScale);
            stage.getScene().getRoot().getTransforms().add(scale);
            job.printPage(stage.getScene().getRoot());
            job.endJob();
            stage.getScene().getRoot().getTransforms().add(new Scale(1/minimumScale, 1/minimumScale));
        }
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
            stage.getScene().setRoot(root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadFXML(String path, NewApplicationController controller) {
        FXMLLoader root = null;
        try {
            root = new FXMLLoader(Main.class.getResource(path));
            root.setController(controller);
            stage.getScene().setRoot(root.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadSearchPage(String searchTerm) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Search.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            SearchController controller = loader.getController();
            controller.init(this, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDetailedSearchPage(SubmittedApplication application, String searchTerm) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailedSearchPage.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            DetailedSearchController controller = loader.getController();
            controller.init(this, application, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNewUserPage(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewUser.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            NewUserController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLoginPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        try {
            ToolbarController.onLoginPage=true;
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            LoginController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHomepage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            HomeController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadProfilePage(String username) {

    }

    public void loadNewApplicationPage(SubmittedApplication application) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newApplicationPage1.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            NewApplicationController controller = loader.getController();
            controller.init(this, application);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadNewApplicationPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newApplicationPage1.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            NewApplicationController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUpdateApplicationPage(SubmittedApplication application) {
        FXMLLoader loader=null;
        if (application.getApplication().getAlcohol().getAlcoholType()== AlcoholType.BEER){
            loader = new FXMLLoader(getClass().getResource("/fxml/UpdateApplicationBeer.fxml"));
        } else if (application.getApplication().getAlcohol().getAlcoholType()== AlcoholType.WINE){
            loader = new FXMLLoader(getClass().getResource("/fxml/UpdateApplicationWine.fxml"));
        } else if (application.getApplication().getAlcohol().getAlcoholType()== AlcoholType.DISTILLEDSPIRITS) {
            loader = new FXMLLoader(getClass().getResource("/fxml/UpdateApplicationSpirits.fxml"));
        } else {
            loader = new FXMLLoader(getClass().getResource("/fxml/UpdateApplication.fxml"));
        }

        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            if (application.getApplication().getAlcohol().getAlcoholType()== AlcoholType.BEER){
                UpdateBeerAppController controller = loader.getController();
                controller.init(this, application,"");
                stage.getScene().setRoot(root);
            } else if (application.getApplication().getAlcohol().getAlcoholType()== AlcoholType.WINE){
                UpdateWineAppController controller = loader.getController();
                controller.init(this, application,"");
                stage.getScene().setRoot(root);
            } else if (application.getApplication().getAlcohol().getAlcoholType()== AlcoholType.DISTILLEDSPIRITS) {
                UpdateSpiritsAppController controller = loader.getController();
                controller.init(this, application,"");
                stage.getScene().setRoot(root);
            } else{
                UpdateApplicationController controller = loader.getController();
                controller.init(this, application);
                stage.getScene().setRoot(root);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadSubmitImages(SubmittedApplication application) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SubmitLabel.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            ImageSubmissionController controller = loader.getController();
            controller.init(this, application);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWorkflowPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TTBWorkflowPage.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            TTBWorkflowController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWorkflowActionsPage(SubmittedApplication application) {

    }

    public void loadApplicantWorkflowPage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApplicantWorkflowPage.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            ApplicantWorkflowController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadApprovalProcessController(SubmittedApplication application) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ApprovalPage.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            ApprovalProcessController controller = loader.getController();
            controller.init(this, application);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLabelPage(SubmittedApplication application){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DisplayLabel.fxml"));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            DisplayLabelController controller = loader.getController();
            controller.init(this, application);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
