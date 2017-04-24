package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Applicant;
import com.emeraldElves.alcohollabelproject.ApplicantInterface;
import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import com.emeraldElves.alcohollabelproject.LogManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateApprovedAppController {
    @FXML
    private TextField repIDNoTextField, permitNoTextField, alcoholName, brandNameField;
    @FXML
    private TextField addressField, phoneNumberField, emailAddressField, signatureField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField alcoholContentField, wineVintageYearField, pHLevelField;
    @FXML
    Label welcomeApplicantLabel;
    @FXML
    private Button submitBtn, saveApplication, cancelApplication, submitLabel;
    @FXML
    private Label permitNoErrorField, addressErrorField, phoneNumErrorField, emailErrorField;
    @FXML
    private Label pSourceErrorField, pTypeErrorField, brandNameErrorField, varietalErrorField, serialErrorField;
    @FXML
    private Label alcContentErrorField, signatureErrorField;
    @FXML
    private TextField varietalText, appellationText, formulaText, serialText,extraInfoText;
    @FXML
    private CheckBox certOfApproval, certOfExemption, distinctiveApproval;
    @FXML
    TextField distinctiveText; //relates to distinctive approval
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox pTypeSelect, pSourceSelect, allowedUpdatesSelect;

    //Options for the updates comboBox fields
    ObservableList<String> wineUpdatesList = FXCollections.observableArrayList("Delete non-mandatory label information", "Reposition label information", "Change label format (color, font, size of label, etc)",
            "Edit percentages of grape varietals and appellations on label", "Edit the vintage date on label", "Change 'produced' on label to 'blended' or 'prepared' statement", "Edit the pH level on label",
            "Change stated amount of sugar on label", "Edit bonded winery or taxpaid bottling house number on label", "Change the net contents statement", "Edit alcohol content");
    ObservableList<String> beerUpdatesList = FXCollections.observableArrayList("Delete non-mandatory label information", "Reposition label information", "Change label format (color, font, size of label, etc)",
            "Change the net contents statement", "Edit alcohol content", "Edit optional statement of alcohol content on label");
    ObservableList<String> spiritsUpdatesList = FXCollections.observableArrayList("Delete non-mandatory label information", "Reposition label information", "Change label format (color, font, size of label, etc)",
            "Change the net contents statement", "Edit alcohol content");

    ObservableList<String> sourceList = FXCollections.observableArrayList("Imported", "Domestic");
    ObservableList<String> typeList = FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits");

    //Data for application type
    public ApplicationType appType;
    ProxyLabelImage proxyLabelImage;

    //Applicant's info
    private String username;
    private ApplicantInterface applicant;
    private EmailAddress emailAddress;
    private int permitNum;
    private String address;
    private PhoneNumber phoneNum;
    private int representativeID;
    private ManufacturerInfo appManInfo = null;

    //Alcohol info
    private ProductSource pSource;
    private AlcoholType alcType;
    private String alcName;
    private String brandName;
    private int alcContent;
    private AlcoholInfo.Wine wineType = null; //null if type is not wine
    private String formula;
    private String serialNum;
    private String extraInfo;
    private AlcoholInfo appAlcoholInfo = null;

    private Main main;

    private SubmittedApplication application;

    public void init(Main main){
        this.main = main;

//        username= Authenticator.getInstance().getUsername();
//        applicant = new ApplicantInterface(username);
//        welcomeApplicantLabel.setText("Welcome, " + String.valueOf(applicant.getApplicant().getName()) + ".");
//
//        emailAddress = applicant.getApplicant().getEmailAddress();
//        permitNum = applicant.getApplicant().getPermitNum();
//        address = applicant.getApplicant().getAddress();
//        phoneNum = applicant.getApplicant().getPhoneNum();
//        representativeID = applicant.getApplicant().getRepresentativeID();
//        repIDNoTextField.setText(String.valueOf(representativeID));
//        permitNoTextField.setText(String.valueOf(permitNum));
//        addressField.setText(String.valueOf(address));
//        phoneNumberField.setText(String.valueOf(phoneNum));
//        emailAddressField.setText(String.valueOf(emailAddress));

//        example manufacturer info
        ManufacturerInfo manInfo= new ManufacturerInfo("Bob", "1 Institute Rd", "", 1234, 1111, new PhoneNumber("9789789788"), new EmailAddress("test@test.com"));
        welcomeApplicantLabel.setText("Welcome, " + String.valueOf(manInfo.getName()) + ".");
        repIDNoTextField.setText(String.valueOf(manInfo.getRepresentativeID()));
        permitNoTextField.setText(String.valueOf(manInfo.getPermitNum()));
        addressField.setText(String.valueOf(manInfo.getPhysicalAddress()));
        phoneNumberField.setText(String.valueOf(manInfo.getPhoneNumber().getPhoneNumber()));
        emailAddressField.setText(String.valueOf(manInfo.getEmailAddress().getEmailAddress()));

        datePicker.setValue(LocalDate.now());

        pSourceSelect.setValue("Select a product source");
        pSourceSelect.setItems(sourceList);
        pTypeSelect.setValue("Select a product type");
        pTypeSelect.setItems(typeList);

    }

    public void init(Main main, SubmittedApplication application) {
        init(main);
        this.application = application;

        if (application.getApplication().getAlcohol().getOrigin() == ProductSource.DOMESTIC) {
            pSourceSelect.setValue("Domestic");
        } else if (application.getApplication().getAlcohol().getOrigin() == ProductSource.IMPORTED) {
            pSourceSelect.setValue("Imported");
        }

        if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER) {
            pTypeSelect.setValue("Beer");
            allowedUpdatesSelect.setItems(beerUpdatesList);
        } else if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE) {
            pTypeSelect.setValue("Wine");
            allowedUpdatesSelect.setItems(wineUpdatesList);
        } else if (application.getApplication().getAlcohol().getAlcoholType() == AlcoholType.DISTILLEDSPIRITS) {
            pTypeSelect.setValue("Distilled Spirits");
            allowedUpdatesSelect.setItems(spiritsUpdatesList);
        }

        allowedUpdatesSelect.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldVal, String newVal) {
                if(allowedUpdatesSelect.getValue().equals("Delete non-mandatory label information")||
                        allowedUpdatesSelect.getValue().equals("Reposition label information")||
                        allowedUpdatesSelect.getValue().equals("Change label format (color, font, size of label, etc)")||
                        allowedUpdatesSelect.getValue().equals("Change the net contents statement")){
                    submitLabel.setDisable(false);
                } else if(allowedUpdatesSelect.getValue().equals("Edit alcohol content")){
                    alcoholContentField.setDisable(false);
                    submitLabel.setDisable(false);
                } else if(allowedUpdatesSelect.getValue().equals("Edit optional statement of alcohol content on label")){
                    submitLabel.setDisable(false);
                } else if(allowedUpdatesSelect.getValue().equals("Edit percentages of grape varietals and appellations on label")){
                    varietalText.setDisable(false);
                    appellationText.setDisable(false);
                    submitLabel.setDisable(false);
                } else if(allowedUpdatesSelect.getValue().equals("Edit the vintage date on label")){
                    wineVintageYearField.setDisable(false);
                    submitLabel.setDisable(false);
                } else if(allowedUpdatesSelect.getValue().equals("Edit the pH level on label")){
                    pHLevelField.setDisable(false);
                    submitLabel.setDisable(false);
                } else if(allowedUpdatesSelect.getValue().equals("Change 'produced' on label to 'blended' or 'prepared' statement")||
                        allowedUpdatesSelect.getValue().equals("Change stated amount of sugar on label")||
                        allowedUpdatesSelect.getValue().equals("Edit bonded winery or taxpaid bottling house number on label")){
                    submitLabel.setDisable(false);
                } else{ //nothing to change
                }
            }
        });
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
    }


    public void submitApp() {
        LogManager.getInstance().logAction("UpdateApprovedAppController", "Logged Click from first page of update Application");

        boolean formFilled = false;
        boolean fieldsValid = false;


        if (alcoholContentField.getText().isEmpty()) {
            alcContentErrorField.setText("Please fill in the alcohol percentage.");
        } else if(!isInt(alcoholContentField)){
            alcContentErrorField.setText("Please enter a valid number.");
        } else {
            alcContentErrorField.setText("");
        }

        if (signatureField.getText().isEmpty()) {
            signatureErrorField.setText("Please fill in the signature field.");
        } else {
            signatureErrorField.setText("");
        }

        //check if required fields are filled in
        if (!signatureField.getText().isEmpty()) {
            formFilled = true;
        }

        //check if fields are valid
        if(isInt(alcoholContentField)){
            fieldsValid=true;
        }

        if (formFilled && fieldsValid) {

            if (pTypeSelect.getValue().equals("Wine")) {
//                    int vintageYr = 0;
//                    double pH = 0.0;
//                    String varietal = "";
//                    String appellation = "";
                if (!wineVintageYearField.getText().isEmpty())
                    application.getApplication().getAlcohol().getWineInfo().vintageYear = Integer.parseInt(wineVintageYearField.getText()); //CHECK IF INPUT INTEGER!
                if (!pHLevelField.getText().isEmpty())
                    application.getApplication().getAlcohol().getWineInfo().pH = Double.parseDouble(pHLevelField.getText()); //CHECK IF INPUT INTEGER!
                if (!varietalText.getText().isEmpty())
                    application.getApplication().getAlcohol().getWineInfo().grapeVarietal = varietalText.getText();
                if (!appellationText.getText().isEmpty())
                    application.getApplication().getAlcohol().getWineInfo().appellation = appellationText.getText();
                //wineType = new AlcoholInfo.Wine(pH, vintageYr, varietal, appellation);
            }

            //sets alc info fields
            alcName = alcoholName.getText();
            brandName = brandNameField.getText();
            alcContent = Integer.parseInt(alcoholContentField.getText()); //CHECK IF INTEGER
            serialNum = serialText.getText();
            if (formulaText.getText().isEmpty()) {
                formula = " ";
            } else formula = formulaText.getText();
            if (extraInfoText.getText().isEmpty()) {
                extraInfo = " ";
            } else extraInfo = extraInfoText.getText();

            //application.getApplication().getAlcohol().setFormula(formula);
            application.getApplication().setExtraInfo(extraInfo);
            application.getApplication().getAlcohol().setAlcoholContent(alcContent);


            //sets the alcohol info
            appAlcoholInfo = new AlcoholInfo(alcContent, alcName, brandName, pSource, alcType, wineType, serialNum, formula);

            //sets the date value
            Date newDate = DateHelper.getDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue() - 1, datePicker.getValue().getYear());

            ApplicationInfo appInfo = new ApplicationInfo(newDate, this.appManInfo, appAlcoholInfo, extraInfo, appType);

            //!!!!!placeholder for applicant's submitted applications!!!!!
            List<SubmittedApplication> appList = new ArrayList<>();

            //Create applicant to store in submitted application
            Applicant applicant = new Applicant(appList);

            //Create a SubmittedApplication
//                application.
//                SubmittedApplication newApp = new SubmittedApplication(appInfo, ApplicationStatus.APPROVED, applicant);
            if (application != null)
                application.setApplicationID(application.getApplicationID());
            applicant.addSubmittedApp(application);
            application.setImage(proxyLabelImage);

            if (application != null)
                application.setApplicationID(application.getApplicationID());

            //Submit the new application to the database
            ApplicantInterface applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
            boolean success = applicantInterface.submitApplication(application);

            main.loadHomepage();
        }
    }

    public void cancelApp() {
        //Go back to homepage
        main.loadApplicantWorkflowPage();
    }

    public void saveApp() {

    }

    public void logout() {
        Authenticator.getInstance().logout();
        main.loadHomepage();
    }

    public boolean isInt(TextField txt){
        try {
            Integer.parseInt(txt.getText());
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public void submitImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg","*jpeg","*png");
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