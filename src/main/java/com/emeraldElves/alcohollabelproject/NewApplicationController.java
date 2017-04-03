package com.emeraldElves.alcohollabelproject;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

import static com.emeraldElves.alcohollabelproject.Main.loadFXML;

public class NewApplicationController {
    @FXML
    TextField repIDNoTextField;
    @FXML
    TextField permitNoTextField;
    @FXML
    RadioButton international;
    @FXML
    RadioButton domestic;
    @FXML
    RadioButton beer;
    @FXML
    RadioButton wine;
    @FXML
    TextField alcoholName;
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
    Button submit;
    @FXML
    Label error;

    public void submitApp() {

        ToggleGroup productType = new ToggleGroup();
        beer.setToggleGroup(productType);
        wine.setToggleGroup(productType);

        ToggleGroup productSource = new ToggleGroup();
        international.setToggleGroup(productSource);
        domestic.setToggleGroup(productSource);

        if(repIDNoTextField.getText().isEmpty()) {
            error.setText("Please fill in the representative ID");
            return;
        }
        if(permitNoTextField.getText().isEmpty()) {
            error.setText("Please fill in the Permit Number");
            return;
        }
        if(productType.getSelectedToggle() == null) {
            error.setText("Please select Whether your product is international or domestic");
            return;
        }
        if(productSource.getSelectedToggle() == null) {
            error.setText("Please select what Type of Alcohol you are selling");
            return;
        }
        if(alcoholName.getText().isEmpty()) {
            error.setText("Please fill in your alcohols name");
            return;
        }
        if(brandNameField.getText().isEmpty()) {
            error.setText("Please fill in your brand name");
            return;
        }
        if(addressField.getText().isEmpty()) {
            error.setText("Please fill in the physical address of your company");
            return;
        }
        if(phoneNumberField.getText().isEmpty()) {
            error.setText("Please fill in the contact number");
            return;
        }
        if(emailAddressField.getText().isEmpty()) {
            error.setText("Please fill in the contact email");
            return;
        }
        if(signatureField.getText().isEmpty()) {
            error.setText("Please fill in the signature field");
            return;
        }
        if(datePicker == null) {
            error.setText("Please fill in the date of your alcohol");
            return;
        }
        if(alcoholContentField.getText() == null) {
            error.setText("Please fill in the alcohol content of your beverage");
            return;
        }
        if(productType.getSelectedToggle() == wine && wineVintageYearField.getText() == null) {
            error.setText("Please fill in the wine vintage year");
            return;
        }
        if(productType.getSelectedToggle() == wine && pHLevelField.getText() == null) {
            error.setText("Please fill in the ph level");
            return;
        }

        AlcoholDatabase alcoholDB = new AlcoholDatabase(Main.database);
        EmailAddress appEmail = new EmailAddress(emailAddressField.getText());
        PhoneNumber appPhone = new PhoneNumber(phoneNumberField.getText());
        ManufacturerInfo appManInfo = new ManufacturerInfo("placeholder", addressField.getText(), brandNameField.getText(),
                Integer.parseInt(repIDNoTextField.getText()), Integer.parseInt(permitNoTextField.getText()), appPhone, appEmail);
        ProductSource pSource = ProductSource.DOMESTIC;
        AlcoholInfo appAlcoholInfo =  new AlcoholInfo(Integer.parseInt(alcoholContentField.getText()), alcoholName.getText(), brandNameField.getText(), pSource);

        if(productSource.getSelectedToggle() == domestic) {
            pSource = ProductSource.DOMESTIC;
        }
        if(productSource.getSelectedToggle() == international) {
            pSource = ProductSource.IMPORTED;
        }
        if(productType.getSelectedToggle() == beer) {
            appAlcoholInfo = new BeerInfo(Integer.parseInt(alcoholContentField.getText()), alcoholName.getText(), brandNameField.getText(), pSource);
        }
        if(productType.getSelectedToggle() == wine) {
            appAlcoholInfo = new WineInfo(Integer.parseInt(alcoholContentField.getText()), alcoholName.getText(), brandNameField.getText(), pSource,
                    Integer.parseInt(wineVintageYearField.getText()), Integer.parseInt(pHLevelField.getText()));
        }

        java.sql.Date newDate = java.sql.Date.valueOf(datePicker.getValue());
        ApplicationInfo appInfo = new ApplicationInfo(newDate, appManInfo, appAlcoholInfo);

        ApplicationStatus placeholder = ApplicationStatus.APPROVED;

        List<SubmittedApplication> appliers = new ArrayList();
        Applicant applicant = new Applicant(appliers);

        SubmittedApplication newApp = new SubmittedApplication(appInfo, placeholder, applicant);
        alcoholDB.submitApplication(newApp);
    }

    public void cancelApp() {

        loadFXML("mainGUI.FXML");
    }
    public void saveApp() {
    }
}
