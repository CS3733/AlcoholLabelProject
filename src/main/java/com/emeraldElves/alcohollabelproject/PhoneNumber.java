package com.emeraldElves.alcohollabelproject;

/**
 * Created by Kylec on 4/1/2017.
 */
public class PhoneNumber {

    private String phoneNumber;


    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = extractPhoneNumber(phoneNumber);
    }

    /**
     * Check if the phone number is valid.
     *
     * @return True if the phone number is a valid phone number;
     */
    public boolean isValid() {
        return phoneNumber.length() == 10 || phoneNumber.length() == 11;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    private String extractPhoneNumber(String phoneNumber) {
        String extractedNumber = "";

        for (int i = 0; i < phoneNumber.length(); i++) {
            if (Character.isDigit(phoneNumber.charAt(i))) {
                extractedNumber += phoneNumber.charAt(i);
            }
        }

        return extractedNumber;
    }

}
