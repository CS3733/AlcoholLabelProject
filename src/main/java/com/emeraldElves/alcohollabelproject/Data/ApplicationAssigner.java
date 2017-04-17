package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Data.IAssigner;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Joe on 4/9/2017.
 */
public abstract class ApplicationAssigner {
    protected IAssigner assigner;
    private List<String> agentUserNames;
    private String lastAssignedAgent;

    // constructors
    public ApplicationAssigner(IAssigner assigner, List<String> agentUserNames) {
        ListIterator<String> itr = agentUserNames.listIterator();
        this.assigner = assigner;
        this.agentUserNames = agentUserNames;
        this.lastAssignedAgent = itr.next();
    }
    public ApplicationAssigner(IAssigner assigner, List<String> agentUserNames, String lastAssignedAgent) {
        this.assigner = assigner;
        this.agentUserNames = agentUserNames;
        this.lastAssignedAgent = lastAssignedAgent;
    }

    /**
     * assignAgent() uses round robin to assign an agent
     * @return new agent's username
     */
    String assignAgent() {
        this.lastAssignedAgent = assigner.assignAgent(agentUserNames, lastAssignedAgent);
        return lastAssignedAgent;
    }
}
