package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.PotentialUser;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Log;
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
     * Loads the given fxml file, and inits the controller of the file
     * @param fxml File to load
     */

    public void loadFXML(String fxml){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            Log.console(loader.getController());
            IController controller = loader.getController();
            Bundle bundle = new Bundle();
            bundle.putMain("main",this);
            controller.init(bundle);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFXML(String fxml, String searchTerm){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            IController controller = loader.getController();
            Bundle bundle = new Bundle();
            bundle.putMain("main",this);
            bundle.putString("searchTerm", searchTerm);
            controller.init(bundle);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the given fxml file, and inits the controller of the file with a bundle of the
     * application
     * @param fxml File to be loaded
     * @param application Application to be bundled
     */
    public void loadFXML(String fxml, SubmittedApplication application){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            IController controller = loader.getController();
            Bundle bundle = new Bundle();
            bundle.putApplication("app",application);
            bundle.putMain("main",this);
            controller.init(bundle);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the given fxml file, and inits the controller of the file
     * @param fxml The fxml file to be loaded
     * @param potentialUser The potential user to be bundled
     */
    public void loadFXML(String fxml, PotentialUser potentialUser){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            IController controller = loader.getController();
            Bundle bundle = new Bundle();
            bundle.putPotentialUser("user",potentialUser);
            bundle.putMain("main",this);
            controller.init(bundle);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the fxml file when looking at detailed search
     * @param fxml File to be loaded
     * @param application Application to be looked at
     * @param searchTerm Search term to look at
     */
    public void loadFXML(String fxml, SubmittedApplication application, String searchTerm){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            DetailedSearchController controller = loader.getController();
            //only one instance of this
            controller.init(this, application, searchTerm);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHomepage() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomePage.fxml"));
        try {
            ToolbarController.onLoginPage=false;
            Parent root = loader.load();
            root.getStylesheets().add("/style/material.css");
            HomeController controller = loader.getController();
            controller.init(this);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
