package com.emeraldElves.alcohollabelproject;

import java.util.Date;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class ApplicationInfo {
    private Date submissionDate;
    private ManufacturerInfo manufacturer;
    private AlcoholInfo submittedAlcohol;
    //private ApplicationType appType;
    private String extraInfo;

    public ApplicationInfo(Date submissionDate, ManufacturerInfo manufacturer, AlcoholInfo submittedAlcohol, String extraInfo) {
        this.submissionDate = submissionDate;
        this.manufacturer = manufacturer;
        this.submittedAlcohol = submittedAlcohol;
        this.extraInfo = extraInfo;
    }

    public ApplicationInfo(Date sub, ManufacturerInfo man, AlcoholInfo alc){
        submissionDate = sub;
        manufacturer = man;
        submittedAlcohol = alc;
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
}
