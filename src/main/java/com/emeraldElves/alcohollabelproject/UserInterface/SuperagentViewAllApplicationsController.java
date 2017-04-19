package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.TTBAgentInterface;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Kylec on 4/18/2017.
 */
public class SuperagentViewAllApplicationsController {

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
    private TableColumn<String, String> agentCol;

    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();
    private TTBAgentInterface agentInterface;

    private Main main;

    public void init(Main main) {
        this.main = main;
        //list of all ttb agent usernames
        List<String> names = Storage.getInstance().getAllTTBUsernames();

        //Find & add matching applications
        for(String strang : names){
            agentInterface = new TTBAgentInterface(strang);
            List<SubmittedApplication> resultsList = agentInterface.getAssignedApplications();
            data.addAll(resultsList);
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
        agentCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue()))));
        resultsTable.setItems(data);
        resultsTable.setRowFactory(tv -> {
            TableRow<SubmittedApplication> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    SubmittedApplication rowData = row.getItem();
                    main.loadApprovalProcessController(rowData);
                }
            });
            return row;
        });
        data.clear();



    }

}