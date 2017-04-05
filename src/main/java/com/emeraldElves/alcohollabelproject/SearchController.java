package com.emeraldElves.alcohollabelproject;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Essam on 4/2/2017.
 */
public class SearchController {

    private Main main;
    private String searchTerm;
    private UserType userType;
    private String username;

    @FXML
    private TextField searchField;
    @FXML
    private TableView<SubmittedApplication> resultsTable;
    @FXML
    private TableColumn<SubmittedApplication, String> dateCol;
    @FXML
    private TableColumn<SubmittedApplication, String> manufacturerCol;
    @FXML
    private TableColumn<SubmittedApplication, String> brandCol;
    @FXML
    private Button saveBtn;
    @FXML
    private MenuItem contextSaveBtn;
    @FXML
    private Label descriptionLabel;
    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();

    public SearchController() {


    }

    public void init(Main main, UserType userType, String username, String searchTerm) {
        this.main = main;
        this.searchTerm = searchTerm;
        this.username = username;
        this.userType = userType;
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
                Date date = p.getValue().getApplication().getSubmissionDate();
                date.setYear(date.getYear() - 1900);
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(dateFormat.format(date)));
            }
        });
        manufacturerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getManufacturer().getCompany()));
            }
        });
        brandCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getBrandName()));
            }
        });
        saveBtn.setDisable(data.size() == 0);
        descriptionLabel.setVisible(false);
        contextSaveBtn.setDisable(data.size() == 0);
        resultsTable.setItems(data);
        resultsTable.setRowFactory(tv -> {
            TableRow<SubmittedApplication> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    SubmittedApplication rowData = row.getItem();
                    main.loadDetailedSearchPage(rowData, searchTerm);
                }
            });
            return row;
        });

        search(searchTerm);
    }

    public void search(ActionEvent e) {
        search(searchField.getText());
    }

    public void search(String searchTerm) {
        //Remove previous results
        data.remove(0, data.size());

        //Find & add matching applications
        List<SubmittedApplication> resultsList = (new AlcoholDatabase(Main.database)).searchByBrandName(searchTerm);
        data.addAll(resultsList); //change to resultsList
        descriptionLabel.setText("Showing " + data.size() + " results for \"" + searchTerm + "\"");
        descriptionLabel.setVisible(true);
        saveBtn.setDisable(data.size() == 0);
        contextSaveBtn.setDisable(data.size() == 0);
    }

    public void goHome() {
        main.loadHomepage(userType, username);
    }

    public void saveCSV(ActionEvent e) {


        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Save Results");
        fileChooser.setInitialFileName("results.csv");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv"));
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv"));


        File file = fileChooser.showSaveDialog(Main.stage);
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);

                /*
                 * This is a weird way of getting column names, but this way columns names are
                 * always printed out correctly and in the order the user arranged them.
                 */

                //write out column names
                for (int colId = 0; colId < resultsTable.getColumns().size(); colId++) {
                    TableColumn<SubmittedApplication, ?> col = resultsTable.getColumns().get(colId);

                    if (colId > 0) fileWriter.write(",");

                    fileWriter.write(StringEscapeUtils.escapeCsv(col.getText()));
                }
                fileWriter.write("\r\n");

                for (int rowId = 0; rowId < data.size(); rowId++) {
                    //for each row, write out column values
                    for (int colId = 0; colId < resultsTable.getColumns().size(); colId++) {
                        TableColumn<SubmittedApplication, ?> col = resultsTable.getColumns().get(colId);
                        if (colId > 0) fileWriter.write(",");
                        fileWriter.write(StringEscapeUtils.escapeCsv((String) col.getCellObservableValue(rowId).getValue()));
                    }
                    fileWriter.write("\r\n");
                }

                fileWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}