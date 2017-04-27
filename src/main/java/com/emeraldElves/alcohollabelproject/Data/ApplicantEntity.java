package com.emeraldElves.alcohollabelproject.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Essam on 4/26/2017.
 */
@Entity
@DiscriminatorValue("applicant")
public class ApplicantEntity extends AccountEntity {

    private Set<ApplicationEntity> applications;

    @OneToMany(mappedBy = "applicant")
    public Set<ApplicationEntity> getSubmittedApplications() {
        return applications;
    }
    public void setSubmittedApplications(Set<ApplicationEntity> applications){this.applications = applications;}

}
