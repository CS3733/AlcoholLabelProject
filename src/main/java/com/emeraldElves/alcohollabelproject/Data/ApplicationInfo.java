package com.emeraldElves.alcohollabelproject.Data;

import java.util.Date;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class ApplicationInfo {
    private Date submissionDate;
    private ManufacturerInfo manufacturer;
    private AlcoholInfo submittedAlcohol;
    private ApplicationType appType;
    private String extraInfo;
    private String qualifications = ""; //Only used by TTB agents
    private Date expirationDate = new Date();
    

    public ApplicationInfo(Date submissionDate, ManufacturerInfo manufacturer, AlcoholInfo submittedAlcohol, String extraInfo, ApplicationType appType) {
        this.submissionDate = submissionDate;
        this.manufacturer = manufacturer;
        this.submittedAlcohol = submittedAlcohol;
        this.extraInfo = extraInfo;
        this.appType = appType;
    }


    public Date getSubmissionDate(){
        return submissionDate;
    }

    public ManufacturerInfo getManufacturer(){
        return manufacturer;
    }

    public AlcoholInfo getAlcohol(){
        return submittedAlcohol;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public ApplicationType getApplicationType(){ return appType;}
    public Date getExpirationDate(){
        return expirationDate;
    }

    public String getQualifications(){ return qualifications;}
//    public Date getExpirationDate(){return expirationDate;}

    public void setQualifications(String qualifications){ this.qualifications = qualifications;}

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }
}



