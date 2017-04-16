package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.PotentialUser;
import com.emeraldElves.alcohollabelproject.Data.Storage;

import java.util.List;

/**
 * Created by Dan on 4/15/2017.
 */
public class SuperUserWorkflowController {
    List<PotentialUser> users;
    Main main;
    public void init(Main main){
        this.main = main;
        //get all from current potential user database
        users = Storage.getInstance().getPotentialUsers();
        //add to displayed table, copy from search functionality
    }

    //on click, load detailed user page
    //on accept/reject(not this controller), send it to the email address attached
    //on accept, add it the TTB agent / applicant table (method is in storage)


    public void goHome(){ main.loadHomepage();}


}
