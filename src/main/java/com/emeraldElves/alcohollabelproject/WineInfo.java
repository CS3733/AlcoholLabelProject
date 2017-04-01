package com.emeraldElves.alcohollabelproject;

/**
 * Created by jessieying on 4/1/17.
 */
public class WineInfo extends AlcoholInfo {

    private int vintageYear;
    private int pH;

    WineInfo(int alcoholContent, String name, String brandName, String origin, int vintageYear, int pH){
        super(alcoholContent, name, brandName, origin);
        this.vintageYear=vintageYear;
        this.pH=pH;
    }

    int getVintageYear(){
        return this.vintageYear;
    }

    int getPH(){
        return this.pH;
    }




}
