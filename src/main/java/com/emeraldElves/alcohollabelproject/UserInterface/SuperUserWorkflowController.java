package com.emeraldElves.alcohollabelproject.UserInterface;

/**
 * Created by Dan on 4/15/2017.
 */
public class SuperUserWorkflowController {

    Main main;
    public void init(Main main){
        this.main = main;
        //show all from current potential user database

    }

    public void goHome(){ main.loadHomepage();}


}
