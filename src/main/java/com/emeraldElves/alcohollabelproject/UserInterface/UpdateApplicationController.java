package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import com.emeraldElves.alcohollabelproject.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class UpdateApplicationController {
    @FXML
    private Button selectRev;
    @FXML
    private CheckBox option1, option2, option3, option4, option5, option6, option7, option8, option9, option10, option11, option12;
    @FXML
    private CheckBox option13, option14, option15, option16, option17, option18, option19, option20, option21, option22, option23;
    @FXML
    private CheckBox option24, option25, option26, option27, option28, option29, option30, option31, option32, option33, option34;
    @FXML
    private TextField repIDNoTextField, permitNoTextField, alcoholName, brandNameField;
    @FXML
    private TextField addressField, phoneNumberField, emailAddressField, signatureField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField alcoholContentField, wineVintageYearField, pHLevelField;
    @FXML
    private Label welcomeApplicantLabel;
    @FXML
    private Button cancelApplication, submitBtn, submitLabel, saveApplication;
    @FXML
    private Label pSourceErrorField, pTypeErrorField, brandNameErrorField, alcContentErrorField, signatureErrorField;
    @FXML
    private TextField varietalText, appellationText, formulaText, serialText, extraInfoText;
    @FXML
    private Label varietalErrorField, serialErrorField, wineNumErrorField;
    @FXML
    private CheckBox certOfApproval, certOfExemption, distinctiveApproval;
    @FXML
    private TextField distinctiveText; //relates to distinctive approval
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox pTypeSelect, pSourceSelect, stateSelect;

    //Options for the comboBox fields
    private ObservableList<String> sourceList = FXCollections.observableArrayList("Imported", "Domestic");
    private ObservableList<String> typeList = FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits");
    private ObservableList<String> stateList = FXCollections.observableArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC",
            "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH",
            "OK", "OR", "MD", "MA", "MI", "MN", "MS", "MO", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");

    //Data for application type
    private ApplicationType appType;
    private ProxyLabelImage proxyLabelImage;

    //Applicant's info
    private String username;
    private ApplicantInterface applicant;
    private EmailAddress emailAddress;
    private int permitNum; //needs to be a string!!!!
    private String address;
    private PhoneNumber phoneNum;
    private int representativeID; //needs to be a string!!!!

    //Alcohol info
    private ProductSource pSource;
    private AlcoholType alcType;
    private String alcName;
    private String brandName;
    private int alcContent;
    private AlcoholInfo.Wine wineType = null;
    private String formula;
    private String serialNum; //needs to be a string!!!!
    private String extraInfo;
    private File file;

    private Main main;

    private SubmittedApplication application;

    public void init(Main main, SubmittedApplication application) {

        this.main = main;
        this.application = application;

    }

    public void init(Main main) {

        Main.loadFXML("/fxml/UpdateApplication.fxml", this);

        username= Authenticator.getInstance().getUsername();
        applicant = new ApplicantInterface(username);
        Log.console(username);
        welcomeApplicantLabel.setText("Welcome, " + applicant.getApplicant().getNamefromDB(username) + ".");
        emailAddress = new EmailAddress(applicant.getApplicant().getEmailAddress());
        permitNum = applicant.getApplicant().getPermitNumFromDB(username);
        address = applicant.getApplicant().getAddress();
        phoneNum = new PhoneNumber(applicant.getApplicant().getPhoneNum());
        representativeID = applicant.getApplicant().getRepresentativeID();
        //repIDNoTextField.setText(Integer.toString(representativeID));
        //permitNoTextField.setText(String.valueOf(permitNum));
        //addressField.setText(String.valueOf(address));
        //phoneNumberField.setText((phoneNum.getPhoneNumber()));
        //emailAddressField.setText((emailAddress.getEmailAddress()));

        datePicker.setValue(LocalDate.now());
        stateSelect.setValue("State Abb.");
        stateSelect.setItems(stateList);

        if (application.getApplication().getAlcohol().getOrigin() == ProductSource.DOMESTIC) {
            pSourceSelect.setValue("Domestic");
        } else if (application.getApplication().getAlcohol().getOrigin() == ProductSource.IMPORTED) {
            pSourceSelect.setValue("Imported");
        }

        pSourceSelect.setItems(sourceList);
        switch (application.getApplication().getAlcohol().getAlcoholType()) {
            case BEER:
                pTypeSelect.setValue("Malt Beverages");
                break;
            case WINE:
                pTypeSelect.setValue("Wine");
                break;
            case DISTILLEDSPIRITS:
                pTypeSelect.setValue("Distilled Spirits");
                break;
        }
        pTypeSelect.setItems(typeList);

        alcoholName.setText(String.valueOf(application.getApplication().getAlcohol().getName()));
        brandNameField.setText(String.valueOf(application.getApplication().getAlcohol().getBrandName()));
        alcoholContentField.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
        formulaText.setText(String.valueOf(application.getApplication().getAlcohol().getFormula()));
        serialText.setText(String.valueOf(application.getApplication().getAlcohol().getSerialNumber()));
        extraInfoText.setText(String.valueOf(application.getApplication().getExtraInfo()));
        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().vintageYear));
            pHLevelField.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().pH));
            varietalText.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().grapeVarietal));
            appellationText.setText(String.valueOf(application.getApplication().getAlcohol().getWineInfo().appellation));
        }
        disableAllFields();
    }

    public void nextPage() {
        init(main);

        if (option1.isSelected() || option2.isSelected() || option3.isSelected() || option4.isSelected()
                || option6.isSelected() || option8.isSelected() || option9.isSelected() || option14.isSelected()
                || option15.isSelected() || option26.isSelected() || option27.isSelected() || option28.isSelected()
                || option29.isSelected() || option30.isSelected() || option31.isSelected() || option23.isSelected()
                || option34.isSelected()) {
            submitLabel.setDisable(false);
        }
        if (option5.isSelected()) {
            wineVintageYearField.setDisable(false);
        }
        if (option7.isSelected()) {
            pHLevelField.setDisable(false);
        }
        if (option10.isSelected()) {
            formulaText.setDisable(false);
        }
        if (option11.isSelected()) {
            submitLabel.setDisable(false);
            alcoholContentField.setDisable(false);
        }
        if (option12.isSelected()) {
            alcoholContentField.setDisable(false);
        }
        if (option13.isSelected()) {
            alcoholContentField.setDisable(false);
        }
        if (option16.isSelected()) {
            stateSelect.setDisable(false);
        }
        if (option17.isSelected()) {
            formulaText.setDisable(false);
        }
        if (option18.isSelected()) {
            datePicker.setDisable(false);
        }
        if (option19.isSelected()) {
            alcoholName.setDisable(false);
            brandNameField.setDisable(false);
            addressField.setDisable(false);
        }
        if (option20.isSelected()) {
            alcoholName.setDisable(false);
            addressField.setDisable(false);
            brandNameField.setDisable(false);
        }
        if (option21.isSelected()) {
            alcoholName.setDisable(false);
            addressField.setDisable(false);
            brandNameField.setDisable(false);
        }
        if (option22.isSelected()) {
            submitLabel.setDisable(false);
            extraInfoText.setDisable(false);
        }
        if (option24.isSelected()) {
            addressField.setDisable(false);
        }
        if (option25.isSelected()) {
            repIDNoTextField.setDisable(false);
        }
        if (option32.isSelected()) {
            formulaText.setDisable(false);
            submitLabel.setDisable(false);
        }
        if (option33.isSelected()) {
            extraInfoText.setDisable(false);
            submitLabel.setDisable(false);
        }

        //go to page 2 of update app

    }

    public void submitApp() {

        Boolean formFilled = false;
        Boolean fieldsValid = false;

        //errors are printed only if required fields are not filled in

        //malt beverages: don't need alcohol content
        if (!isInt(alcoholContentField)) {
            alcContentErrorField.setText("Please enter a valid number.");
        } else {
            alcContentErrorField.setText("");
        }

        if (alcoholContentField.getText().isEmpty()) {
            alcContentErrorField.setText("Please fill in the alcohol percentage.");
        } else if (!isInt(alcoholContentField)) {
            alcContentErrorField.setText("Please enter a valid number.");
        } else {
            alcContentErrorField.setText("");
        }

        if (signatureField.getText().isEmpty()) {
            signatureErrorField.setText("Please fill in the signature field.");
        } else {
            signatureErrorField.setText("");
        }
        if (pTypeSelect.getValue().equals("Wine")) {
            if (!isInt(wineVintageYearField) || !isDouble(pHLevelField)) {
                wineNumErrorField.setText("Please enter a valid number.");
            } else {
                wineNumErrorField.setText("");
            }
        }

        //check if required fields are filled in
        if (!alcoholContentField.getText().isEmpty() && !signatureField.getText().isEmpty()) {
            formFilled = true;
        }


        //check if fields are valid
        if (isInt(alcoholContentField)) {
            if (pTypeSelect.getValue().equals("Wine")) {
                if (isInt(wineVintageYearField) && isDouble(pHLevelField)) {
                    fieldsValid = true;
                }
            } else fieldsValid = true;
        }

        if (formFilled && fieldsValid) {
            if (pTypeSelect.getValue().equals("Wine")) {
                int vintageYr = 0;
                double pH = 0.0;
                String varietal = "";
                String appellation = "";
                if (!wineVintageYearField.getText().isEmpty())
                    vintageYr = Integer.parseInt(wineVintageYearField.getText());
                if (!pHLevelField.getText().isEmpty())
                    pH = Double.parseDouble(pHLevelField.getText());
                if (!varietalText.getText().isEmpty())
                    varietal = varietalText.getText();
                if (!appellationText.getText().isEmpty())
                    appellation = appellationText.getText();
                wineType = new AlcoholInfo.Wine(pH, vintageYr, varietal, appellation);
            }

            alcContent = Integer.parseInt(alcoholContentField.getText());


//            if (application != null)
//                newApp.setApplicationID(application.getApplicationID());

            //Submit the new application to the database
            //ApplicantInterface applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
            //boolean success = applicantInterface.submitApplication(newApp);
            System.out.println("Form deleted");
            main.loadHomepage();
        }
    }

    public void cancelApp() {
        //Go back to homepage
        System.out.println("Check github ;)");
        main.loadApplicantWorkflowPage();
    }

    public void saveApp() {

    }

    public boolean isInt(TextField txt) {
        try {
            Integer.parseInt(txt.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(TextField txt) {
        try {
            Double.parseDouble(txt.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void disableAllFields() {
        pSourceSelect.setDisable(true);
        pTypeSelect.setDisable(true);
        alcoholName.setDisable(true);
        brandNameField.setDisable(true);
        alcoholContentField.setDisable(true);
        formulaText.setDisable(true);
        serialText.setDisable(true);
        extraInfoText.setDisable(true);
        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            wineVintageYearField.setDisable(true);
            pHLevelField.setDisable(true);
            varietalText.setDisable(true);
            appellationText.setDisable(true);
        }
    }

    public void exemptionChecked() {
        stateSelect.setDisable(!certOfExemption.isSelected());
    }

    public void distinctiveChecked() {
        distinctiveText.setDisable(!distinctiveApproval.isSelected());
    }

    public void submitImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*jpeg", "*png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        java.nio.file.Path source = Paths.get((file.getPath()));
        java.nio.file.Path targetDir = Paths.get("Labels");
        try {
            Files.createDirectories(targetDir);//in case target directory didn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = (System.currentTimeMillis() + ".jpeg");
        java.nio.file.Path target = targetDir.resolve(fileName);// create new path ending with `name` content
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(target.toUri().toString());
        imageView.setImage(image);
        proxyLabelImage = new ProxyLabelImage(fileName);
        //application.setImage(proxyLabelImage);
    }


}