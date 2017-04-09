package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by Joe on 4/9/2017.
 */
public abstract class ApplicationAssigner {
    private IAssigner assigner;
    private List<String> agentUserNames;
    private String lastAssignedAgent;

    // constructors
    public ApplicationAssigner(IAssigner assigner, List<String> agentUserNames) {
        this.assigner = assigner;
        this.agentUserNames = agentUserNames;
    }
    public ApplicationAssigner(IAssigner assigner, List<String> agentUserNames, String lastAssignedAgent) {
        this.assigner = assigner;
        this.agentUserNames = agentUserNames;
        this.lastAssignedAgent = lastAssignedAgent;
    }

    String assignAgent() {
        String agent = "x";
        return agent;
    }
}
