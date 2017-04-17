package com.emeraldElves.alcohollabelproject.updateCommands;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.TTBAgentInterface;
import com.emeraldElves.alcohollabelproject.LogManager;

/**
 * Created by Kylec on 4/16/2017.
 */
public class RejectCommand implements StatusUpdateCommand {

    private SubmittedApplication application;
    private boolean sendEmail;
    private TTBAgentInterface agent;
    private String reason;

    public RejectCommand(SubmittedApplication application, String reason, boolean sendEmail) {
        this.application = application;
        this.sendEmail = sendEmail;
        this.reason = reason;
        agent = new TTBAgentInterface(Authenticator.getInstance().getUsername());
    }

    public RejectCommand(SubmittedApplication application, String reason) {
        this(application, reason, false);
    }

    @Override
    public void execute() {
        boolean rejected = agent.rejectApplication(application, reason);

        if (rejected && sendEmail) {
            sendEmail();
        }

    }

    private void sendEmail() {
        LogManager.getInstance().logAction("RejectCommand", "Email sent");
    }

}
