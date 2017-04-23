package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by Dan on 4/23/2017.
 */
public class SavedApplication {

    private ApplicationType applicationType;
    private AlcoholInfo alcoholInfo;
    private String extraInfo;
    private String imageURL;

    public SavedApplication(ApplicationType applicationType, AlcoholInfo alcoholInfo, String extraInfo, String imageURL) {
        this.applicationType = applicationType;
        this.alcoholInfo = alcoholInfo;
        this.extraInfo = extraInfo;
        this.imageURL = imageURL;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public AlcoholInfo getAlcoholInfo() {
        return alcoholInfo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public String getImageURL() {
        return imageURL;
    }
}
