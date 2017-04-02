package com.emeraldElves.alcohollabelproject;;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import com.emeraldElves.alcohollabelproject.ApplicationStatus;

/**
 * Created by Keion Bisland on 4/2/2017.
 */

public class UpdateApplicationController {

   public ApplicationStatus status;//get status from database

    @FXML
    TextField repIDNoTextField;
    TextField permitNoTextField;
    TextField brandNameField;
    TextField AddressField;
    TextField phoneNumberField;
    TextField emailAddressField;
    TextField alcoholContentField;
    TextField wineVintageYearField;
    TextField phLevelField;
    TextField signatureField;

    @FXML
    RadioButton InternationalRadio;
    RadioButton DomesticRadio;
    RadioButton ProductType_Beer;
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
            case PENDING_REVIEW:
                //idk what im supposed to do in this case rn
                break;

        }

    }


    public void updateRejected()
    {


    }

    public void updateApproved()
    {


    }



}
