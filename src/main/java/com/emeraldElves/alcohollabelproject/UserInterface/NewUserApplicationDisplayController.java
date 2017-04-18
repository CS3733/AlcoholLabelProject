package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.COLASearch;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.PotentialUser;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.apache.commons.lang3.StringEscapeUtils;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by keionbis on 4/18/17.
 */
public class NewUserApplicationDisplayController {
    private Main main;


    private AutoCompletionBinding<String> autoCompletionBinding;
    private Set<String> possibleSuggestions = new HashSet<>();
    @FXML
    private TableView<PotentialUser> resultsTable;
    @FXML
    private TableColumn<PotentialUser, String> dateCol;
    @FXML
    private TableColumn<PotentialUser, String> nameCol;
    @FXML
    private TableColumn<PotentialUser, String> userNameCol;
    @FXML
    private TableColumn<PotentialUser, String> userTypeCol;
    @FXML
    private TableColumn<PotentialUser, String> idNumCol;


    private ObservableList<PotentialUser> data = FXCollections.observableArrayList();
    private COLASearch search;



    public void init(Main main) {
        this.main = main;

        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PotentialUser, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PotentialUser, String> p) {
                Date date = p.getValue().getDate();
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(DateHelper.dateToString(date)));
            }
        });
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PotentialUser, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PotentialUser, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getName()));
            }
        });
        userNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PotentialUser, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PotentialUser, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getEmail().toString()));
            }
        });
        userTypeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PotentialUser, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PotentialUser, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getUserType().toString()));
            }
        });
        idNumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PotentialUser, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PotentialUser, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getPermitNum())));
            }
        });
        nameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getName())));
        userNameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getEmail().toString())));
        userTypeCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getUserType().toString())));
        idNumCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getPermitNum()))));
        resultsTable.setItems(data);
        resultsTable.setRowFactory(tv -> {
            TableRow<PotentialUser> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    PotentialUser rowData = row.getItem();
                    main.loadSuperUserWorkflowController(rowData);
                }
            });
            return row;
        });
    }



    public void goHome() {
        main.loadHomepage();
    }

}
