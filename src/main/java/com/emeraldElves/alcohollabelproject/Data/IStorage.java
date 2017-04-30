package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Applicant;

import java.util.Date;
import java.util.List;

/**
 * Created by Kyle on 4/30/2017.
 */
public interface IStorage {
    boolean submitApplication(SubmittedApplication application, String username);

    String getAgentPassword(String username);

    String getUserPassword(String username);

    boolean updateApplication(SubmittedApplication application, String username);

    boolean approveApplication(SubmittedApplication application, String agentName, Date expirationDate);

    boolean rejectApplication(SubmittedApplication application, String reason);

    boolean removeSavedApplication(SavedApplication application);

    boolean addApplication(SubmittedApplication application, String agentUsername);

    boolean createUser(PotentialUser user);

    void deleteUser(PotentialUser potentialUser);

    boolean applyForUser(PotentialUser user);

    List<PotentialUser> getPotentialUsers();

    boolean saveApplication(SavedApplication application, String username);

    boolean saveUpdateHistory(SubmittedApplication application, String username);

    boolean isValidUser(UserType usertype, String username, String password);

    void updatePassword(String username, String password);

    boolean isValidUser(String username);

    boolean isValidAgent(String username);

    boolean isValidApplicant(String username);

    boolean isValidUser(String username, String password);

    List<SubmittedApplication> getRecentlyApprovedApplications(int numApps);

    List<SubmittedApplication> getApprovedApplications();

    List<SubmittedApplication> getApplicationsByBrandName(String brandName);

    List<SubmittedApplication> getApplicationsByName(String name);

    List<SubmittedApplication> getApplicationsByFancifulName(String fancifulName);

    List<SubmittedApplication> getApplicationsByApplicant(String username);

    List<SavedApplication> getSavedApplicationsByApplicant(String username);

    List<SubmittedApplication> getAssignedApplications(String agentName);

    List<String> getAllTTBUsernames();

    Applicant getUserFromEmail(String email);

    void modifyRepresentativeID(String email, int repID);

    void modifypermitNum(String email, String permitNum);

    void modifyAddress(String email, String address);

    void modifyphoneNum(String email, String phoneNum);

    void modifyName(String email, String name);
}
