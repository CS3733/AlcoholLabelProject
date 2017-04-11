package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Applicant;
import com.emeraldElves.alcohollabelproject.Data.ApplicationInfo;
import com.emeraldElves.alcohollabelproject.Data.ApplicationStatus;

/**
 * Represents a submitted application
 */
public class SubmittedApplication {

    private ApplicationInfo application;
    private ApplicationStatus status;
    private Applicant applicant;
    private int applicationID = -1;
    private String ttbMessage = "";
    private ILabelImage proxyImage;
    public String getTtbMessage() {
        return ttbMessage;
    }

    public void setTtbMessage(String ttbMessage) {
        this.ttbMessage = ttbMessage;
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
        proxyImage = new ProxyLabelImage("");
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

    public ILabelImage getImage() {
        return proxyImage;
    }

    public void setImage(ILabelImage proxyImage) {
        this.proxyImage = proxyImage;
    }
}
