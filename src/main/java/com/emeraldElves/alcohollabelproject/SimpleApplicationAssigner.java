package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by Joe on 4/9/2017.
 */
public class SimpleApplicationAssigner extends ApplicationAssigner {

    public SimpleApplicationAssigner(List<String> agentUserNames) {
        super(new RoundRobinAssigner(), agentUserNames);
    }

    public SimpleApplicationAssigner(List<String> agentUserNames, String lastAssignedAgent) {
        super(new RoundRobinAssigner(), agentUserNames, lastAssignedAgent);
    }
}

