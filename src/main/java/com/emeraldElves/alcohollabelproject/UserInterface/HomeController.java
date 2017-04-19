package com.emeraldElves.alcohollabelproject.UserInterface;


import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.COLASearch;
import com.emeraldElves.alcohollabelproject.Data.Controller;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.util.*;

/**
 * Created by Harry and Joe on 4/2/2017.
 */
public class HomeController implements Controller {
    public ArrayList<Label> mostRecentLabels;
    public ArrayList<SubmittedApplication> mostRecentSubmissions;
    public List<SubmittedApplication> submitted;


    // Alcohol 1
    @FXML
    private AnchorPane alc1;
    @FXML
    private Label brand1;
    @FXML
    private Label fanciful1;
    @FXML
    private Label content1;
    @FXML
    private Label date1;
    @FXML
    private ImageView image1;

    // Alcohol 2
    @FXML
    private AnchorPane alc2;
    @FXML
    private Label brand2;
    @FXML
    private Label fanciful2;
    @FXML
    private Label content2;
    @FXML
    private Label date2;
    @FXML
    private ImageView image2;

    // Alcohol 3
    @FXML
    private AnchorPane alc3;
    @FXML
    private Label brand3;
    @FXML
    private Label fanciful3;
    @FXML
    private Label content3;
    @FXML
    private Label date3;
    @FXML
    private ImageView image3;

    // Alcohol 4
    @FXML
    private AnchorPane alc4;
    @FXML
    private Label brand4;
    @FXML
    private Label fanciful4;
    @FXML
    private Label content4;
    @FXML
    private Label date4;
    @FXML
    private ImageView image4;

    private Main main;

    @FXML
    private TextField searchbox;


    // delete this
    @FXML
    private Button goToProfile;

    private COLASearch search;


    public HomeController() {
        mostRecentLabels = new ArrayList<>();
        mostRecentSubmissions = new ArrayList<>();
        search = new COLASearch();
        submitted = search.searchRecentApplications(4);
    }


    // TODO: put FXML in correct folder

    /**
     * Loads homepage
     */
    public void utilityButton() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                main.loadWorkflowPage();
                break;
            case APPLICANT:
                main.loadApplicantWorkflowPage();
                break;
        }
    }

    public void loadLog() {
        switch (Authenticator.getInstance().getUserType()) {
            case TTBAGENT:
                Authenticator.getInstance().logout();
                main.loadHomepage();
                break;
            case APPLICANT:
                Authenticator.getInstance().logout();
                main.loadHomepage();
                break;
            case SUPERAGENT:
                Authenticator.getInstance().logout();
                main.loadHomepage();
                break;
            case BASIC:
                main.loadLoginPage();
                break;
        }
    }

    public void loadProfile() {
        main.loadProfilePage();
    }

    public void createNewUser(){
        main.loadNewUserPage();
    }


    public void searchDatabase() {
        main.loadSearchPage(searchbox.getText());
    }

    public void feelingThirsty() {
        List<SubmittedApplication> applications = search.searchApprovedApplications();
        Random random = new Random();
        SubmittedApplication application;
        if (applications.isEmpty()) {
            application = null;
        } else {
            int pos = random.nextInt(applications.size());
            application = applications.get(pos);
        }
        if (application != null)
            main.loadDetailedSearchPage(application, application.getApplication().getAlcohol().getBrandName());
    }

    public void init(Main main) {
        this.main = main;
        for (int i = 0; i < submitted.size(); i++) {
            SubmittedApplication recentApplication = submitted.get(i);
            switch (i) {
                case 0:
                    alc1.setOnMouseClicked(event -> main.loadDetailedSearchPage(recentApplication, recentApplication.getApplication().getAlcohol().getBrandName()));
                    brand1.setText(recentApplication.getApplication().getAlcohol().getBrandName().toUpperCase());
                    fanciful1.setText(recentApplication.getApplication().getAlcohol().getName());
                    content1.setText(recentApplication.getApplication().getAlcohol().getAlcoholContent() + "%");
                    date1.setText(DateHelper.dateToString(recentApplication.getApplication().getSubmissionDate()));
                    image1.setImage(recentApplication.getImage().display());
                    ImageUtils.centerImage(image1);
                    break;
                case 1:
                    alc2.setOnMouseClicked(event -> main.loadDetailedSearchPage(recentApplication, recentApplication.getApplication().getAlcohol().getBrandName()));
                    brand2.setText(recentApplication.getApplication().getAlcohol().getBrandName().toUpperCase());
                    fanciful2.setText(recentApplication.getApplication().getAlcohol().getName());
                    content2.setText(recentApplication.getApplication().getAlcohol().getAlcoholContent() + "%");
                    date2.setText(DateHelper.dateToString(recentApplication.getApplication().getSubmissionDate()));
                    image2.setImage(recentApplication.getImage().display());
                    ImageUtils.centerImage(image2);
                    break;
                case 2:
                    alc3.setOnMouseClicked(event -> main.loadDetailedSearchPage(recentApplication, recentApplication.getApplication().getAlcohol().getBrandName()));
                    brand3.setText(recentApplication.getApplication().getAlcohol().getBrandName().toUpperCase());
                    fanciful3.setText(recentApplication.getApplication().getAlcohol().getName());
                    content3.setText(recentApplication.getApplication().getAlcohol().getAlcoholContent() + "%");
                    date3.setText(DateHelper.dateToString(recentApplication.getApplication().getSubmissionDate()));
                    image3.setImage(recentApplication.getImage().display());
                    ImageUtils.centerImage(image3);
                    break;
                case 3:
                    alc4.setOnMouseClicked(event -> main.loadDetailedSearchPage(recentApplication, recentApplication.getApplication().getAlcohol().getBrandName()));
                    brand4.setText(recentApplication.getApplication().getAlcohol().getBrandName().toUpperCase());
                    fanciful4.setText(recentApplication.getApplication().getAlcohol().getName());
                    content4.setText(recentApplication.getApplication().getAlcohol().getAlcoholContent() + "%");
                    date4.setText(DateHelper.dateToString(recentApplication.getApplication().getSubmissionDate()));
                    image4.setImage(recentApplication.getImage().display());
                    ImageUtils.centerImage(image4);
                    break;
            }
            List<SubmittedApplication> resultsList = search.searchApprovedApplications();

            AutoCompletionBinding<String> autoCompletionBinding;
            Set<String> possibleSuggestions = new HashSet<>();
            possibleSuggestions.clear();
            resultsList.sort((lhs, rhs) -> lhs.getApplication().getAlcohol().getBrandName().compareToIgnoreCase(rhs.getApplication().getAlcohol().getBrandName()));

            for (SubmittedApplication application : resultsList) {
                possibleSuggestions.add(application.getApplication().getAlcohol().getBrandName());
                possibleSuggestions.add(application.getApplication().getAlcohol().getName());
            }

            autoCompletionBinding = TextFields.bindAutoCompletion(searchbox, possibleSuggestions);
            autoCompletionBinding.setPrefWidth(472);

            searchbox.setOnKeyPressed(ke -> autoCompletionBinding.setUserInput(searchbox.getText().trim()));
        }


    }
}
