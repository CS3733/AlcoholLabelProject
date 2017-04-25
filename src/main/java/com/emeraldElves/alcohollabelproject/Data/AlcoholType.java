package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by keionbis on 4/3/17.
 */
public enum AlcoholType {
    BEER(0),
    WINE(1),
    DISTILLEDSPIRITS(2),
    UNKNOWN(3);

    private int value;

    AlcoholType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AlcoholType fromInt(int val) {
        switch (val) {
            case 0:
                return BEER;
            case 1:
                return WINE;
            default:
                return DISTILLEDSPIRITS;
        }
    }
    public static AlcoholType fromString(String str){
        switch (str.trim().toUpperCase()){
            case "WINE":
            case "WINES":
                return AlcoholType.WINE;
            case "BEER":
            case "BEERS":
                return AlcoholType.BEER;
            case "DISTILLED SPIRITS":
            case "DISTILLEDSPIRITS":
                return AlcoholType.DISTILLEDSPIRITS;
            default:
                return AlcoholType.UNKNOWN;

        }
    }

    public String toText(){
        switch (this){
            case BEER:
                return "Beer";
            case WINE:
                return "Wine";
            case DISTILLEDSPIRITS:
                return "Distilled Spirits";
            case UNKNOWN:
            default:
                return "Unknown Type";
        }
    }
}
