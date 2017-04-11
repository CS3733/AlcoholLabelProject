package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.emeraldElves.alcohollabelproject.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.ProductSource;
import com.emeraldElves.alcohollabelproject.AlcoholType;
import com.emeraldElves.alcohollabelproject.SubmittedApplication;
import javafx.scene.control.cell.CheckBoxListCell;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.Instant;

public class UpdateBeerAppController {

    public ApplicationStatus status;//get status from database
    @FXML
    TextField alcoholContentField;

    @FXML
    TextField signatureField;
    @FXML
    Button submitBtn;
    @FXML
    DatePicker datePicker;
    @FXML
    CheckBox deleteInfo;
    @FXML
    CheckBox repositionInfo;
    @FXML
    CheckBox changeFormat;
    @FXML
    CheckBox changeNetContents;
    @FXML
    CheckBox changeAlcStatement;

    Main main;
    SubmittedApplication CurrentlyBeingUpdated;
    String Username;
    AlcoholDatabase alcoholDatabase;

    //image label changes
    Boolean deletedInfo=false;
    Boolean repositionedInfo=false;
    Boolean changedFormatting=false;
    Boolean changedNetContents=false;
    Boolean changedAlcStatement=false;


    public void init(Main main, SubmittedApplication CurrentlyBeingUpdated, String Username) {
        this.main = main;
        this.CurrentlyBeingUpdated = CurrentlyBeingUpdated;
        this.Username = Username;
        status = CurrentlyBeingUpdated.getStatus();
        alcoholDatabase = new AlcoholDatabase(Main.database);
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
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
//        SubmittedApplication CurrentlyBeingUpdated = null;
//        if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
//        }
    }

    public void updateApproved() {
        //SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
//        if (CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
//        }
    }

    public void submitApp() {
        AlcoholType alcoholType = AlcoholType.BEER;

        if (deleteInfo.isSelected()) {
            deletedInfo = true;
        }
        if (repositionInfo.isSelected()){
            repositionedInfo=true;
        }
        if (changeFormat.isSelected()) {
            changedFormatting=true;
        }
        if (changeNetContents.isSelected()){
            changedNetContents=true;
        }
        if (changeAlcStatement.isSelected()){
            changedAlcStatement=true;
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
