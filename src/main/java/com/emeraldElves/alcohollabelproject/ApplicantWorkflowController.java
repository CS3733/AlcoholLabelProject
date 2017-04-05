package com.emeraldElves.alcohollabelproject;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

/**
 * Created by keionbis on 4/4/17.
 */

public class ApplicantWorkflowController {
   String Username;
    Main main;
    private ListView <SubmittedApplication> ApplicationsList;
    public void init(String Username, Main main){
        this.Username = Username;;
        this.main = main;
    }
    AlcoholDatabase alcoholDatabase = new AlcoholDatabase((Main.database));
    AuthenticatedUsersDatabase RepID = new AuthenticatedUsersDatabase(Main.database);
    public void ApplicationWorkflow() {
        ApplicationsList = new ListView<SubmittedApplication>(FXCollections.observableArrayList(alcoholDatabase.getApplicationsByRepresentative(RepID.getRepresentativeID(Username))));
        ApplicationsList.getSelectionModel().getSelectedItem();


    }
}
