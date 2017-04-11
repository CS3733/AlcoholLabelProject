package com.emeraldElves.alcohollabelproject;

/**
 * Represents a submitted application
 */
public class SubmittedApplication {

    private ApplicationInfo application;
    private ApplicationStatus status;
    private Applicant applicant;
    private int applicationID = -1;
    private String ttbMessage = "";
    private String proxyImage = "";
    public String getTtbMessage() {
        return ttbMessage;
    }

    public void setTtbMessage(String ttbMessage) {
        this.ttbMessage = ttbMessage;
    }

    public String getProxyImage() {
        return proxyImage;
    }

    public void setProxyImage(String proxyImage) {
        this.proxyImage = proxyImage;
    }

    /**
     * Creates an application which was submitted.
     *
     * @param application The application which was submitted.
     * @param status      The current status of the application.
     * @param applicant   The applicant of the application.
     */
    public SubmittedApplication(ApplicationInfo application, ApplicationStatus status, Applicant applicant) {
        this.application = application;
        this.status = status;
        this.applicant = applicant;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public ApplicationInfo getApplication() {
        return application;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplication(ApplicationInfo application) {
        this.application = application;
    }
    public void setStatus(ApplicationStatus status){
        this.status = status;
    }
    public void setApplicant(Applicant applicant){
        this.applicant = applicant;
    }

}
