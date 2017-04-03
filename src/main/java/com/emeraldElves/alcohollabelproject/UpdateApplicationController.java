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
    ToggleGroup ProductSourceGroup;
    ToggleGroup ProductTypeGroup;
    @FXML
    TextField repIDNoTextField;
    @FXML
    TextField permitNoTextField;
    @FXML
    TextField brandNameField;
    @FXML
    TextField AddressField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField emailAddressField;
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
    RadioButton InternationalRadio;
    @FXML
    RadioButton DomesticRadio;
    @FXML
    RadioButton ProductType_Beer;
    @FXML
    RadioButton ProductType_Wine;
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
                //idk what im supposed to do in this case rn
                break;

        }

    }


    public void updateRejected(){
        SubmittedApplication CurrentlyBeingUpdated = null;

        repIDNoTextField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getRepresentativeID()));
        permitNoTextField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPermitNum()));
        brandNameField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getBrandName()));
        AddressField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhysicalAddress()));
        phoneNumberField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhoneNumber()));
        emailAddressField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getEmailAddress()));
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getOrigin() == ProductSource.IMPORTED)
        {
            InternationalRadio.setSelected(true);
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getOrigin() == ProductSource.DOMESTIC)
        {
            DomesticRadio.setSelected(true);
        }
        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE)
        {
            ProductType_Wine.setSelected(true);
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER)
        {
            ProductType_Beer.setSelected(true);
        }


    }

    public void updateApproved() {
        SubmittedApplication CurrentlyBeingUpdated = null;
        Date date = new Date();
        Instant instant = (CurrentlyBeingUpdated.getApplication().getSubmissionDate().toInstant());
        repIDNoTextField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getRepresentativeID()));
        permitNoTextField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPermitNum()));
        brandNameField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getBrandName()));
        AddressField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhysicalAddress()));
        phoneNumberField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhoneNumber()));
        emailAddressField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getEmailAddress()));
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getOrigin() == ProductSource.IMPORTED)
        {
            InternationalRadio.setSelected(true);
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getOrigin() == ProductSource.DOMESTIC)
        {
            DomesticRadio.setSelected(true);
        }
        if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE)
        {
            ProductType_Wine.setSelected(true);
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().vintageYear));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().pH));
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER)
        {
            ProductType_Beer.setSelected(true);
        }


    }

    public void SubmitUpdateApplication(){
        String applicantname = null;
        String physicalAddress = AddressField.getText();
        String company = null;
        int representativeID = Integer.parseInt(repIDNoTextField.getText());
        int permitNum = Integer.parseInt(permitNoTextField.getText());
        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberField.getText());
        EmailAddress emailAddress = new EmailAddress(emailAddressField.getText());
        String fancifulname = fancifulName.getText();
        String brandName = brandNameField.getText();
        int pH = Integer.parseInt(phLevelField.getText()) ;
        int vintageYear = Integer.parseInt(wineVintageYearField.getText());
        int alcoholContent = Integer.parseInt(alcoholContentField.getText());
        ProductSource origin = null;
        AlcoholType alcoholType = null;
        AlcoholInfo.Wine wineInfo = null;
        if(ProductSourceGroup.getSelectedToggle() == InternationalRadio) {
             origin = ProductSource.IMPORTED;
        }
        else if(ProductSourceGroup.getSelectedToggle() == DomesticRadio) {
             origin = ProductSource.DOMESTIC;
        }
        if(ProductTypeGroup.getSelectedToggle() == ProductType_Beer) {
            alcoholType= AlcoholType.BEER;
             wineInfo = null;
        }
        else if(ProductTypeGroup.getSelectedToggle() == ProductType_Wine) {
             alcoholType = AlcoholType.WINE;
             wineInfo = new AlcoholInfo.Wine(vintageYear,pH);
        }


        Date submissionDate = java.sql.Date.valueOf(String.valueOf(datePicker.getValue())) ;

        ManufacturerInfo manufacturer =  new  ManufacturerInfo(applicantname, physicalAddress, company, representativeID, permitNum, phoneNumber, emailAddress);


        ApplicationStatus status = null;
        AlcoholInfo submittedAlcohol = new AlcoholInfo( alcoholContent,  fancifulname, brandName, origin, alcoholType, wineInfo);

        ApplicationInfo application = new ApplicationInfo( submissionDate,  manufacturer,  submittedAlcohol);



        Applicant applicant;
        SubmittedApplication CurrentlyBeingUpdated = null;

    }

}
