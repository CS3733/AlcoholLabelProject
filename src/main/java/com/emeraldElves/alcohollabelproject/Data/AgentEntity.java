package com.emeraldElves.alcohollabelproject.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Essam on 4/26/2017.
 */
@Entity
@DiscriminatorValue("agent")
public class AgentEntity extends AccountEntity {
    private Set<ApplicationEntity> applications;

    @OneToMany(mappedBy = "agent")
    public Set<ApplicationEntity> getAssignedApplications() {
        return applications;
    }

    public void setAssignedApplications(Set<ApplicationEntity> assignedApplications) {
        this.applications = assignedApplications;
    }
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
