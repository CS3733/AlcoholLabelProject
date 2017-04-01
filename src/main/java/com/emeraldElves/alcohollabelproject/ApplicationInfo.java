package com.emeraldElves.alcohollabelproject;

import java.util.Date;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class ApplicationInfo {
    private Date submissionDate;
    private ManufacturerInfo manufacturer;
    private AlcoholInfo submittedAlcohol;

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
}
