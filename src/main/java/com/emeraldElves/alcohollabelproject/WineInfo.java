package com.emeraldElves.alcohollabelproject;


public class WineInfo extends AlcoholInfo{

    private int vintageYear;
    private int pH;

    /**
     * Constructor to create a new WineInfo
     * @param alcoholContent Alcohol percentage of beverage
     * @param name Fanciful name of alcohol
     * @param brandName Brand name of alcohol
     * @param origin Whether the alcohol is domestic or imported
     * @param vintageYear Vintage year of the alcohol
     * @param pH The pH of the alcohol
     */
    public WineInfo(int alcoholContent, String name, String brandName, ProductSource origin, WineInfo Wine_Info, int vintageYear, int pH), {
        super(alcoholContent, name, brandName, origin, AlcoholType.WINE, Wine_Info );
        this.vintageYear=vintageYear;
        this.pH=pH;
    }

    /**
     * Gets the vintage year of the alcohol
     * @return the vintage year as an int
     */
    public int getVintageYear(){
        return this.vintageYear;
    }

    /**
     * Gets the pH of the alcohol
     * @return the pH as an int
     */
    public int getPH(){
        return this.pH;
    }
}
