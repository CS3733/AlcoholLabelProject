package com.emeraldElves.alcohollabelproject.Data;

/**
 * Information about the manufacturer of the alcohol.
 */
public class ManufacturerInfo {

    private String name;
    private String physicalAddress;
    private String company;
    private int representativeID;
    private int permitNum;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;

    public ManufacturerInfo(String name, String physicalAddress, String company, int representativeID, int permitNum, PhoneNumber phoneNumber, EmailAddress emailAddress) {
        this.name = name;
        this.physicalAddress = physicalAddress;
        this.company = company;
        this.representativeID = representativeID;
        this.permitNum = permitNum;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * Get the name of the applicant.
     *
     * @return The name of the applicant.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the physical address of the manufacturer.
     *
     * @return The physical address of the manufacturer.
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * Get the company of the manufacturer.
     *
     * @return The company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Get the ID of the representative.
     *
     * @return The ID of the representative.
     */
    public int getRepresentativeID() {
        return representativeID;
    }

    /**
     * Get the permit number of the manufacturer.
     *
     * @return The permit number of the manufacturer.
     */
    public int getPermitNum() {
        return permitNum;
    }

    /**
     * Get the phone number of the manufacturer.
     *
     * @return The phone number of the manufacturer.
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get the email address of the manufacturer.
     *
     * @return The email address of the manufacturer.
     */
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }
}
