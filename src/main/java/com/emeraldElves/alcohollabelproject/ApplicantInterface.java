package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SavedApplication;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.UserInterface.Bundle;
import com.emeraldElves.alcohollabelproject.UserInterface.IController;

import java.util.List;

/**
 * Created by Kylec on 4/10/2017.
 */
public class ApplicantInterface implements IController {

    private Storage storage;
    private Applicant applicant;
    private String username;

    public ApplicantInterface(String username){
        this.username = username;
        storage = Storage.getInstance();
        applicant = new Applicant(storage.getApplicationsByApplicant(username), storage.getSavedApplicationsByApplicant(username));
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public boolean submitApplication(SubmittedApplication application){
        boolean submitted = storage.submitApplication(application, username);
        if(submitted){
            applicant.setApplications(storage.getApplicationsByApplicant(username));
        }
        return submitted;
    }

    public boolean updateApplication(SubmittedApplication application){
        return submitApplication(application);
    }

    public List<SubmittedApplication> getSubmittedApplications(){
        return applicant.getApplications();
    }

    public List<SavedApplication> getSavedApplications(){
        return applicant.getSavedApplications();
    }


    @Override
    public void init(Bundle data) {

    }
}
