package com.emeraldElves.alcohollabelproject.Data;

import javax.naming.ServiceUnavailableException;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 4/9/2017.
 */
public class Storage {
    private AlcoholDatabase alcoholDB;
    private AuthenticatedUsersDatabase usersDB;
    private Database db;

    public boolean submitApplication(SubmittedApplication application, String username){
        return alcoholDB.submitApplication(application, username);
    }
    public boolean approveApplication(SubmittedApplication application, String agentName, Date expirationDate){
        return alcoholDB.approveApplication(application, agentName, expirationDate);
    }
    public boolean rejectApplication(SubmittedApplication application, String reason){
        return alcoholDB.rejectApplication(application, reason);
    }
    public boolean createUser(UserType usertype, String username, String password){
        if(usertype == UserType.TTBAGENT) {
            return db.insert("'" + username + "', '" + password + "'", "TTBAgentLogin");
        } else {
            return db.insert("'" + username + "', '" + password + "'", "ApplicantLogin");
        }
    }
    public boolean isValidUser(UserType usertype, String username, String password){
        return usersDB.isValidAccount(username, password);
    }
    public List<SubmittedApplication> getRecentlyApprovedApplications(int numApps){
        return alcoholDB.getMostRecentApproved(numApps);
    }
    public List<SubmittedApplication> getApprovedApplications(){
        return alcoholDB.getApproved();
    }
    
}
