package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.*;

import com.emeraldElves.alcohollabelproject.Data.*;
import javafx.application.Platform;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.StringEscapeUtils;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.JDBCType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    @FXML
    private CheckMenuItem filterBeers;
    @FXML
    private CheckMenuItem filterWine;
    @FXML
    private CheckMenuItem filterSpirits;

    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();
    private COLASearch search;

    private SearchSubject searchTermSubject;

    public SearchController() {
        this.search = new COLASearch();
        searchTermSubject = new SearchSubject();
        new SearchObserver(searchTermSubject, data);
    }

    public void init(Main main, String searchTerm) {
        this.main = main;
        this.searchTerm = searchTerm;
        dateCol.setCellValueFactory(p -> {
            DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
            Date date = p.getValue().getApplication().getSubmissionDate();
            return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(dateFormat.format(date)));
        });
        manufacturerCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getName())));
        brandCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getBrandName())));
        typeCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getApplication().getAlcohol().getAlcoholType().name())));
        contentCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getApplication().getAlcohol().getAlcoholContent()))));
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

        /*
        List<SubmittedApplication> resultsList = search.searchApprovedApplications();
        possibleSuggestions.clear();
        resultsList.sort((lhs, rhs) -> lhs.getApplication().getAlcohol().getBrandName().compareToIgnoreCase(rhs.getApplication().getAlcohol().getBrandName()));

                autoCompletionBinding.setUserInput(searchField.getText().trim());
                //search(searchField.getText().trim());


            }
        });*/
        refreshSuggestions();
        searchField.setText(searchTerm);
        search(searchTerm);
    }

    public void search(ActionEvent e) {
//        notifyObservers();
        Platform.runLater(() -> search(searchField.getText()));
    }

    public void onKeyType(KeyEvent e){
        //delay is required for .getText() to get the updated field
        Platform.runLater(() -> {
            search(searchField.getText());



        });
    }
    public void search(String searchTerm) {
        //Remove previous results
        data.remove(0, data.size());

        //Find & add matching applications
        List<SubmittedApplication> resultsList = search.searchByName(searchTerm.trim());
        filterList(resultsList);
        data.addAll(resultsList); //change to resultsList
        descriptionLabel.setText("Showing " + data.size() + " results for \"" + searchTerm + "\"");
        descriptionLabel.setVisible(true);
        saveBtn.setDisable(data.size() == 0);
        contextSaveBtn.setDisable(data.size() == 0);
    }
    private void refreshSuggestions(){
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
        for(SubmittedApplication application: resultsList){
            possibleSuggestions.add(application.getApplication().getAlcohol().getBrandName());
            possibleSuggestions.add(application.getApplication().getAlcohol().getName());
        }

        if (autoCompletionBinding != null){
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(searchField, possibleSuggestions);
    }
    public void filter(ActionEvent e){
        Platform.runLater(() -> {
            refreshSuggestions();
            search(e);



        });

    }
    public void goHome() {
        Schema LoginSchema = new Schema("TTBAgentLogin",
                new Attribute("username", JDBCType.VARCHAR),
                new Attribute("password", JDBCType.VARCHAR)
        );
        Model<Entity> LoginModel = new Model(LoginSchema);
        Entities<Entity> logins = LoginModel.find(new HashMap<String, String>());
        System.out.println("WARNING REMOVE THIS");
        System.out.println(logins.size());
        main.loadHomepage();
    }

    private void filterList(List<SubmittedApplication> appList){
        appList.removeIf(p -> (filterBeers.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER));
        appList.removeIf(p -> (filterWine.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE));
        appList.removeIf(p -> (filterSpirits.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.DISTILLEDSPIRITS));
    }
    public void saveCSV(ActionEvent e) {

        ApplicationExporter exporter = new ApplicationExporter(new CSVExporter());
        exporter.export(data);
    }
}