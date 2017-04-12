package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.AlcoholType;
import com.emeraldElves.alcohollabelproject.Data.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    @FXML
    Button uploadImage;
    @FXML
    ImageView imageView;


    Main main;
    SubmittedApplication CurrentlyBeingUpdated;
    String Username;
    ApplicantInterface applicant;

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
        applicant= new ApplicantInterface(Authenticator.getInstance().getUsername());
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
    }

    public void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        java.nio.file.Path source = Paths.get((file.getPath()));
        java.nio.file.Path targetDir = Paths.get("Labels");
        try {
            Files.createDirectories(targetDir);//in case target directory didn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        java.nio.file.Path target = targetDir.resolve(System.currentTimeMillis() + ".jpeg");// create new path ending with `name` content
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(target.toUri().toString());
        imageView.setImage(image);
    }

    public void ApplicationStatusChecker() {
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
    }

    public void updateApproved() {
        //SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
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
        CurrentlyBeingUpdated.getApplication().getAlcohol().setAlcoholContent(alcoholContent);
        applicant.updateApplication(CurrentlyBeingUpdated);

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
