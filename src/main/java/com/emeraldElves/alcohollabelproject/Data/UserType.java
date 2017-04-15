package com.emeraldElves.alcohollabelproject.Data;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Kylec on 4/4/2017.
 */
public enum UserType {
    TTBAGENT(0),
    APPLICANT(1),
    BASIC(2),
    SUPERAGENT(3);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType fromInt(int val) {
        switch (val) {
            case 0:
                return TTBAGENT;
            case 1:
                return APPLICANT;
            case 2:
                return BASIC;
            case 3:
                return SUPERAGENT;
            default: return BASIC;
        }
    }
}
