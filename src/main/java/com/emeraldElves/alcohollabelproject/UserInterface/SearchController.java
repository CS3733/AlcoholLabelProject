package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.COLASearch;
import com.emeraldElves.alcohollabelproject.Data.*;
import com.emeraldElves.alcohollabelproject.*;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang3.StringEscapeUtils;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextInputDialog;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


import java.io.IOException;
import java.util.*;

import static javafx.stage.PopupWindow.AnchorLocation.CONTENT_BOTTOM_LEFT;
import static javafx.stage.PopupWindow.AnchorLocation.CONTENT_TOP_RIGHT;

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
    private TableView<ApplicationEntity> resultsTable;
    @FXML
    private TableColumn<ApplicationEntity, String> dateCol;
    @FXML
    private TableColumn<ApplicationEntity, String> manufacturerCol;
    @FXML
    private TableColumn<ApplicationEntity, String> brandCol;
    @FXML
    private TableColumn<ApplicationEntity, String> typeCol;
    @FXML
    private TableColumn<ApplicationEntity, String> contentCol;
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

    private ObservableList<ApplicationEntity> data = FXCollections.observableArrayList();
    private COLASearch search;

    private SearchSubject searchTermSubject;

    public SearchController() {
    }

    public void init(Bundle bundle){

        this.init((Main)bundle.get("main"), (String)bundle.get("searchTerm"));
    }

    public void init(Main main, String searchTerm) {
        this.main = main;
        this.searchTerm = searchTerm;
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ApplicationEntity, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ApplicationEntity, String> p) {
                Date date = p.getValue().getSubmissionDate();
                return new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(DateHelper.dateToString(date)));
            }
        });
        manufacturerCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getFancifulName())));
        brandCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getBrandName())));
        typeCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(p.getValue().getAlcoholType().toString())));
        contentCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<String>(StringEscapeUtils.escapeJava(String.valueOf(p.getValue().getAlcoholContent()))));
        saveBtn.setDisable(data.size() == 0);
        descriptionLabel.setVisible(false);
        contextSaveBtn.setDisable(data.size() == 0);
        resultsTable.setItems(data);
        resultsTable.setRowFactory(tv -> {
            TableRow<ApplicationEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ApplicationEntity rowData = row.getItem();
                    //main.loadFXML("/fxml/DetailedSearchPage.fxml", rowData, searchTerm);
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

    public void search(String searchTerm) {
        //Remove previous results
        this.searchTerm = searchTerm;
        data.remove(0, data.size());
        List<ApplicationEntity> resultsList = IOManager.list(ApplicationEntity.class);//search.searchByName(searchTerm.trim());
        filterList(resultsList);
        data.addAll(resultsList); //change to resultsList
        descriptionLabel.setText("Showing " + data.size() + " results for \"" + searchTerm + "\"");
        descriptionLabel.setVisible(true);
        saveBtn.setDisable(data.size() == 0);
        contextSaveBtn.setDisable(data.size() == 0);
    }

    private void refreshSuggestions() {
        DetachedCriteria criteria = DetachedCriteria.forClass(ApplicationEntity.class);
        criteria.add(Restrictions.disjunction()
                .add(Restrictions.like("brandName", "%"+searchTerm+"%").ignoreCase())
                .add(Restrictions.like("fancifulName", "%"+searchTerm+"%").ignoreCase()));
        criteria.add(Restrictions.eq("status", 3));
        List<ApplicationEntity> resultsList = IOManager.find(ApplicationEntity.class, criteria);
        filterList(resultsList);
        possibleSuggestions.clear();
        for (ApplicationEntity application : resultsList) {
            possibleSuggestions.add(application.getBrandName());
            possibleSuggestions.add(application.getFancifulName());
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

    private void filterList(List<ApplicationEntity> appList) {
        appList.removeIf(p -> (filterBeers.isSelected() && p.getAlcoholType() == AlcoholType.BEER));
        appList.removeIf(p -> (filterWine.isSelected() && p.getAlcoholType() == AlcoholType.WINE));
        appList.removeIf(p -> (filterSpirits.isSelected() && p.getAlcoholType() == AlcoholType.DISTILLED_SPIRITS));
    }

    public void saveTSV(ActionEvent e) {
        ApplicationExporter exporter = new ApplicationExporter(new TSVExporter());
        //exporter.exportToFile(data);
    }
    public void saveUserChar(ActionEvent ae) {
        ApplicationImporter ai = new ApplicationImporter(new UserCharImporter(','));
        try {
            ai.importFromFile();
        }
        catch(IOException e){

        }
        TextInputDialog dialog = new TextInputDialog();
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        dialog.setTitle("Data Exporter");
        dialog.setHeaderText("Enter a character.");
        dialog.setContentText("Please enter a character to separate data:");

        TextField tField = dialog.getEditor();
        Tooltip customTooltip = new Tooltip();
        customTooltip.setText("Illegal delimiter character");
        ((Button)dialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Export");

        customTooltip.setAutoHide(true);
        customTooltip.setAnchorLocation(CONTENT_BOTTOM_LEFT);

        //System.out.println(tField.getFont().getSize() + tField.getBorder().getOutsets().getLeft() + (tField.getPadding().getLeft()*2) + (tField.getInsets().getLeft()*2));
        tField.setOnKeyTyped(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {

                Platform.runLater(() -> {
                    tField.setTooltip(null);
                    customTooltip.hide();
                    tField.setText(ke.getCharacter());

                    //Read from textbox, not from ke.getCharacter() to avoid Backspace (\\u08) character
                    String userStr = tField.getText();
                    tField.setStyle("");
                    if (userStr.length() == 0){
                        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
                    }
                    else {
                        try {
                            ApplicationExporter exporter = new ApplicationExporter(new UserCharExporter(userStr.charAt(0), "txt"));
                            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                        }
                        catch (IllegalArgumentException e){
                            Point2D p = tField.localToScene(0.0, 0.0);
                            tField.setTooltip(customTooltip);

                            customTooltip.show(tField, p.getX()
                                    + tField.getScene().getX() + tField.getScene().getWindow().getX(), p.getY()
                                    + tField.getScene().getY() + tField.getScene().getWindow().getY());
                            dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
                            tField.setStyle("-fx-focus-color: red;-fx-text-box-border: red;");
                        }
                    }
                });
            }
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(userStr -> {
            try {
                if (userStr.length() == 1) {
                    ApplicationExporter exporter = new ApplicationExporter(new UserCharExporter(userStr.charAt(0), "txt"));
                    //exporter.exportToFile(data);
                }
            }
            catch (IllegalArgumentException e){
                System.out.println("Illegal thingy");
            }
        });


    }

    public void saveCSV(ActionEvent e) {

        ApplicationExporter exporter = new ApplicationExporter(new CSVExporter());
        //exporter.exportToFile(data);
    }
}
