package com.emeraldElves.alcohollabelproject;

/**
 * Created by jessieying on 4/1/17.
 */
public class DistilledSpiritsAlcoholInfo extends AlcoholInfo {

    /**
     * Constructor to create a new OtherAlcoholInfo
     * @param alcoholContent Alcohol percentage of beverage
     * @param name Fanciful name of alcohol
     * @param brandName Brand name of alcohol
     * @param origin Whether the alcohol is domestic or imported
     */
    public DistilledSpiritsAlcoholInfo(int alcoholContent, String name, String brandName, ProductSource origin){
        super(alcoholContent, name, brandName, origin, AlcoholType.DISTILLEDSPIRITS, null);
    }
}
