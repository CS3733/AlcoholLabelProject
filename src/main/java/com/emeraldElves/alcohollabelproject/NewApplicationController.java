package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import static com.emeraldElves.alcohollabelproject.Main.loadFXML;

public class NewApplicationController {
    @FXML
    TextField reIDNoTextField;
    @FXML
    TextField permitNoTextField;
    @FXML
    RadioButton productSourceRadios;
    @FXML
    RadioButton productTypeRadios;
    @FXML
    TextField brandNameField;
    @FXML
    TextField addressField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField emailAddressField;
    @FXML
    TextField signatureField;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField alcoholContentField;
    @FXML
    TextField wineVintageYearField;
    @FXML
    TextField pHLevelField;
    @FXML
    Button saveApplication;
    @FXML
    Button cancelApplication;
    @FXML
    Button Submit;

    public void submitApp() {
        AlcoholDatabase alcoholDB = new AlcoholDatabase(Main.database);
    }

    public void cancelApp() {

        loadFXML("mainGUI.FXML");
    }
    public void saveApp() {
    }
}
