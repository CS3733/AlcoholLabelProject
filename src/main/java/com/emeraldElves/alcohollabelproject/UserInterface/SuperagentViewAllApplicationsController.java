package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kylec on 4/18/2017.
 */
public class SuperagentViewAllApplicationsController implements IController{

    @FXML
    private TableView<SubmittedApplication> resultsTable;
    @FXML
    private TableColumn<SubmittedApplication, String> dateCol;
    @FXML
    private TableColumn<SubmittedApplication, String> manufacturerCol;
    @FXML
    private TableColumn<SubmittedApplication, String> brandCol;
    @FXML
    private TableColumn<SubmittedApplication, String> typeCol;
    @FXML
    private TableColumn<SubmittedApplication, String> appIDCol;
    @FXML
    private TableColumn<SubmittedApplication, String> agentCol;
    @FXML
    ComboBox appTypeBox;

    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();
    private TTBAgentInterface agentInterface;

    private Main main;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"));
    }

    public void init(Main main) {
        this.main = main;
        //list of all ttb agent usernames
        List<String> names = Storage.getInstance().getAllTTBUsernames();
        ApplicationStatus applicationStatus;
        if(appTypeBox.getValue().toString().equals("Approved")){
            applicationStatus = ApplicationStatus.APPROVED;
        }
        else if(appTypeBox.getValue().toString().equals("Pending Review")){
            applicationStatus = ApplicationStatus.PENDINGREVIEW;
        }
        else{
            //Rejected
            applicationStatus = ApplicationStatus.REJECTED;
        }


        //agentInterface = new TTBAgentInterface(Authenticator.getInstance().getUsername());
        dateCol.setCellValueFactory(p -> {
            Date date = p.getValue().getApplication().getSubmissionDate();
            return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(DateHelper.dateToString(date)));
        });
        manufacturerCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getName())));
        brandCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getBrandName())));
        typeCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getAlcoholType().name())));
        appIDCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getApplicationID()))));
        agentCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getTtbAgentName())));
        resultsTable.setItems(data);
        resultsTable.setRowFactory(tv -> {
            TableRow<SubmittedApplication> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    SubmittedApplication rowData = row.getItem();
                    main.loadFXML("/fxml/ApprovalPage.fxml",rowData);
                }
            });
            return row;
        });
        data.clear();
        List<SubmittedApplication> actualListToAdd = new ArrayList<>();
        //Find & add matching applications
        for(String strang : names){
            agentInterface = new TTBAgentInterface(strang);
            List<SubmittedApplication> resultsList = agentInterface.getAssignedApplications();
            for(SubmittedApplication temp : resultsList){
                temp.setTtbAgentName(strang);
                if(temp.getStatus() == applicationStatus){
                    actualListToAdd.add(temp);
                }
            }
            //data.addAll(actualListToAdd);
        }
        data.addAll(actualListToAdd);



    }

}
