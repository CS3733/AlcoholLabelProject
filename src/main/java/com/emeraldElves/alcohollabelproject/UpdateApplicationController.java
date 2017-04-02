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


    }

    public void updateApproved()
    {


    }



}
