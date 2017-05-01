package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.COLASearch;
import com.emeraldElves.alcohollabelproject.Data.DateHelper;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Kyle on 5/1/2017.
 */
public class AdvancedSearch implements IController {

    Main main;
    COLASearch search;

    @FXML
    TextField brand;

    @FXML
    TextField fanciful;

    @FXML
    TextField contentStart;

    @FXML
    TextField contentEnd;

    @FXML
    CheckBox beer;

    @FXML
    CheckBox wine;

    @FXML
    CheckBox spirits;

    @FXML
    TextField email;

    @FXML
    TextField address;

    @FXML
    DatePicker dateStart;

    @FXML
    DatePicker dateEnd;

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

    private ObservableList<SubmittedApplication> data = FXCollections.observableArrayList();


    @Override
    public void init(Bundle data) {
        main = data.getMain("main");
        search = new COLASearch();
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

    }

    public void search() {
        String brandName;
        String fancifulName;
        String eAddr, addr;
        double contentMin, contentMax;
        Date startDate,endDate;
        if(!brand.getText().isEmpty()){
            brandName = brand.getText();
        }
        else{
            brandName = "";
        }
        if(!fanciful.getText().isEmpty()) {
            fancifulName = fanciful.getText();
        }
        else{
            fancifulName = "";
        }

        boolean wantBeer = beer.isSelected();
        boolean wantWine = wine.isSelected();
        boolean wantSpirits = spirits.isSelected();
        if(!email.getText().isEmpty()) {
            eAddr = email.getText();
        }
        else{
            eAddr = "";
        }
        if(!address.getText().isEmpty()) {
            addr = address.getText();
        }
        else{
            addr = "";
        }
        if(!dateStart.getValue().equals("")){
            startDate = DateHelper.getDate(dateStart.getValue().getDayOfMonth(), dateStart.getValue().getMonthValue(), dateStart.getValue().getYear());
        }
        else{
            startDate = DateHelper.getDate(1,1,1700);
        }
        if(!dateEnd.getValue().equals("")) {
            endDate = DateHelper.getDate(dateEnd.getValue().getDayOfMonth(), dateEnd.getValue().getMonthValue(), dateEnd.getValue().getYear());
        }
        else{
            endDate = DateHelper.getDate(1,1,2300);
        }
        if(!contentStart.getText().isEmpty()) {
            contentMin = Double.valueOf(contentStart.getText());
        }
        else{
            contentMin = 0.0;
        }
        if(!contentStart.getText().isEmpty()) {
            contentMax = Double.valueOf(contentEnd.getText());
        }
        else{
            contentMax = 999999.99;
        }
        data.remove(0, data.size());

        //Find & add matching applications
        List<SubmittedApplication> resultsList = search.advancedSearch(brandName, fancifulName, wantBeer, wantWine, wantSpirits, eAddr, addr, startDate, endDate, contentMin, contentMax);
        data.addAll(resultsList); //change to resultsList
    }
}
