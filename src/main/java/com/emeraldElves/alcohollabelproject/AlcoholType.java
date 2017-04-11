package com.emeraldElves.alcohollabelproject;

/**
 * Created by keionbis on 4/3/17.
 */
public enum AlcoholType {
    BEER(0),
    WINE(1),
    DISTILLEDSPIRITS(2);

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
}
