package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.COLASearch;
import com.emeraldElves.alcohollabelproject.Data.AlcoholType;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.*;

import com.sun.javafx.scene.control.skin.TableViewSkin;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.application.Platform;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import org.apache.commons.lang3.StringEscapeUtils;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextInputDialog;


import java.util.*;

/**
 * Created by Essam on 4/2/2017.
 */
public class SearchController implements IController{

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

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getString("searchTerm"));
    }

    public void init(Main main, String searchTerm) {
        this.main = main;
        this.searchTerm = searchTerm;
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubmittedApplication, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<SubmittedApplication, String> p) {
                Date date = p.getValue().getApplication().getSubmissionDate();
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(DateHelper.dateToString(date)));
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
                    main.loadFXML("/fxml/DetailedSearchPage.fxml",rowData, searchTerm);
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


            //}
        //});
        */
        refreshSuggestions();
        searchField.setText(searchTerm);
        search(searchTerm);
    }

    public void search(ActionEvent e) {
//        notifyObservers();
        Platform.runLater(() -> search(searchField.getText()));
    }

    public void onKeyType(KeyEvent e) {
        //delay is required for .getText() to get the updated field
        Platform.runLater(() -> {
            search(searchField.getText());


        });
    }

    public void advancedSearch(){
        main.loadFXML("/fxml/AdvancedSearchPage.fxml");
    }

    private int getNumberOfVisibleRows()
    {
        return 100;
    }



    public void search(String searchTerm) {
        //Remove previous results
        data.remove(0, data.size());

        //Find & add matching applications
        List<SubmittedApplication> resultsList = search.searchByName(searchTerm.trim(), getNumberOfVisibleRows(), filterBeers.isSelected(), filterWine.isSelected(), filterSpirits.isSelected());
//        filterList(resultsList);
        data.addAll(resultsList); //change to resultsList
        descriptionLabel.setText("Showing top " + data.size() + " results for \"" + searchTerm + "\"");
        descriptionLabel.setVisible(true);
        saveBtn.setDisable(data.size() == 0);
        contextSaveBtn.setDisable(data.size() == 0);
    }

    private void refreshSuggestions() {
        List<SubmittedApplication> resultsList = search.searchApprovedApplications(filterBeers.isSelected(), filterWine.isSelected(), filterSpirits.isSelected());
//        filterList(resultsList);
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
//        appList.removeIf(p -> (filterBeers.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.BEER));
//        appList.removeIf(p -> (filterWine.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.WINE));
//        appList.removeIf(p -> (filterSpirits.isSelected() && p.getApplication().getAlcohol().getAlcoholType() == AlcoholType.DISTILLEDSPIRITS));
    }

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
