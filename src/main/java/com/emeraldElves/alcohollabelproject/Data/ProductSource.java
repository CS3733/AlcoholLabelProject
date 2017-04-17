package com.emeraldElves.alcohollabelproject.Data;

/**
 * Represents whether the alcohol is domestic or imported
 */
public enum ProductSource {
    DOMESTIC(0),
    IMPORTED(1);

    private int value;

    ProductSource(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProductSource fromInt(int val) {
        return val == 0 ? DOMESTIC : IMPORTED;
    }
}
