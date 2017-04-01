package com.emeraldElves.alcohollabelproject;

import java.util.List;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class AlcoholDatabase {
    /**
     * @param numApplications
     * @return
     */
    public List<ApplicationInfo> getMostRecentApproved(int numApplications) {
        return null;
    }

    /**
     * @param brandName
     * @return
     */
    public List<ApplicationInfo> searchByBrandName(String brandName) {
        return null;
    }

    public boolean submitApplication(ApplicationInfo application) {
        return false;
    }

    public List<ApplicationInfo> getMostRecentUnapproved(int numApplications) {
        return null;
    }

    public boolean updateApplicationStatus(ApplicationInfo application, ApplicationStatus status) {
        return false;
    }
}
