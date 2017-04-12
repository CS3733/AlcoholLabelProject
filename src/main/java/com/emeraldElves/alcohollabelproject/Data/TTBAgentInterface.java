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
        agent = new TTBAgent(username, (int)Math.random());//TODO fix id
        storage = Storage.getInstance();
        agent.setCurrentApps(storage.getAssignedApplications(username));
        this.username = username;
    }

    public boolean approveApplication(SubmittedApplication application, Date expirationDate){
        agent.getCurrentApplications().remove(application);
        return storage.approveApplication(application, username, expirationDate);
    }

    public boolean rejectApplication(SubmittedApplication application, String reason){
        agent.getCurrentApplications().remove(application);
        return storage.rejectApplication(application,reason);
    }

    public List<SubmittedApplication> getAssignedApplications(){
        return agent.getCurrentApplications();
    }
}
