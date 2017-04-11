package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Data.ApplicationAssigner;
import com.emeraldElves.alcohollabelproject.Data.RoundRobinAssigner;

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

