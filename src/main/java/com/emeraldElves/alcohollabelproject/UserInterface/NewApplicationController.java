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
    Label dateErrorField;
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
    Label formulaErrorField;
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
    TextField exemptionText;
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
    //@FXML
    //ComboBox pStateSelect;

    //Options for the comboBox fields
    ObservableList<String> sourceList = FXCollections.observableArrayList("Imported", "Domestic");
    ObservableList<String> typeList = FXCollections.observableArrayList("Malt Beverages", "Wine", "Distilled Spirits");
    //ObservableList<String> stateList = FXCollections.observableArrayList("AL","AK","AZ","AR","CA","CO","CT","DE","DC","FL","GA","HI","ID","IL",
    //"IN","IA","KS","KY","LA","ME","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","MD","MA","MI","MN","MS","MO","PA","RI","SC",
    //"SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");
    
    //Data for application type
    public ApplicationType appType;
    ProxyLabelImage proxyLabelImage;

    //Initializes and temporarily stores applicant's info
    private int repIDNo = -1; //means they didn't enter a rep ID num
    private int permitNo = 0;
    private String physicalAddress = null;
    private EmailAddress applicantEmail = null;
    private PhoneNumber applicantPhone = null;

    //Stores the manufacturer's info
    public ManufacturerInfo appManInfo = null;

    //Initializes and temporarily stores data fields for alc info
    private ProductSource pSource = null;
    private AlcoholType alcType = null;
    private String alcName = null;
    private String brandName = null;
    private int alcContent = 0;
    private AlcoholInfo.Wine wineType = null; //null if type is not wine
    private String formula;
    private String serialNum;
    private String extraInfo;

    //Stores the alcohol info from the form
    public AlcoholInfo appAlcoholInfo = null;

    private Main main;

    private SubmittedApplication application;

    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application = application;
        repIDNoTextField.setText(String.valueOf(application.getApplication().getManufacturer().getRepresentativeID()));
        permitNoTextField.setText(String.valueOf(application.getApplication().getManufacturer().getPermitNum()));
        addressField.setText(String.valueOf(application.getApplication().getManufacturer().getPhysicalAddress()));
        phoneNumberField.setText(String.valueOf(application.getApplication().getManufacturer().getPhoneNumber().getPhoneNumber()));
        emailAddressField.setText(String.valueOf(application.getApplication().getManufacturer().getEmailAddress().getEmailAddress()));
        alcoholName.setText(String.valueOf(application.getApplication().getAlcohol().getName()));
        brandNameField.setText(String.valueOf(application.getApplication().getAlcohol().getBrandName()));
        alcoholContentField.setText(String.valueOf(application.getApplication().getAlcohol().getAlcoholContent()));
        formulaText.setText(String.valueOf(application.getApplication().getAlcohol().getFormula()));

        pTypeSelect.setValue("Select a product type");
        pTypeSelect.setItems(typeList);
        pSourceSelect.setValue("Select a product source");
        pSourceSelect.setItems(sourceList);

        pTypeSelect.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldVal, String newVal) {
                if(pTypeSelect.getValue().equals("Wine")){
                    showTheWine();
                } else hideTheWine();
            }
        });
    }


    public void init(Main main){
//        init(main, null);
        this.main = main;

        pTypeSelect.setValue("Select a product type");
        pTypeSelect.setItems(typeList);
        pSourceSelect.setValue("Select a product source");
        pSourceSelect.setItems(sourceList);

        pTypeSelect.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldVal, String newVal) {
                if(pTypeSelect.getValue().equals("Wine")){
                    showTheWine();
                } else hideTheWine();
            }
        });
    }

    public void submitApp() {
        LogManager.getInstance().logAction("newApplicationController", "Logged Click from first page of the new Application");

        Boolean formFilled = false;

        //filling out application type
        boolean labelApproval;
        String stateOnly;
        int bottleCapacity;
        //14a
        labelApproval = certOfApproval.isSelected();
        //14b
        if (certOfExemption.isSelected()) {
            stateOnly = exemptionText.getText();
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
            } else {
                alcContentErrorField.setText("");
            }
            if (serialText.getText().isEmpty()) {
                serialErrorField.setText("Please input a serial number");
            } else {
                serialErrorField.setText("");
            }

            if (pTypeSelect.getValue().equals("Wine")) {
                //enable wine fields!!!!!

                int vintageYr = 0;
                double pH = 0.0;
                String varietal = "";
                String appellation = "";
                if (!wineVintageYearField.getText().isEmpty()) {
                    vintageYr = Integer.parseInt(wineVintageYearField.getText()); //CHECK IF INPUT INTEGER!
                }
                if (!pHLevelField.getText().isEmpty()) {
                    pH = Double.parseDouble(pHLevelField.getText()); //CHECK IF INPUT INTEGER!
                }
                if (!varietalText.getText().isEmpty()) varietal = varietalText.getText();
                if (!appellationText.getText().isEmpty()) appellationText.getText();
                wineType = new AlcoholInfo.Wine(pH, vintageYr, varietal, appellation);
            }

//            if (signatureField.getText().isEmpty()) {
//                signatureErrorField.setText("Please fill in the signature field.");
//            } else if (datePicker == null){ //this doesn't work for now
//                signatureErrorField.setText("");
//                dateErrorField.setText("Please select the date.");
//            } else {
//                signatureErrorField.setText("");
//                dateErrorField.setText("");
//            }

            //check if required fields are filled in
            if ((!pTypeSelect.getValue().equals("Select a product type")) &&
                    (!pSourceSelect.getValue().equals("Select a product source")) &&
                    !brandNameField.getText().isEmpty() && !alcoholContentField.getText().isEmpty() &&
                    (datePicker != null) && !signatureField.getText().isEmpty() && !serialText.getText().isEmpty()
                    ) {
                formFilled = true;
            }

            if (formFilled) {
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

                //sets the alcohol info
                appAlcoholInfo = new AlcoholInfo(alcContent, alcName, brandName, pSource, alcType, wineType, "123", formula);//fix serial number

                //creates a new ManufacturerInfo
                this.appManInfo = new ManufacturerInfo("Name Person", physicalAddress, "company", repIDNo,
                        permitNo, applicantPhone, applicantEmail);

                //creates and sets the date value
                Date newDate = DateHelper.getDate(datePicker.getValue().getDayOfMonth(), datePicker.getValue().getMonthValue() - 1, datePicker.getValue().getYear());

                // Creates a new application info and sets data
                if (extraInfoText.getText().isEmpty()) {
                    extraInfo = " ";
                } else extraInfo = extraInfoText.getText();

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
        main.loadHomepage();
    }
    public void saveApp() {

    }
    public void logout() {
        Authenticator.getInstance().logout();
        main.loadHomepage();
    }

    public void exemptionChecked(){
        exemptionText.setDisable(!certOfExemption.isSelected());
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
