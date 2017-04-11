package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Data.ApplicationAssigner;
import com.emeraldElves.alcohollabelproject.Data.RoundRobinAssigner;

import java.util.List;

/**
 * Created by Joe on 4/11/2017.
 */
public class MultiApplicationAssigner extends ApplicationAssigner {
    int currentApplicationCount;
    int maxAppsPerAgent;

    public MultiApplicationAssigner(List<String> agentUserNames, int numApps) {
        super(new RoundRobinAssigner(), agentUserNames);
    }

    public MultiApplicationAssigner(List<String> agentUserNames, String lastAssignedAgent, int numApps) {
        super(new RoundRobinAssigner(), agentUserNames, lastAssignedAgent);
    }
}
