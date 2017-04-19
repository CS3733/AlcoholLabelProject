package com.emeraldElves.alcohollabelproject.UserInterface;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Kylec on 4/10/2017.
 */
public class UISwitcher {

    public static String SEARCH_PAGE = "/fxml/Search.fxml";
    public static String DETAILED_SEARCH_PAGE = "/fxml/DetailedSearchPage.fxml";
    public static String NEW_USER_PAGE = "/fxml/NewUser.fxml";
    public static String LOGIN_PAGE = "/fxml/Login.fxml";
    public static String HOME_PAGE = "/fxml/HomePage.fxml";
    public static String NEW_APPLICATION_PAGE = "/fxml/newApplicationPage1.fxml";
    public static String UPDATE_APPLICATION_PAGE = "/fxml/UpdateApplication.fxml";
    public static String WORKFLOW_PAGE = "/fxml/workflowController.fxml";
    public static String WORKFLOW_ACTIONS_PAGE = "/fxml/ApprovalPage.fxml";
    public static String APPLICANT_WORKFLOW_PAGE = "/fxml/ApplicantWorkflowPage.fxml";
    private Main main;


    private static UISwitcher instance;
    private Stage stage;

    public static synchronized UISwitcher getInstance() {
        if (instance == null)
            instance = new UISwitcher();
        return instance;
    }


    public void setMain(Main main){
        this.main = main;
    }

    public Main getMain(){
        return main;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void switchToPage(String page, Bundle data){
        if(stage == null)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
        try {
            Parent root = loader.load();
            IController controller = loader.getController();
            controller.init(data);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToPage(String page){
        switchToPage(page, new Bundle());
    }

}
