package com.emeraldElves.alcohollabelproject;

/**
 * Created by keionbis on 4/3/17.
 */
public enum AlcoholType {
    BEER(0),
    WINE(1),
    OTHER(2);

    private int value;

    AlcoholType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
