package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by Dan on 4/23/2017.
 */
public class SavedApplication {

    private ApplicationType applicationType;
    private AlcoholInfo alcoholInfo;
    private String extraInfo;
    private LabelImage image;
    private long applicationID = -1;

    public SavedApplication(ApplicationType applicationType, AlcoholInfo alcoholInfo, String extraInfo, LabelImage imageURL) {
        this.applicationType = applicationType;
        this.alcoholInfo = alcoholInfo;
        this.extraInfo = extraInfo;
        this.image = imageURL;
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

    public LabelImage getImage() {
        return image;
    }

    public long getApplicationID(){ return applicationID;}

    public void setApplicationID(long id){ applicationID = id;}
}
