package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by Joe on 4/9/2017.
 */
public abstract class ApplicationAssigner {
    protected static IAssigner assigner;
    protected List<String> agentUserNames;
    protected String lastAssignedAgent;

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
