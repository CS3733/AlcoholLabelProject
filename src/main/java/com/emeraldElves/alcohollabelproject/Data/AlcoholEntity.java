package com.emeraldElves.alcohollabelproject.Data;

import java.sql.JDBCType;

/**
 * Created by Essam on 4/9/2017.
 */
public class AlcoholEntity extends Entity {
    public AlcoholEntity(){
        super(new AlcoholModel());
    }
    public void setApplicationId(int id){
        this.set("applicationID", id);
    }
    public void setAlcoholContent(int content){ this.set("alcoholContent", content);}
    public void setFancifulName(String name){this.set("fancifulName", name);}
    public void setBrandName(String name){this.set("brandName", name);}
    public void setOrigin(int origincode){this.set("origin", origincode);}
    public void setOrigin(ProductSource origin){this.setOrigin(origin.getValue());}
    public void setType(int type){this.set("type", type);}
    public void setType(AlcoholType type){this.setType(type.getValue());}
    public void setFormula(String formula){this.set("formula", formula);}
    public void setSerialNumber(String serial){this.set("serialNumber", serial);}
    public void setPH(double ph){this.set("pH", ph);}
    public void setVintageYear(int year){this.set("vintageYear", year);}
    public void setVarietals(String varietals){this.set("varietals", varietals);}
    public void setWineAppellation(String appellation){this.set("wineAppellation", appellation);}

    public int getApplicationId(){return Integer.parseInt(this.get("applicationID"));}
    public int getAlcoholContent(){ return Integer.parseInt(this.get("alcoholContent"));}
    public String getFancifulName(){return this.get("fancifulName");}
    public String getBrandName(){return this.get("brandName");}
    public int getOrigin(){return Integer.parseInt(this.get("origin"));}
    public String getFormula(){return this.get("formula");}
    public String getSerialNumber(){return this.get("serialNumber");}
    public double getPH(){return Double.parseDouble(this.get("pH"));}
    public AlcoholType getType(){return AlcoholType.fromInt(Integer.parseInt(this.get("type")));}
    public int getVintageYear(){return Integer.parseInt(this.get("vintageYear"));}
    public String getVarietals(){return this.get("varietals");}
    public String getWineAppellation(){return this.get("wineAppellation");}

}
