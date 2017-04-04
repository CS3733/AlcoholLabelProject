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
    Label fanciful1;
    @FXML
    Label fanciful2;
    @FXML
    Label fanciful3;
    @FXML
    Label fanciful4;
    @FXML
    Label fanciful5;
    @FXML
    Label brand1;
    @FXML
    Label brand2;
    @FXML
    Label brand3;
    @FXML
    Label brand4;
    @FXML
    Label brand5;
    @FXML
    Label date1;
    @FXML
    Label date2;
    @FXML
    Label date3;
    @FXML
    Label date4;
    @FXML
    Label date5;



    AlcoholDatabase db = new AlcoholDatabase(Main.database);
    // TODO: array sizes are currently arbitrary; possibly change to ArrayList<> in the future
    public String[] appBrandNames = new String[50];
    public String[] appNames = new String[50];
    public String[] dates = new String[50];
    public String pattern = "MM/dd/yyyy";
    public int i = 0;

    // for init
    Main main;
    String Username;

    public void init(Main main, String Username ) {
        this.main = main;
        this.Username = Username;

        // load application values into labels on FXML
        fanciful1.setText(appNames[0]);
        fanciful2.setText(appNames[1]);
        fanciful3.setText(appNames[2]);
        fanciful4.setText(appNames[3]);
        fanciful5.setText(appNames[4]);
        brand1.setText(appBrandNames[0]);
        brand2.setText(appBrandNames[1]);
        brand3.setText(appBrandNames[2]);
        brand4.setText(appBrandNames[3]);
        brand5.setText(appBrandNames[4]);
        date1.setText(dates[0]);
        date2.setText(dates[1]);
        date3.setText(dates[2]);
        date4.setText(dates[3]);
        date5.setText(dates[4]);
    }

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
