package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.COLASearch;
<<<<<<< HEAD
import com.emeraldElves.alcohollabelproject.Data.AlcoholType;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.*;

import javafx.application.Platform;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
=======
import com.emeraldElves.alcohollabelproject.Data.AlcoholDatabase;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.UserType;
import javafx.application.Platform;
>>>>>>> refs/remotes/origin/master
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
<<<<<<< HEAD
=======
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
>>>>>>> refs/remotes/origin/master
import javafx.util.Callback;
import org.apache.commons.lang3.StringEscapeUtils;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextInputDialog;


<<<<<<< HEAD
=======
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
>>>>>>> refs/remotes/origin/master
import java.util.*;

/**
 * Created by Essam on 4/2/2017.
 */
public class SearchController {

    private Main main;
    private String searchTerm;
    private AutoCompletionBinding<String> autoCompletionBinding;
    private Set<String> possibleSuggestions = new HashSet<>();
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
    private TableColumn<SubmittedApplication, String> typeCol;
    @FXML
    private TableColumn<SubmittedApplication, String> contentCol;
    @FXML
    private Button saveBtn;
    @FXML
    private MenuItem contextSaveBtn;
    @FXML
    private Label descriptionLabel;
    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();
    private COLASearch search;

    public SearchController() {
        this.search = new COLASearch();
    }

    public void init(Main main, String searchTerm) {
        this.main = main;
        this.searchTerm = searchTerm;
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
                Date date = p.getValue().getApplication().getSubmissionDate();
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(dateFormat.format(date)));
            }
        });
        manufacturerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getName()));
            }
        });
        brandCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getBrandName()));
            }
        });
        typeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getAlcoholType().name()));
            }
        });
        contentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getApplication().getAlcohol().getAlcoholContent())));
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

        List<SubmittedApplication> resultsList = search.searchApprovedApplications();
        possibleSuggestions.clear();
        Collections.sort(resultsList, new Comparator<SubmittedApplication>() {
            @Override
            public int compare(SubmittedApplication lhs, SubmittedApplication rhs) {
                return lhs.getApplication().getAlcohol().getBrandName().compareToIgnoreCase(rhs.getApplication().getAlcohol().getBrandName());
            }
        });

        for(SubmittedApplication application: resultsList){
            possibleSuggestions.add(application.getApplication().getAlcohol().getBrandName());
            possibleSuggestions.add(application.getApplication().getAlcohol().getName());
        }

        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, possibleSuggestions);


        /*searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {

                autoCompletionBinding.setUserInput(searchField.getText().trim());
                //search(searchField.getText().trim());


            }
        });*/

        searchField.setText(searchTerm);
        search(searchTerm);
    }
    public void search(ActionEvent e) {
        Platform.runLater(() -> {
            search(searchField.getText());
        });
    }
<<<<<<< HEAD

    public void onKeyType(KeyEvent e) {
        //delay is required for .getText() to get the updated field
        Platform.runLater(() -> {
            search(searchField.getText());


=======
    public void onKeyType(KeyEvent e){
        //delay is required for .getText() to get the updated field
        Platform.runLater(() -> {
            search(searchField.getText());
>>>>>>> refs/remotes/origin/master
        });
    }

    public void search(String searchTerm) {
        //Remove previous results
        data.remove(0, data.size());

        //Find & add matching applications
        List<SubmittedApplication> resultsList = search.searchByName(searchTerm.trim());
        ;
        data.addAll(resultsList); //change to resultsList
        descriptionLabel.setText("Showing " + data.size() + " results for \"" + searchTerm + "\"");
        descriptionLabel.setVisible(true);
        saveBtn.setDisable(data.size() == 0);
        contextSaveBtn.setDisable(data.size() == 0);
    }
<<<<<<< HEAD

    private void refreshSuggestions() {
        List<SubmittedApplication> resultsList = search.searchApprovedApplications();
        filterList(resultsList);
        possibleSuggestions.clear();
        /*
        Collections.sort(resultsList, new Comparator<SubmittedApplication>() {
            @Override
            public int compare(SubmittedApplication lhs, SubmittedApplication rhs) {
                return lhs.getApplication().getAlcohol().getBrandName().compareToIgnoreCase(rhs.getApplication().getAlcohol().getBrandName());
            }
        });
        */
        for (SubmittedApplication application : resultsList) {
            possibleSuggestions.add(application.getApplication().getAlcohol().getBrandName());
            possibleSuggestions.add(application.getApplication().getAlcohol().getName());
        }

        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, possibleSuggestions);
        autoCompletionBinding.setPrefWidth(583);
    }

    public void filter(ActionEvent e) {
        Platform.runLater(() -> {
            refreshSuggestions();
            search(e);


        });

    }

    private void filterList(List<SubmittedApplication> appList) {
        appList.removeIf(p -> (filterBeers.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER));
        appList.removeIf(p -> (filterWine.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE));
        appList.removeIf(p -> (filterSpirits.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.DISTILLEDSPIRITS));
    }
=======

    public void goHome() {
        main.loadHomepage();
    }

    public void saveCSV(ActionEvent e) {
>>>>>>> refs/remotes/origin/master

    public void saveTSV(ActionEvent e) {

        ApplicationExporter exporter = new ApplicationExporter(new TSVExporter());
        exporter.export(data);
    }

    public void saveUserChar(ActionEvent e) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Data Exporter");
        dialog.setHeaderText("Enter a character.");
        dialog.setContentText("Please enter a character to separate data:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            ApplicationExporter exporter = new ApplicationExporter(new UserCharExporter(name.charAt(0), "txt"));
            exporter.export(data);
        });


    }

    public void saveCSV(ActionEvent e) {

        ApplicationExporter exporter = new ApplicationExporter(new CSVExporter());
        exporter.export(data);
    }
}