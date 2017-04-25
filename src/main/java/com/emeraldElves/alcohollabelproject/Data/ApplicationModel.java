package com.emeraldElves.alcohollabelproject.Data;

/**
 * Created by Essam on 4/25/2017.
 */
public class ApplicationModel extends Model<ApplicationEntity> {
    public ApplicationModel(){
        super(Storage.getApplicationSchema());
    }

    public ApplicationEntity create(){
        return new ApplicationEntity();
    }
}
