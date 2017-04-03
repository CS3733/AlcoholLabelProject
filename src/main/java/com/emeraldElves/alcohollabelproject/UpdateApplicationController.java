package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import com.emeraldElves.alcohollabelproject.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.ProductSource;
import com.emeraldElves.alcohollabelproject.AlcoholType;

/**
 * Created by Keion Bisland on 4/2/2017.
 */

public class UpdateApplicationController {

   public ApplicationStatus status;//get status from database

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
    DatePicker datePicker;


    public void ApplicationStatuschecker()
    {
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


    public void updateRejected()
    {
        SubmittedApplication CurrentlyBeingUpdated = null;

        repIDNoTextField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getRepresentativeID()));
        permitNoTextField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPermitNum()));
        brandNameField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getBrandName()));
        AddressField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhysicalAddress()));
        phoneNumberField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getPhoneNumber()));
        emailAddressField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getManufacturer().getEmailAddress()));
        alcoholContentField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholContent()));
        datePicker.setPromptText(String.valueOf(CurrentlyBeingUpdated.getApplication().getSubmissionDate()));
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
            wineVintageYearField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().getvintageYear()));
            phLevelField.setText(String.valueOf(CurrentlyBeingUpdated.getApplication().getAlcohol().getWineInfo().getpH()));
        }
        else if(CurrentlyBeingUpdated.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER)
        {
            ProductType_Beer.setSelected(true);
        }

    }

    public void updateApproved()
    {


    }



}
