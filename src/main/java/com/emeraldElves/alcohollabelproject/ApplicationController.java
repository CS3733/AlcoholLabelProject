package com.emeraldElves.alcohollabelproject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Dan on 4/1/2017.
 */
public class ApplicationController {
    public Label applicantNameLabel;
    public Button logoutBtn;
    public Button newApplicationBtn;
    public Button updatePrevApplicationBtn;
    public Button viewSubmittedApplicationBtn;

    public ApplicationController(){
        //nothing
    }

    /**
     * Loads the new application fxml file
     */
    public void newApplication() {
        Main.loadFXML("/fxml/newApplicationPage1.fxml");
    }

    /**
     * Loads the update application fxml file
     */
    public void updateApplication(){ Main.loadFXML("/fxml/update-app.fxml");}
}
