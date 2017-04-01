package com.emeraldElves.alcohollabelproject;

/**
 * Created by Tarek on 4/1/2017.
 */
public class AlcoholInfo {

    private int alcoholContent;
    private String name;
    private String brandName;
    private String origin;

    AlcoholInfo(int alcoholContent, String name, String brandName, String origin){
        alcoholContent=this.alcoholContent;
        this.name=name;
        this.brandName=brandNamae;
        this.origin=origin;
    }

    int getAlcoholContent(){
        return this.alcoholContent;
    }

    String getName(){
        return this.name;
    }

    String getBrandName(){
        return this.brandName;
    }

    String getOrigin(){
        return this.origin;
    }






}
