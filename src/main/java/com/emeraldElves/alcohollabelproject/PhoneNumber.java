package com.emeraldElves.alcohollabelproject;

/**
 * A representation of a phone number which is validated.
 */
public class PhoneNumber {

    private String phoneNumber;


    /**
     * Create a phone number.
     *
     * @param phoneNumber The phone number in most acceptable formats.
     */
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

    /**
     * Get the phone number as a string of only numbers without any dashes or dots.
     *
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Extract the phone number from a string.
     *
     * @param phoneNumber A string containing the phone number.
     * @return The string phone number with only numbers.
     */
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
