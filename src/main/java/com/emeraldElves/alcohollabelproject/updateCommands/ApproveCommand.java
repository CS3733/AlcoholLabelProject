package com.emeraldElves.alcohollabelproject.updateCommands;

import com.emeraldElves.alcohollabelproject.Data.ApplicationEntity;
import com.emeraldElves.alcohollabelproject.EmailManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kylec on 4/16/2017.
 */
public class ApproveCommand extends ApplicationCommand {
    public ApproveCommand(ApplicationEntity application){
        super(application);
    }
    public ApproveCommand(ApplicationEntity application, Boolean sendEmail){
        super(application, sendEmail);
    }
    @Override
    public void execute() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);

        Date expDate = calendar.getTime();

        boolean approved = true;//agent.approveApplication(application, expDate); //TODO: Refactor

        if (approved && sendEmail) {
            sendEmail();
        }

    }

    private void sendEmail() {
        EmailManager.getInstance().sendEmail(application);
    }

}
