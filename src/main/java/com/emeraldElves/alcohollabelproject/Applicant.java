package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.List;

/**
 * Represents an applicant who may have submitted applications.
 */
public class Applicant {

    private List<SubmittedApplication> applications;

    /**
     * Creates an Applicant with the given applications.
     *
     * @param applications The submitted applications of the applicant.
     */
    public Applicant(List<SubmittedApplication> applications) {
        this.applications = applications;
    }
    public List<SubmittedApplication> getApplications() {
        return applications;
    }
    public void setApplications(List<SubmittedApplication> subApps) {
        this.applications = subApps;
    }
    public void addSubmittedApp(SubmittedApplication submittedApp) {
        this.applications.add(submittedApp);
    }


}
