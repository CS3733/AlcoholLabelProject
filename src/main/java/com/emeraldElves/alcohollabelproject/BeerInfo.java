package com.emeraldElves.alcohollabelproject;

/**
 * Created by jessieying on 4/1/17.
 */
public class BeerInfo extends AlcoholInfo {

    /**
     * Constructor to create a new BeerInfo
     * @param alcoholContent Alcohol percentage of beverage
     * @param name Fanciful name of alcohol
     * @param brandName Brand name of alcohol
     * @param origin Whether the alcohol is domestic or imported
     */
    public BeerInfo(int alcoholContent, String name, String brandName, ProductSource origin){
        super(alcoholContent, name, brandName, origin, AlcoholType.BEER, null);
    }
}

//not sure if we should just remove this as it doesnt seem to be needed explicitly