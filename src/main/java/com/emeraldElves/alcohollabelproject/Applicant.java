package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Authenticator;

import java.util.List;

/**
 * Represents an applicant who may have submitted applications.
 */
public class Applicant {

    private List<SubmittedApplication> applications;
    private String email;
    private int representativeID;
    private int permitNum;
    private String address;
    private String phoneNum;

    /**
     * Creates an Applicant with the given applications.
     *
     * @param applications The submitted applications of the applicant.
     */
    public Applicant(List<SubmittedApplication> applications) {
        this.applications = applications;
    }

    /**
     * Creates applicant with fields
     */
    public Applicant(String email, int representativeID, int permitNum, String address, String phoneNum) {
        this.email = email;
        this.representativeID = representativeID;
        this.permitNum = permitNum;
        this.address = address;
        this.phoneNum = phoneNum;
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

    private Storage storage = Storage.getInstance();

    public void getApplicantFields(String email) {
        Applicant fields = storage.getUserFromEmail(email);
        this.email = fields.getEmailAddress();
        this.representativeID = fields.getRepresentativeID();
        this.permitNum = fields.getPermitNum();
        this.address = fields.getAddress();
        this.phoneNum = fields.getPhoneNum();
    }

    public String getEmailAddress() {
        return email;
    }
    public int getRepresentativeIDFromDB(String email) {
        getApplicantFields(email);
        return this.getRepresentativeID();
    }
    public int getPermitNumFromDB(String email) {
        getApplicantFields(email);
        return this.getPermitNum();
    }
    public String getAddressFromDB(String email) {
        getApplicantFields(email);
        return this.getAddress();
    }
    public String getPhoneNumFromDB(String email) { return ""; }

    // getter functions -- NOT from DB
    public int getRepresentativeID() { return representativeID; }
    public int getPermitNum() { return permitNum; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }

    public void setEmailAddress() {}
    public void setRepresentativeID() {}
    public void setPermitNum() {}
    public void setAddress() {}
    public void setPhoneNum() {}
}
