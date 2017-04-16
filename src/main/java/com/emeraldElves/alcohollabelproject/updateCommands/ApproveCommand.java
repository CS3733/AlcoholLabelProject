package com.emeraldElves.alcohollabelproject.updateCommands;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;
import com.emeraldElves.alcohollabelproject.Data.TTBAgentInterface;
import com.emeraldElves.alcohollabelproject.LogManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kylec on 4/16/2017.
 */
public class ApproveCommand implements StatusUpdateCommand {

    private SubmittedApplication application;
    private boolean sendEmail;
    private TTBAgentInterface agent;

    public ApproveCommand(SubmittedApplication application, boolean sendEmail) {
        this.application = application;
        this.sendEmail = sendEmail;
        agent = new TTBAgentInterface(Authenticator.getInstance().getUsername());
    }

    public ApproveCommand(SubmittedApplication application) {
        this(application, false);
    }

    @Override
    public void execute() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);

        Date expDate = calendar.getTime();

        boolean approved = agent.approveApplication(application, expDate);

        if (approved && sendEmail) {
            sendEmail();
        }

    }

    private void sendEmail() {
        LogManager.getInstance().logAction("ApproveCommand", "Email sent");
    }

}
