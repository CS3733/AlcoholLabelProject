package com.emeraldElves.alcohollabelproject.updateCommands;

import com.emeraldElves.alcohollabelproject.Data.ApplicationEntity;

/**
 * Created by Essam on 4/27/2017.
 */
abstract public class ApplicationCommand implements ICommand {
    ApplicationEntity application;
    boolean sendEmail = false;
    public ApplicationCommand(ApplicationEntity application){
        this(application, false);
    }
    public ApplicationCommand(ApplicationEntity application, boolean sendEmail){
        this.application = application;
        this.sendEmail = sendEmail;
    }
    protected ApplicationEntity getApplication() {return application;}
    abstract public void execute();
}
