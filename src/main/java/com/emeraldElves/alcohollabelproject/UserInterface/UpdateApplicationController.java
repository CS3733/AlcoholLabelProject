package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Authenticator;
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
    ApplicantInterface applicantInterface;


    public void init(Main main, SubmittedApplication CurrentlyBeingUpdated) {
        this.main = main;
        this.CurrentlyBeingUpdated = CurrentlyBeingUpdated;
        status = CurrentlyBeingUpdated.getStatus();
        applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
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

        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getRepresentativeID()));
        phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));

        //SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));

        if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        } else if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
        }
    }

    public void submitApp() {
        AlcoholInfo alcoholInfo = CurrentlyBeingUpdated.getApplication().getAlcohol();
        AlcoholType alcoholType = alcoholInfo.getAlcoholType();
        double pH;
        int vintageYear;
        if(alcoholType == AlcoholType.WINE) {
            pH = Double.parseDouble(phLevelField.getText());
            vintageYear = Integer.parseInt(wineVintageYearField.getText());

            alcoholInfo.getWineInfo().pH = pH;
            alcoholInfo.getWineInfo().vintageYear = vintageYear;

            applicantInterface.submitApplication(CurrentlyBeingUpdated);
        }

        int alcoholContent = Integer.parseInt(alcoholContentField.getText());
        alcoholInfo.setAlcoholContent(alcoholContent);

        applicantInterface.submitApplication(CurrentlyBeingUpdated);

        main.loadHomepage();

    }

    public void cancelApp() {
        main.loadHomepage();

    }

    public void logout() {
        Authenticator.getInstance().logout();
        main.loadHomepage();
    }

}
