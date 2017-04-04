package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.emeraldElves.alcohollabelproject.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.ProductSource;
import com.emeraldElves.alcohollabelproject.AlcoholType;
import com.emeraldElves.alcohollabelproject.SubmittedApplication;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.Instant;
/**
 * Created by Keion Bisland on 4/2/2017.
 */

public class UpdateApplicationController {

   public ApplicationStatus status;//get status from database
    @FXML
    TextField brandNameField;
    @FXML
    TextField alcoholContentField;
    @FXML
    TextField wineVintageYearField;
    @FXML
    TextField phLevelField;
    @FXML
    TextField fancifulName;
    @FXML
    TextField signatureField;
    @FXML
    Button submitBtn;
    @FXML
    DatePicker datePicker;


    public void ApplicationStatuschecker() {
        switch(status)
        {
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


    public void updateRejected(){
        SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE)
        {
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER)
        {
        }
    }

    public void updateApproved() {
        SubmittedApplication CurrentlyBeingUpdated = null;
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));

        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE)
        {
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER)
        {
        }
    }

    public void submitApp(){
        String applicantname = null;

       SubmittedApplication CurrentlyBeingUpdated = null;

        String physicalAddress = CurrentlyBeingUpdated.getApplication().getManufacturer().getPhysicalAddress();
        String company = null;
        int representativeID = CurrentlyBeingUpdated.getApplication().getManufacturer().getRepresentativeID();;
        int permitNum = CurrentlyBeingUpdated.getApplication().getManufacturer().getPermitNum();
        PhoneNumber phoneNumber = new PhoneNumber(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhoneNumber()));
        EmailAddress emailAddress = new EmailAddress(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getEmailAddress()));
        String fancifulname = fancifulName.getText();
        String brandName = CurrentlyBeingUpdated.getApplication().getAlcohol().getBrandName();
        int pH = Integer.parseInt(phLevelField.getText()) ;
        int vintageYear = Integer.parseInt(wineVintageYearField.getText());
        int alcoholContent = Integer.parseInt(alcoholContentField.getText());
        ProductSource origin = CurrentlyBeingUpdated.getApplication().getAlcohol().getOrigin();
        AlcoholType alcoholType = CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType();
        AlcoholInfo.Wine wineInfo = new AlcoholInfo.Wine(vintageYear,pH);;
        Date submissionDate = java.sql.Date.valueOf(String.valueOf(datePicker.getValue())) ;
        ManufacturerInfo manufacturer =  new  ManufacturerInfo(applicantname, physicalAddress, company, representativeID, permitNum, phoneNumber, emailAddress);
        AlcoholInfo submittedAlcohol = new AlcoholInfo( alcoholContent,  fancifulname, brandName, origin, alcoholType, wineInfo);
        ApplicationInfo application = new ApplicationInfo( submissionDate,  manufacturer,  submittedAlcohol);
        Applicant applicant = new Applicant(null);
        SubmittedApplication UpdatedApplication =new SubmittedApplication(application,status,applicant);

    }

}
