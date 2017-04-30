package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.ApplicationType;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.util.HashMap;

public class UpdateOptionsController implements IController {
    @FXML
    private Button selectRev;
    @FXML
    private CheckBox option1, option2, option3, option4, option5, option6, option7, option8, option9, option10, option11, option12;
    @FXML
    private CheckBox option13, option14, option15, option16, option17, option18, option19, option20, option21, option22, option23;
    @FXML
    private CheckBox option24, option25, option26, option27, option28, option29, option30, option31, option32, option33, option34;

    private Main main;
    private SubmittedApplication application;
    private ApplicationType appType;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getApplication("app"));
    }

    public void init(Main main){
        this.main = main;
    }

    public void init(Main main, SubmittedApplication application) {
        this.main = main;
        this.application=application;
        this.appType=application.getApplication().getApplicationType();
    }

    public void nextPage(){

       HashMap<String,Boolean> updates= new HashMap<>();
        updates.put("label", false);
        updates.put("alcContent", false);
        updates.put("vintYr", false);
        updates.put("pH", false);
        updates.put("formula", false);
        updates.put("stateSelect", false);
        updates.put("alcoholName", false);
        updates.put("brandName", false);
        updates.put("address", false);
        updates.put("extraInfo", false);
        updates.put("repID", false);
        updates.put("date", false);

        if(option1.isSelected() || option2.isSelected() || option3.isSelected() || option4.isSelected()
                || option6.isSelected() || option8.isSelected() || option9.isSelected() || option14.isSelected()
                || option15.isSelected() || option26.isSelected() || option27.isSelected() || option28.isSelected()
                || option29.isSelected() || option30.isSelected() || option31.isSelected() || option23.isSelected()
                || option34.isSelected()){
            updates.replace("label", true);
        }
        if(option5.isSelected()){
            updates.replace("vintYr", true);
        }
        if(option7.isSelected()){
            updates.replace("pH", true);
        }
        if(option10.isSelected()||option17.isSelected()){
            updates.replace("formula", true);
        }
        if(option11.isSelected()){
            updates.replace("label", true);
            updates.replace("alcContent", true);
        }
        if(option12.isSelected()||option13.isSelected()){
            updates.replace("alcContent", true);
        }
        if(option16.isSelected()){
            updates.replace("stateSelect", true);
        }
        if(option18.isSelected()){
            updates.replace("date", true);
        }
        if(option19.isSelected()){
            updates.replace("alcContent", true);
            updates.replace("brandName", true);
            updates.replace("address", true);
        }
        if(option20.isSelected()){
            updates.replace("alcoholName", true);
            updates.replace("address", true);
            updates.replace("brandName", true);
        }
        if(option21.isSelected()){
            updates.replace("alcoholName", true);
            updates.replace("address", true);
            updates.replace("brandName", true);
        }
        if(option22.isSelected()){
            updates.replace("label", true);
            updates.replace("extraInfo", true);
        }
        if(option24.isSelected()){
            updates.replace("address", true);
        }
        if(option25.isSelected()){
            updates.replace("repID", true);
        }
        if(option32.isSelected()){
            updates.replace("formula", true);
            updates.replace("label", true);
        }
        if(option33.isSelected()){
            updates.replace("extraInfo", true);
            updates.replace("label", true);
        }

        //application.setUpdatesSelected(updates);

        //go to page 2 of update app
        main.loadFXML("/fxml/UpdateApplication.fxml", application);

    }

    public void cancelApp() {
        //Go back to homepage
        main.loadFXML("fxml/ApplicantWorkflowPage.fxml");
    }


}