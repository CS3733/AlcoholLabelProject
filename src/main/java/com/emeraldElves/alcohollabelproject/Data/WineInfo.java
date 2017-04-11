package com.emeraldElves.alcohollabelproject.Data;


public class WineInfo extends AlcoholInfo {

    /**
     * Constructor to create a new WineInfo
     * @param alcoholContent Alcohol percentage of beverage
     * @param name Fanciful name of alcohol
     * @param brandName Brand name of alcohol
     * @param origin Whether the alcohol is domestic or imported
     * @param vintageYear Vintage year of the alcohol
     * @param pH The pH of the alcohol
     * @param grapeVarietal The grape varietal of the wine
     * @param appellation The appellation of the wine
     */
    public WineInfo(int alcoholContent, String name, String brandName, ProductSource origin, int vintageYear, double pH, String grapeVarietal, String appellation){
        super(alcoholContent, name, brandName, origin, AlcoholType.WINE,  new AlcoholInfo.Wine(pH,vintageYear,grapeVarietal,appellation));
    }
}
