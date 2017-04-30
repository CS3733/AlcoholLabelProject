package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.SavedApplication;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kyle on 4/30/2017.
 */
public class ApplicantInterfaceTest {

    ApplicantInterface applicantInterface;

    @Before
    public void setup() {
        applicantInterface = new ApplicantInterface("Kyle", new TestStorage());

    }

    @Test
    public void testApplicant(){
        Applicant applicant = applicantInterface.getApplicant();
        assertNotNull(applicant);
    }

    @Test
    public void testApplicantSubmittedApps() {
        Applicant applicant = applicantInterface.getApplicant();
        assertEquals(1, applicant.getApplications().size());
        assertEquals(10, applicant.getApplications().get(0).getApplicationID());
    }

    @Test
    public void testApplicantSavedApps(){
        Applicant applicant = applicantInterface.getApplicant();
        List<SavedApplication> savedApplicationList = applicant.getSavedApplications();
        assertEquals(1, savedApplicationList.size());
        assertEquals(5, savedApplicationList.get(0).getApplicationID());
    }

    @Test
    public void testGetSubmittedApplications(){
        assertEquals(applicantInterface.getApplicant().getApplications(), applicantInterface.getSubmittedApplications());
    }

    @Test
    public void testGetSavedApplications(){
        assertEquals(applicantInterface.getApplicant().getSavedApplications(), applicantInterface.getSavedApplications());
    }



}
