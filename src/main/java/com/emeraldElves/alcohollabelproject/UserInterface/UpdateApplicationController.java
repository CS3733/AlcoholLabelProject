package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Keion Bisland on 4/2/2017.
 */

public class UpdateApplicationController {

    public ApplicationStatus status;//get status from database
    @FXML
    TextField alcoholContentField;
    @FXML
    TextField wineVintageYearField;
    @FXML
    TextField phLevelField;
    @FXML
    TextField signatureField;
    @FXML
    Button submitBtn;
    @FXML
    DatePicker datePicker;

    Main main;
    SubmittedApplication CurrentlyBeingUpdated;
    String Username;
    AlcoholDatabase alcoholDatabase;


    public void init(Main main, SubmittedApplication CurrentlyBeingUpdated, String Username) {
        this.main = main;
        this.CurrentlyBeingUpdated = CurrentlyBeingUpdated;
        this.Username = Username;
        status = CurrentlyBeingUpdated.getStatus();
        alcoholDatabase = new AlcoholDatabase(Main.database);
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE){
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        }
    }

    public void ApplicationStatuschecker() {
        switch (status) {
            case REJECTED:
                updateRejected();
                break;
            case APPROVED:
                updateApproved();
                break;
            case PENDINGREVIEW:
                break;
            case APPROVEDWITHCONDITIONS:
                break;
            case NEEDSCORRECTIONS:
                break;
        }

    }

    public void updateRejected() {
        //SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        } else if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
        }
    }

    public void updateApproved() {
        //SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));

        if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        } else if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
        }
    }

    public void submitApp() {
        AlcoholType alcoholType = CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType();
        double pH;
        int vintageYear;
        if(alcoholType == AlcoholType.WINE) {
            pH = Double.parseDouble(phLevelField.getText());
            vintageYear = Integer.parseInt(wineVintageYearField.getText());
            alcoholDatabase.changePH(CurrentlyBeingUpdated, pH);
            alcoholDatabase.changeVintageYear(CurrentlyBeingUpdated, vintageYear);
        }

        int alcoholContent = Integer.parseInt(alcoholContentField.getText());
        alcoholDatabase.changeAlcoholContent(CurrentlyBeingUpdated, alcoholContent);

        main.loadHomepage(UserType.APPLICANT, Username);

    }

    public void cancelApp() {
        main.loadHomepage(UserType.APPLICANT, Username);

    }

    public void logout() {
        main.loadHomepage(UserType.BASIC, "");
    }

}