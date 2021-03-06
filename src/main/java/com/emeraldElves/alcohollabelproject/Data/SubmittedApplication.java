package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Applicant;
import com.emeraldElves.alcohollabelproject.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a submitted application
 */
public class SubmittedApplication {

    private ApplicationInfo application;
    private ApplicationStatus status;
    private Applicant applicant;
    private long applicationID = -1;
    private String ttbMessage = "";
    private ProxyLabelImage proxyImage;
    private String ttbAgentName = "";
    //Adding fields for CSV import
    private int classTypeCode;
    private String applType;
    private String specialDesc;
    private Date issueDate;
    private Date surrenderedDate;
    private String recievedCode;
    //End of adding fields for csv import

    public String getTtbMessage() {
        return ttbMessage;
    }
    private HashSet<String> updatesSelected= new HashSet<>();

    public void setTtbMessage(String ttbMessage) {
        this.ttbMessage = ttbMessage;
    }


    /**
     * Creates an application which was submitted.
     *
     * @param application The application which was submitted.
     * @param status      The current status of the application.
     * @param applicant   The applicant of the application.
     */
    public SubmittedApplication(ApplicationInfo application, ApplicationStatus status, Applicant applicant) {
        this.application = application;
        this.status = status;
        this.applicant = applicant;

        this.proxyImage = new ProxyLabelImage("");
        

    }

    public long getApplicationID() {
        return applicationID;
    }
    public void setTtbAgentName(String name){ this.ttbAgentName = name;}

    public String getTtbAgentName(){ return this.ttbAgentName;}

    public void setApplicationID(long applicationID) {
        this.applicationID = applicationID;
    }

    public ApplicationInfo getApplication() {
        return application;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public ProxyLabelImage getproxyImage(){return proxyImage;}

    public void setApplication(ApplicationInfo application) {
        this.application = application;
    }

    public void setStatus(ApplicationStatus status){
        this.status = status;
    }

    public void setApplicant(Applicant applicant){
        this.applicant = applicant;
    }

    public ILabelImage getImage() {
        return proxyImage;
    }

    public void setImage(ProxyLabelImage proxyImage) {
        this.proxyImage = proxyImage;
    }

    public void setUpdatesSelected(HashSet<String> updates){
        this.updatesSelected=updates;
    }

    public HashSet<String> getUpdatesSelected(){
        return this.updatesSelected;
    }

    public void setClassTypeCode(int classTypeCode) {
        this.classTypeCode = classTypeCode;
    }

    public void setApplType(String applType) {
        this.applType = applType;
    }

    public void setSpecialDesc(String specialDesc) {
        this.specialDesc = specialDesc;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setSurrenderedDate(Date surrenderedDate) {
        this.surrenderedDate = surrenderedDate;
    }

    public void setRecievedCode(String recievedCode) {
        this.recievedCode = recievedCode;
    }

    public int getClassTypeCode() {
        return classTypeCode;
    }

    public String getApplType() {
        return applType;
    }

    public String getSpecialDesc() {
        return specialDesc;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getSurrenderedDate() {
        return surrenderedDate;
    }

    public String getRecievedCode() {
        return recievedCode;
    }
}
