package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kyle on 4/30/2017.
 */
public class TestStorage implements IStorage {
    @Override
    public boolean submitApplication(SubmittedApplication application, String username) {
        return false;
    }

    @Override
    public String getAgentPassword(String username) {
        return null;
    }

    @Override
    public String getUserPassword(String username) {
        return null;
    }

    @Override
    public boolean updateApplication(SubmittedApplication application, String username) {
        return false;
    }

    @Override
    public boolean approveApplication(SubmittedApplication application, String agentName, Date expirationDate) {
        return false;
    }

    @Override
    public boolean rejectApplication(SubmittedApplication application, String reason) {
        return false;
    }

    @Override
    public boolean removeSavedApplication(SavedApplication application) {
        return false;
    }

    @Override
    public boolean addApplication(SubmittedApplication application, String agentUsername) {
        return false;
    }

    @Override
    public boolean createUser(PotentialUser user) {
        return false;
    }

    @Override
    public void deleteUser(PotentialUser potentialUser) {

    }

    @Override
    public boolean applyForUser(PotentialUser user) {
        return false;
    }

    @Override
    public List<PotentialUser> getPotentialUsers() {
        return null;
    }

    @Override
    public boolean saveApplication(SavedApplication application, String username) {
        return false;
    }

    @Override
    public boolean saveUpdateHistory(SubmittedApplication application, String username) {
        return false;
    }

    @Override
    public boolean isValidUser(UserType usertype, String username, String password) {
        return false;
    }

    @Override
    public void updatePassword(String username, String password) {

    }

    @Override
    public boolean isValidUser(String username) {
        return false;
    }

    @Override
    public boolean isValidAgent(String username) {
        return false;
    }

    @Override
    public boolean isValidApplicant(String username) {
        return false;
    }

    @Override
    public boolean isValidUser(String username, String password) {
        return false;
    }

    @Override
    public List<SubmittedApplication> getRecentlyApprovedApplications(int numApps) {
        return null;
    }

    @Override
    public List<SubmittedApplication> getApprovedApplications() {
        return null;
    }

    @Override
    public List<SubmittedApplication> getApplicationsByBrandName(String brandName) {
        return null;
    }

    @Override
    public List<SubmittedApplication> getApplicationsByName(String name) {
        return null;
    }

    @Override
    public List<SubmittedApplication> getApplicationsByFancifulName(String fancifulName) {
        return null;
    }

    @Override
    public List<SubmittedApplication> getApplicationsByApplicant(String username) {
        List<SubmittedApplication> applications = new ArrayList<>();
        SubmittedApplication application = new SubmittedApplication(null, null, null);
        application.setApplicationID(10);
        applications.add(application);
        return applications;
    }

    @Override
    public List<SavedApplication> getSavedApplicationsByApplicant(String username) {
        List<SavedApplication> applications = new ArrayList<>();
        SavedApplication application = new SavedApplication(null, null, null, null);
        application.setApplicationID(5);
        applications.add(application);
        return applications;
    }

    @Override
    public List<SubmittedApplication> getAssignedApplications(String agentName) {
        return null;
    }

    @Override
    public List<String> getAllTTBUsernames() {
        return null;
    }

    @Override
    public Applicant getUserFromEmail(String email) {
        return null;
    }

    @Override
    public void modifyRepresentativeID(String email, int repID) {

    }

    @Override
    public void modifypermitNum(String email, String permitNum) {

    }

    @Override
    public void modifyAddress(String email, String address) {

    }

    @Override
    public void modifyphoneNum(String email, String phoneNum) {

    }

    @Override
    public void modifyName(String email, String name) {

    }
}
