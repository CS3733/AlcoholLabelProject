package com.emeraldElves.alcohollabelproject.updateCommands;
import com.emeraldElves.alcohollabelproject.Data.ApplicationEntity;
import com.emeraldElves.alcohollabelproject.EmailManager;

/**
 * Created by Kylec on 4/16/2017.
 */
public class RejectCommand implements ICommand {
    boolean sendEmail = false;
    private ApplicationEntity application;
    private String reason;

    public RejectCommand(ApplicationEntity application, String reason) {
        this(application, reason, false);
    }
    public RejectCommand(ApplicationEntity application, String reason, boolean sendEmail) {
        this.application = application;
        this.sendEmail = sendEmail;
        this.reason = reason;
    }

    @Override
    public void execute() {
        //AppState.getLoggedInAccount() == application.getAgent()
        boolean rejected = false;//agent.rejectApplication(application, reason);

        if (rejected && sendEmail) {
            sendEmail();
        }

    }

    private void sendEmail() {
        EmailManager.getInstance().sendEmail(application);
    }

}
