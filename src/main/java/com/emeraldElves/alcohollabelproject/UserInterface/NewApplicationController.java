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

public class NewApplicationController {
    @FXML
    TextField repIDNoTextField;
    @FXML
    TextField permitNoTextField;
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
    Label welcomeApplicantLabel;
    @FXML
    Button saveApplication;
    @FXML
    Button cancelApplication;
    @FXML
    Button nextPageBtn;
    @FXML
    Button submitBtn;
    @FXML
    Button logoutBtn;
    @FXML
    Label permitNoErrorField;
    @FXML
    Label addressErrorField;
    @FXML
    Label phoneNumErrorField;
    @FXML
    Label emailErrorField;
    @FXML
    Label pSourceErrorField;
    @FXML
    Label pTypeErrorField;
    @FXML
    Label brandNameErrorField;
    @FXML
    Label alcContentErrorField;
    @FXML
    Label signatureErrorField;
    @FXML
    TextField varietalText;
    @FXML
    TextField appellationText;
    @FXML
    TextField formulaText;
    @FXML
    Label varietalErrorField;
    @FXML
    TextField serialText;
    @FXML
    TextField extraInfoText;
    @FXML
    Label serialErrorField;
    @FXML
    CheckBox certOfApproval;
    @FXML
    CheckBox certOfExemption;
    @FXML
    CheckBox distinctiveApproval;
    @FXML
    TextField distinctiveText;//relates to distinctive approval
    @FXML
    Button submitLabel;
    @FXML
    ImageView imageView;
    @FXML
    ComboBox pTypeSelect;
    @FXML
    ComboBox pSourceSelect;
    @FXML
    ComboBox stateSelect;

