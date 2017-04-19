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

    public String getEmailAddress() { return ""; }
    public int getRepresentativeID() { return 0; }
    public int getPermitNum() { return 0; }
    public String getAddress() { return ""; }
    public String getPhoneNum() { return ""; }

    public void setEmailAddress() {}
    public void setRepresentativeID() {}
    public void setPermitNum() {}
    public void setAddress() {}
    public void setPhoneNum() {}
}
