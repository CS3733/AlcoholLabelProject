package com.emeraldElves.alcohollabelproject.updateCommands;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kylec on 4/16/2017.
 */
public class ApplicationStatusChanger {

    private List<StatusUpdateCommand> updates;

    public ApplicationStatusChanger() {
        updates = new LinkedList<>();
    }

    public void changeStatus(StatusUpdateCommand update) {
        updates.add(update);
    }

    public void commitUpdates() {
        for (StatusUpdateCommand update : updates) {
            update.execute();
        }
        updates.clear();
    }

}