    //Options for the comboBox fields
    ObservableList<String> sourceList = FXCollections.observableArrayList("Imported", "Domestic");
    ObservableList<String> typeList = FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits");
    ObservableList<String> stateList = FXCollections.observableArrayList("AL","AK","AZ","AR","CA","CO","CT","DE","DC","FL","GA","HI","ID","IL",
            "IN","IA","KS","KY","LA","ME","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","MD","MA","MI","MN","MS","MO","PA","RI","SC",
            "SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
    
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
        repIDNoTextField.setText(String.valueOf(representativeID));
        permitNoTextField.setText(String.valueOf(permitNum));
        addressField.setText(String.valueOf(address));
        phoneNumberField.setText(String.valueOf(phoneNum));
        emailAddressField.setText(String.valueOf(emailAddress));

//        example manufacturer info
//        ManufacturerInfo manInfo= new ManufacturerInfo("Bob", "1 Institute Rd", "", 1234, 1111, new PhoneNumber("9789789788"), new EmailAddress("test@test.com"));
//        welcomeApplicantLabel.setText("Welcome, " + String.valueOf(manInfo.getName()) + ".");
//        repIDNoTextField.setText(String.valueOf(manInfo.getRepresentativeID()));
//        permitNoTextField.setText(String.valueOf(manInfo.getPermitNum()));
//        addressField.setText(String.valueOf(manInfo.getPhysicalAddress()));
//        phoneNumberField.setText(String.valueOf(manInfo.getPhoneNumber().getPhoneNumber()));
//        emailAddressField.setText(String.valueOf(manInfo.getEmailAddress().getEmailAddress()));

        datePicker.setValue(LocalDate.now());
        stateSelect.setValue("State Abb.");
        stateSelect.setItems(stateList);
        pSourceSelect.setValue("Select a product source");
        pSourceSelect.setItems(sourceList);
        pTypeSelect.setValue("Select a product type");
        pTypeSelect.setItems(typeList);
        pTypeSelect.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldVal, String newVal) {
                if(pTypeSelect.getValue().equals("Wine")){
                    showTheWine();
                } else hideTheWine();
            }
        });
    }

    public void init(Main main, SubmittedApplication application) {
        init(main);
        this.application = application;

        alcoholName.setText(String.valueOf(application.getApplication().getAlcohol().getName()));
        brandNameField.setText(String.valueOf(application.getApplication().getAlcohol().getBrandName()));
        alcoholContentField.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
        formulaText.setText(String.valueOf(application.getApplication().getAlcohol().getFormula()));
        serialText.setText(String.valueOf(application.getApplication().getAlcohol().getSerialNumber()));
        extraInfoText.setText(String.valueOf(application.getApplication().getExtraInfo()));
    }

    public void submitApp() {
        LogManager.getInstance().logAction("newApplicationController", "Logged Click from first page of the new Application");

        Boolean formFilled = false;
        Boolean fieldsValid = false;

        //filling out application type
        boolean labelApproval;
        String stateOnly;
        int bottleCapacity;
        //14a
        labelApproval = certOfApproval.isSelected();
        //14b
        if (certOfExemption.isSelected()) {
            if(!stateSelect.getValue().equals("State Abb."))
                stateOnly = (String)stateSelect.getValue();
            else stateOnly = "";
        } else {
            stateOnly = "";
        }
        //14c
        if (distinctiveApproval.isSelected()) {
            bottleCapacity = Integer.parseInt(distinctiveText.getText());
        } else {
            bottleCapacity = -1;
        }

        appType = new ApplicationType(labelApproval, stateOnly, bottleCapacity);

            //errors are printed only if required fields are not filled in
            if (pSourceSelect.getValue().equals("Select a product source")) {
                pSourceErrorField.setText("Please select the alcohol source.");
            } else {
                pSourceErrorField.setText("");
            }
            if (pTypeSelect.getValue().equals("Select a product type")) {
                pTypeErrorField.setText("Please select the alcohol type.");
            } else {
                pTypeErrorField.setText("");
            }
            if (brandNameField.getText().isEmpty()) {
                brandNameErrorField.setText("Please fill in the brand name.");
            } else {
                brandNameErrorField.setText("");
            }
            if (alcoholContentField.getText().isEmpty()) {
                alcContentErrorField.setText("Please fill in the alcohol percentage.");
            } else if(!isInt(alcoholContentField)){
                alcContentErrorField.setText("Please enter a valid number.");
            } else {
                alcContentErrorField.setText("");
            }
            if (serialText.getText().isEmpty()) {
                serialErrorField.setText("Please input a serial number");
            } else if(!isInt(serialText)){
                serialErrorField.setText("Please enter a valid number.");
            } else {
                serialErrorField.setText("");
            }
            if (signatureField.getText().isEmpty()) {
                signatureErrorField.setText("Please fill in the signature field.");
            } else {
                signatureErrorField.setText("");
            }

            //check if required fields are filled in
            if ((!pTypeSelect.getValue().equals("Select a product type")) &&
                    (!pSourceSelect.getValue().equals("Select a product source")) &&
                    !brandNameField.getText().isEmpty() && !alcoholContentField.getText().isEmpty() &&
                    !signatureField.getText().isEmpty() && !serialText.getText().isEmpty()) {
                formFilled = true;
            }

            //check if fields are valid
            if(isInt(alcoholContentField)&&isInt(serialText)){
                    fieldsValid=true;
            }

            if (formFilled && fieldsValid) {

                if (pTypeSelect.getValue().equals("Wine")) {
                    int vintageYr = 0;
                    double pH = 0.0;
                    String varietal = "";
                    String appellation = "";
                    if (!wineVintageYearField.getText().isEmpty())
                        vintageYr = Integer.parseInt(wineVintageYearField.getText()); //CHECK IF INPUT INTEGER!
                    if (!pHLevelField.getText().isEmpty())
                        pH = Double.parseDouble(pHLevelField.getText()); //CHECK IF INPUT INTEGER!
                    if (!varietalText.getText().isEmpty())
                        varietal = varietalText.getText();
                    if (!appellationText.getText().isEmpty())
                        appellationText.getText();
                    wineType = new AlcoholInfo.Wine(pH, vintageYr, varietal, appellation);
                }

                //Checking if the product is domestic or imported
                if (pSourceSelect.getValue().equals("Domestic")) {
                    pSource = ProductSource.DOMESTIC;
                } else if (pSourceSelect.getValue().equals("Imported")) {
                    pSource = ProductSource.IMPORTED;
                }

                //Checking if the product is a beer, wine or spirits
                if (pTypeSelect.getValue().equals("Beer")) {
                    alcType = AlcoholType.BEER;
                } else if (pTypeSelect.getValue().equals("Wine")) {
                    alcType = AlcoholType.WINE;
                } else if (pTypeSelect.getValue().equals("Distilled Spirits")) {
                    alcType = AlcoholType.DISTILLEDSPIRITS;
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
                SubmittedApplication newApp = new SubmittedApplication(appInfo, ApplicationStatus.PENDINGREVIEW, applicant);
                if (application != null)
                    newApp.setApplicationID(application.getApplicationID());
                applicant.addSubmittedApp(newApp);
                newApp.setImage(proxyLabelImage);

                if (application != null)
                    newApp.setApplicationID(application.getApplicationID());

                //Submit the new application to the database
                ApplicantInterface applicantInterface = new ApplicantInterface(Authenticator.getInstance().getUsername());
                boolean success = applicantInterface.submitApplication(newApp);

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

    public void exemptionChecked(){
        stateSelect.setDisable(!certOfExemption.isSelected());
    }

    public void distinctiveChecked(){
        distinctiveText.setDisable(!distinctiveApproval.isSelected());
    }

    public void hideTheWine(){
        wineVintageYearField.setDisable(true);
        pHLevelField.setDisable(true);
        varietalText.setDisable(true);
        appellationText.setDisable(true);
    }

    public void showTheWine(){
        wineVintageYearField.setDisable(false);
        pHLevelField.setDisable(false);
        varietalText.setDisable(false);
        appellationText.setDisable(false);
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
