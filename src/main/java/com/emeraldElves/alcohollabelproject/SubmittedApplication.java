package com.emeraldElves.alcohollabelproject;

/**
 * Represents a submitted application
 */
public class SubmittedApplication {

    private ApplicationInfo application;
    private ApplicationStatus status;
    private Applicant applicant;

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

    public ApplicationInfo getApplication() {
        return application;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Applicant getApplicant() {
        return applicant;
    }
}
