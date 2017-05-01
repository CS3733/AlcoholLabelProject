package com.emeraldElves.alcohollabelproject.Data;

import java.util.List;

/**
 * Created by Joe on 4/11/2017.
 */
public class MultiApplicationAssigner extends ApplicationAssigner {
    int currentApplicationCount;
    int maxAppsPerAgent;

    public MultiApplicationAssigner(List<String> agentUserNames, int numApps, int currentApplicationCount) {
            this(agentUserNames, agentUserNames.get(0), numApps, currentApplicationCount);
    }

    public MultiApplicationAssigner(List<String> agentUserNames, String lastAssignedAgent, int numApps, int currentApplicationCount) {
        super(new MultiRoundRobinAssigner(currentApplicationCount, numApps), agentUserNames, lastAssignedAgent);
        System.out.println("Assigning application: " + lastAssignedAgent);
        this.maxAppsPerAgent = numApps;
        this.currentApplicationCount = currentApplicationCount;
    }
}
