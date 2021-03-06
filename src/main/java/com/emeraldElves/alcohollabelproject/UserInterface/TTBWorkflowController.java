package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kylec on 4/18/2017.
 */
public class TTBWorkflowController implements IController {

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

    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();
    private TTBAgentInterface agentInterface;

    @FXML
    private CheckBox beer;

    @FXML
    private CheckBox wine;

    @FXML
    private CheckBox spirits;


    private Main main;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"));
    }

    public void init(Main main) {
        this.main = main;
        agentInterface = new TTBAgentInterface(Authenticator.getInstance().getUsername());
        dateCol.setCellValueFactory(p -> {
            Date date = p.getValue().getApplication().getSubmissionDate();
            return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(DateHelper.dateToString(date)));
        });
        manufacturerCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getName())));
        brandCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getBrandName())));
        typeCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getAlcoholType().name())));
        appIDCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getApplicationID()))));
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

        //Find & add matching applications
        List<SubmittedApplication> resultsList = agentInterface.getAssignedApplications();
        data.addAll(resultsList);
    }

    public void fetchApplications(){
        List<SubmittedApplication> unassigned = Storage.getInstance().getUnassignedApplications();

        unassigned = unassigned.stream().filter((a) -> {
            AlcoholType type = a.getApplication().getAlcohol().getAlcoholType();
            return type.equals(AlcoholType.BEER) && beer.isSelected() ||
                    type.equals(AlcoholType.WINE) && wine.isSelected() ||
                    type.equals(AlcoholType.DISTILLEDSPIRITS) && spirits.isSelected();
        }).collect(Collectors.toList());

        int numAssigned = agentInterface.getAssignedApplications().size();
        for (int i = numAssigned; i < 10 && unassigned.size() > 0; i++) {
            agentInterface.addApplication(unassigned.get(0));
            unassigned.remove(0);
        }
        List<SubmittedApplication> resultsList = agentInterface.getAssignedApplications();
        data.clear();
        data.addAll(resultsList);
    }

}
