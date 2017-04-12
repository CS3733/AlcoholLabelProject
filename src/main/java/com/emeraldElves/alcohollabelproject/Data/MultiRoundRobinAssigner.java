package com.emeraldElves.alcohollabelproject.Data;

import java.util.List;

/**
 * Created by Joe on 4/11/2017.
 */
public class MultiRoundRobinAssigner implements IAssigner {
    private int currentApplicationCount;
    private int maxAppsPerAgent;

    public MultiRoundRobinAssigner(int currentApplicationCount, int maxAppsPerAgent) {
        this.currentApplicationCount = currentApplicationCount;
        this.maxAppsPerAgent = maxAppsPerAgent;
    }

    public String assignAgent(List<String> agentUserNames, String lastAssignedAgent) {
        RoundRobin<String> roundRobin = new RoundRobin(agentUserNames);

        if (currentApplicationCount < maxAppsPerAgent) {
            currentApplicationCount++;
            return lastAssignedAgent;
        }
        else {
            int pos = roundRobin.find(lastAssignedAgent);
            roundRobin.setPosition(pos);
            roundRobin.next();
            currentApplicationCount = 1;
            return roundRobin.next();
        }
    }
}
