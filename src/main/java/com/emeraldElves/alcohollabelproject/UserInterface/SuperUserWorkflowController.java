package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.PotentialUser;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

/**
 * Created by Dan on 4/15/2017.
 */
public class SuperUserWorkflowController implements IController {
    @FXML
    Label Name, Type, PhoneNumber, RepresentativeID, Email, Date, physicalAddress, permitNo;
    List<PotentialUser> users;
    PotentialUser UserforApproval;
    Main main;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getPotentialUser("user"));
    }

    public void init(Main main,PotentialUser UserforApproval){
        this.main = main;
        this.UserforApproval = UserforApproval;
        //add to displayed table, copy from search functionality
        Name.setText(UserforApproval.getName());
        Type.setText(UserforApproval.getUserType().toString());
        PhoneNumber.setText(UserforApproval.getPhoneNumber().getPhoneNumber());
        RepresentativeID.setText(String.valueOf(UserforApproval.getRepresentativeID()));
        Email.setText(UserforApproval.getEmail().getEmailAddress());
        Date.setText(UserforApproval.getDate().toString());
        physicalAddress.setText(UserforApproval.getAddress());
        permitNo.setText(String.valueOf(UserforApproval.getPermitNum()));

    }

    public void Approve() {
        Storage.getInstance().createUser(UserforApproval);
        Storage.getInstance().deleteUser(UserforApproval);
        main.loadFXML("/fxml/SuperagentWorkflowPage.fxml");
    }

    public void Reject() {
        Storage.getInstance().deleteUser(UserforApproval);
        main.loadFXML("/fxml/SuperagentWorkflowPage.fxml");

    }

  

    public void printPage(){
        main.printPage();
    }

    //on click, load detailed user page
    //on accept/reject(not this controller), send it to the email address attached
    //on accept, add it the TTB agent / applicant table (method is in storage)


    public void goHome(){ main.loadFXML("/fxml/HomePage.fxml");}


}
