package com.emeraldElves.alcohollabelproject.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Essam on 4/26/2017.
 */
@Entity
@DiscriminatorValue("super_agent")
public class SuperAgentEntity extends AccountEntity {
    /*
    @Column(name = "access_level")
    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    private int governmentId;
    @Column(name = "government_id")
    public int getGovernmentId(){return governmentId;}
    public void setGovernmentId(int governmentId){this.governmentId = governmentId;}
    */
    //
}
