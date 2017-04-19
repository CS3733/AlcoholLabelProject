package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.List;

/**
 * Represents an applicant who may have submitted applications.
 */
public class Applicant {

    private List<SubmittedApplication> applications;
    private String email;
    private String name;
    private int representativeID = 0;
    private int permitNum = 0;
    private String address = "";
    private String phoneNum = "";

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
    public Applicant(String email, String name, int representativeID, int permitNum, String address, String phoneNum) {
        this.email = email;
        this.name = name;
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

    public String getName() {
        return name;
    }

    private Storage storage = Storage.getInstance();

    public void getApplicantFields(String email) {
        Applicant fields = storage.getUserFromEmail(email);
        this.email = email;
        this.representativeID = fields.getRepresentativeID();
        this.permitNum = fields.getPermitNum();
        this.address = fields.getAddress();
        this.phoneNum = fields.getPhoneNum();
        this.name = fields.getName();
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
    public String getNamefromDB(String email) {
        getApplicantFields(email);
        return this.getName();
    }
    public String getPhoneNumFromDB(String email) { return ""; }

    // getter functions -- NOT from DB
    public int getRepresentativeID() { return representativeID; }
    public int getPermitNum() { return permitNum; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }

    // setter functions - set to DB
    public void setEmailAddress() {}
    public void setRepresentativeID(String email, int representativeID) {
        storage.modifyRepresentativeID(email, representativeID);
    }
    public void setPermitNum(String email, int permitNum) {}
    public void setAddress(String email, String address) {}
    public void setPhoneNum(String email, String phoneNum) {}
    public void setName(String email, String name) {}
}
