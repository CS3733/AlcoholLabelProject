package com.emeraldElves.alcohollabelproject;

/**
 * Created by Harry and Joe on 4/3/2017.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WorkflowController implements Initializable {
    @FXML
    public Label ttbAgentNameLabel;
    @FXML
    public ComboBox pullApplicationsDropDown;
    @FXML
    public Button logoutBtn;
    @FXML
    public Button viewCompletedApplicationBtn;
    @FXML
    public ComboBox statusDropdown;
    @FXML
    public Label alcoholNameLabel;
    @FXML
    public Label applicatorNameLabel;
    @FXML
    public Label dateLabel;
    @FXML
    public Button submitBtn;


    AlcoholDatabase db = new AlcoholDatabase(Main.database);
    // TODO: array sizes are currently arbitrary; possibly change to ArrayList<> in the future
    public String[] appBrandNames = new String[50];
    public String[] appNames = new String[50];
    public String[] dates = new String[50];
    public String pattern = "MM/dd/yyyy";
    public int i = 0;

    public void queryDatabase(String ttbUsername) {
        List<SubmittedApplication> list = db.getAssignedApplications(ttbUsername);
        for (ListIterator<SubmittedApplication> itr = list.listIterator(); itr.hasNext(); ) {
            SubmittedApplication current = itr.next();
            appBrandNames[i] = current.getApplication().getAlcohol().getBrandName();
            appNames[i] = current.getApplication().getAlcohol().getName();

            // format date into string
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            Date currentDate = current.getApplication().getSubmissionDate();
            dates[i] = dateFormat.format(currentDate);
            i++;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        queryDatabase("Placeholder");

    }

}
