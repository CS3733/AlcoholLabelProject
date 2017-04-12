package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.TTBAgent;

import java.util.Date;
import java.util.List;

/**
 * Created by Dan on 4/11/2017.
 */
public class TTBAgentInterface {
    private Storage storage;
    private TTBAgent agent;
    private String username;

    public TTBAgentInterface(String username){
        agent = new TTBAgent(username, (int)Math.random());
        storage = Storage.getInstance();
        this.username = username;
    }

    public boolean approveApplication(SubmittedApplication application, Date expirationDate){
        return storage.approveApplication(application, username, expirationDate);
    }

    public boolean rejectApplication(SubmittedApplication application, String reason){
        return storage.rejectApplication(application,reason);
    }

    public List<SubmittedApplication> getAssignedApplications(){
        return agent.getCurrentApplications();
    }
}
