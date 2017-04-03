package com.emeraldElves.alcohollabelproject;


public class WineInfo extends AlcoholInfo{

    /**
     * Constructor to create a new WineInfo
     * @param alcoholContent Alcohol percentage of beverage
     * @param name Fanciful name of alcohol
     * @param brandName Brand name of alcohol
     * @param origin Whether the alcohol is domestic or imported
     * @param vintageYear Vintage year of the alcohol
     * @param pH The pH of the alcohol
     */
    public WineInfo(int alcoholContent, String name, String brandName, ProductSource origin, int vintageYear, int pH){
        super(alcoholContent, name, brandName, origin, AlcoholType.WINE,  new AlcoholInfo.Wine(pH,vintageYear));
    }
}
