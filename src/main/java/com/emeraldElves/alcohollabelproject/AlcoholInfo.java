package com.emeraldElves.alcohollabelproject;

/**
 * Created by Tarek on 4/1/2017.
 */
public class AlcoholInfo {

    private int alcoholContent;
    private String name;
    private String brandName;
    private ProductSource origin;

    /**
     * Constructor to create a new AlcoholInfo
     * @param alcoholContent Alcohol percentage of beverage
     * @param name Fanciful name of alcohol
     * @param brandName Brand name of alcohol
     * @param origin Whether the alcohol is domestic or imported
     */
    public AlcoholInfo(int alcoholContent, String name, String brandName, ProductSource origin){
        this.alcoholContent=alcoholContent;
        this.name=name;
        this.brandName=brandName;
        this.origin=origin;
    }

    /**
     * Gets the alcohol content of the alcohol
     * @return the alcohol percentage as an int
     */
    public int getAlcoholContent(){
        return this.alcoholContent;
    }

    /**
     * Gets the fanciful name of the alcohol
     * @return the alcohol name as a String
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the brand name of the alcohol
     * @return the brand name as a String
     */
    public String getBrandName(){
        return this.brandName;
    }

    /**
     * Gets the origin of the alcohol
     * @return Domestic or Imported
     */
    public ProductSource getOrigin(){
        return this.origin;
    }






}
