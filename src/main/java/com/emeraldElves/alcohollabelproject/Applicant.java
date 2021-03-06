package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SavedApplication;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import java.util.List;

/**
 * Represents an applicant who may have submitted applications.
 */
public class Applicant {

    private List<SubmittedApplication> applications;
    private List<SavedApplication> savedApplications;
    private String email;
    private String name;
    private String representativeID = "0";
    private String permitNum = "";
    private String address = "";
    private String phoneNum = "";
    private String company = "";

    /**
     * Creates an Applicant with the given applications.
     *
     * @param applications The submitted applications of the applicant.
     */
    public Applicant(List<SubmittedApplication> applications) {
        this.applications = applications;
    }

    public Applicant(List<SubmittedApplication> applications, List<SavedApplication> saves){
        this.applications = applications;
        this.savedApplications = saves;
    }

    /**
     * Creates applicant with fields
     */
    public Applicant(String email, String name, String representativeID, String permitNum, String address, String phoneNum, String company) {
        this.email = email;
        this.name = name;
        this.representativeID = representativeID;
        this.permitNum = permitNum;
        this.address = address;
        this.phoneNum = phoneNum;
        this.company = company;
    }

    public List<SubmittedApplication> getApplications() {
        return applications;
    }
    public List<SavedApplication> getSavedApplications(){ return savedApplications; }
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
        this.company = fields.getCompany();
    }

    public String getEmailAddress() {
        return email;
    }
    public String getRepresentativeIDFromDB(String email) {
        getApplicantFields(email);
        return this.getRepresentativeID();
    }
    public String getPhoneNumFromDB(String email) {
        getApplicantFields(email);
        return this.getPhoneNum();
    }
    public String getCompanyFromDB(String email) {
        getApplicantFields(email);
        return this.getCompany();
    }
    public String getPermitNumFromDB(String email) {
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
//    public String getPhoneNumFromDB(String email) { return ""; }

    // getter functions -- NOT from DB
    public String getRepresentativeID() { return representativeID; }
    public String getPermitNum() { return permitNum; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }
    public String getCompany() { return company; }

    // setter functions - set to DB
    public void setRepresentativeID(String email, String representativeID) {
        storage.modifyRepresentativeID(email, representativeID);
    }
    public void setPermitNum(String email, String permitNum) {
        storage.modifypermitNum(email, permitNum);
    }
    public void setAddress(String email, String address) {
        storage.modifyAddress(email, address);
    }
    public void setPhoneNum(String email, String phoneNum) {
        storage.modifyphoneNum(email, phoneNum);
    }
    public void setCompany(String email, String company) {
        storage.modifyCompany(email, company);
    }
    public void setName(String email, String name) {
        storage.modifyName(email, name);
    }
}
