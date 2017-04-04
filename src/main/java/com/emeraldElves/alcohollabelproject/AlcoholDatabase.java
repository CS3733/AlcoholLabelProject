package com.emeraldElves.alcohollabelproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class AlcoholDatabase {

    private Database db;

    /**
     * Creates an AlcoholDatabase
     *
     * @param db the main database that contains the data
     */
    public AlcoholDatabase(Database db) {
        this.db = db;
    }

    // TODO: finish getMostRecentApproved

    /**
     * Get the most recently approved applications.
     *
     * @param numApplications The maximum number of applications to receive.
     * @return A list of the most recently approved applications ordered from most recent to least recent.
     */
    public List<SubmittedApplication> getMostRecentApproved(int numApplications) {
        ResultSet results = db.selectOrdered("*", "SubmittedApplications", "status = " + ApplicationStatus.APPROVED.getValue(), "submissionTime ASC");

        List<SubmittedApplication> applications = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        try {
            while (results.next() && ids.size() < numApplications) {
                ids.add(results.getInt("applicationID"));
            }
            for (int i = 0; i < ids.size(); i++) {
                applications.add(getApplicationByID(ids.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    // TODO: finish searchByBrandName

    /**
     * Search by the brand name of alcohol.
     *
     * @param brandName The brand name to search.
     * @return A list of approved alcohols containing the brandName ordered by time approved.
     */
    public List<SubmittedApplication> searchByBrandName(String brandName) {
        ResultSet results = db.select("*", "AlcoholInfo", "brandName='" + brandName + "'");

        List<SubmittedApplication> applications = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        try {
            while (results.next()) {
                ids.add(results.getInt("applicationID"));
            }
            for (int i = 0; i < ids.size(); i++) {
                SubmittedApplication application = getApplicationByID(ids.get(i));
                if (application.getStatus() == ApplicationStatus.APPROVED)
                    applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    /**
     * Submit an application for review.
     *
     * @param application The application to submit. If this is an update to an application, be sure to include the correct unique ID number in the application.
     * @return True if the application was submitted without error.
     */
    public boolean submitApplication(SubmittedApplication application) {
        /*KYLE read this before checking my code. This is how i assume this method works:
        you add info to submittedapplications, manufacturerinfo, and alcoholinfo table.
        all of that info from the application passed in. I understood it better as i was typing
        this out, but i figured you might like to see this. Just imagine me maniacally laughing to myself at
        night. Thats how i'll type out this code.
        */

        /*IMPORTANT: I have not idea where to find the unique ID number for the application. I will just make a randomID number for each application, but
        i dont know where to associate the id number to the application. Other than that, this function should work.
        Also, I don't know where to find the approval time, expiration date, TTBUsername. Nothing in submittedapplication relating to it. hope you can figure this
        out. I'll do what i can with the rest.
        */

        //getting all info needed from submitted application into variables
        ApplicationStatus status = application.getStatus();
        ApplicationInfo info = application.getApplication();
        AlcoholInfo alcInfo = info.getAlcohol();
        ManufacturerInfo manInfo = info.getManufacturer();


        //
        boolean worked;//whether or not it added stuff to database

        int appID;

        if (application.getApplicationID() == -1) {
            appID = (int) System.currentTimeMillis(); //the unique application id for now
            application.setApplicationID(appID);
        } else {
            appID = application.getApplicationID();
        }


        ResultSet resultsSubmitted = db.select("*", "SubmittedApplications", "applicationID = " + appID);

        try {
            if (resultsSubmitted.next()) {
                return false;
            } else {
                //not in table, need to add to all 3 tables
                //SubmittedApplications
                worked = db.insert(appID + ", " //application id
                                + manInfo.getRepresentativeID() + ", " //applicant ID
                                + status.getValue() + ", '" //status
                                + status.getMessage() + "', " //status message
                                + info.getSubmissionDate().getTime() + ", " //submission time
                                + info.getSubmissionDate().getTime() + ", '"//no field for expiration date
                                + manInfo.getName() + "', " //agent name
                                + info.getSubmissionDate().getTime() + ", '" //approval date
                                + "admin1'" //TTBUsername
                        , "SubmittedApplications");

                Log.console("SubmittedApplication");

                if (!worked) {
                    return false;
                }//didn't add to at least 1 table, so return false
                //ManufacturerInfo
                worked = db.insert(appID + ", '"
                                + manInfo.getName() + "', '" //authorized name: i assume this is just the name of the applicant???
                                + manInfo.getPhysicalAddress() + "', '" //physical address
                                + manInfo.getCompany() + "', " //company
                                + manInfo.getRepresentativeID() + ", " //representative id
                                + manInfo.getPermitNum() + ", '"//permit num
                                + manInfo.getPhoneNumber().getPhoneNumber() + "', '" //phone num. It may look stupid but it works
                                + manInfo.getEmailAddress().getEmailAddress() + "'" //email
                        , "ManufacturerInfo");
                if (!worked) {
                    return false;
                }//didn't add to at least 1 table, so return false
                //AlcoholInfo


                if (alcInfo.getAlcoholType() == AlcoholType.WINE) {
                    worked = db.insert(appID + ", "
                                    + alcInfo.getAlcoholContent() + ", '" //alcohol content
                                    + alcInfo.getName() + "', '" //fanciful name
                                    + alcInfo.getBrandName() + "', " //brand name
                                    + alcInfo.getOrigin().getValue() + ", " //origin: still not sure how it handles enums...
                                    + alcInfo.getAlcoholType().getValue() + ", " //type: I think you said you would sort this out with the 1, 2, 3 label for beer, wine, other........ :)
                                    + alcInfo.getWineInfo().pH + ", " //pH: to get ph, have to call wineinfo in alcinfo. Not sure if good
                                    + alcInfo.getWineInfo().vintageYear //vintage year: see above comment
                            , "AlcoholInfo");
                } else {
                    worked = db.insert(appID + ", "
                                    + alcInfo.getAlcoholContent() + ", '" //alcohol content
                                    + alcInfo.getName() + "', '" //fanciful name
                                    + alcInfo.getBrandName() + "', " //brand name
                                    + alcInfo.getOrigin().getValue() + ", " //origin: still not sure how it handles enums...
                                    + alcInfo.getAlcoholType().getValue()
                            , "AlcoholInfo (applicationID, alcoholContent, fancifulName, brandName, origin, type)");
                }

                if (!worked) {
                    return false;
                }//didn't add to at least 1 table, so return false
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    // TODO: finish getMostRecentUnapproved

    /**
     * Get the most recent unapproved applications.
     *
     * @param numApplications The maximum number of applications to receive.
     * @return A list of the most recent unapproved applications ordered from most recent to least recent.
     */
    public List<SubmittedApplication> getMostRecentUnapproved(int numApplications) {
        ResultSet results = db.selectOrdered("*", "SubmittedApplications", "status = " + ApplicationStatus.PENDINGREVIEW.getValue(), "submissionTime ASC");

        List<SubmittedApplication> applications = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        try {
            while (results.next() && ids.size() < numApplications) {
                ids.add(results.getInt("applicationID"));
            }
            for (int i = 0; i < ids.size(); i++) {
                applications.add(getApplicationByID(ids.get(i)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    private SubmittedApplication getApplicationByID(int id) {
        ResultSet submittedResult = db.select("*", "SubmittedApplications", "applicationID = " + id);

        try {
            if (submittedResult.next()) {
                Date subDate = new Date(submittedResult.getLong("submissionTime"));

                Applicant applicant = new Applicant(null); // TODO: implement this
                ApplicationStatus status = ApplicationStatus.fromInt(submittedResult.getInt("status"));

                ManufacturerInfo manufacturerInfo = getManufacturerInfoByID(id);

                ResultSet alcoholResult = db.select("*", "AlcoholInfo", "applicationID = " + id);
                AlcoholInfo alcoholInfo = null;
                if (alcoholResult.next()) {
                    AlcoholType type = AlcoholType.fromInt(alcoholResult.getInt("type"));
                    if (type == AlcoholType.WINE) {
                        alcoholInfo = new WineInfo(alcoholResult.getInt("alcoholContent"),
                                alcoholResult.getString("fancifulName"), alcoholResult.getString("brandName"),
                                ProductSource.fromInt(alcoholResult.getInt("origin")),
                                alcoholResult.getInt("vintageYear"), alcoholResult.getInt("pH"));
                    } else {
                        alcoholInfo = new AlcoholInfo(alcoholResult.getInt("alcoholContent"),
                                alcoholResult.getString("fancifulName"), alcoholResult.getString("brandName"),
                                ProductSource.fromInt(alcoholResult.getInt("origin")), type, null);
                    }

                }

                ApplicationInfo info = new ApplicationInfo(subDate, manufacturerInfo, alcoholInfo);
                return new SubmittedApplication(info, status, applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: finish updateApplicationStatus

    /**
     * Update the approval status of the application.
     *
     * @param application The application to change the status of.
     * @param status      The new status of the application.
     * @return True if the status was updated without error.
     */
    public boolean updateApplicationStatus(SubmittedApplication application, ApplicationStatus status) {
        application.setStatus(status);
        return db.update("SubmittedApplications", "status = " + status.getValue() + ", statusMsg = '" + status.getMessage() + "'", "applicationID = " + application.getApplicationID());
    }


    private ManufacturerInfo getManufacturerInfoByID(int applicationID) {
        ResultSet results = db.select("*", "ManufacturerInfo", "applicationID = " + applicationID);
        try {
            if (results.next()) {
                String authorizedName = results.getString("authorizedName");
                String physicalAddress = results.getString("physicalAddress");
                String company = results.getString("company");
                int repID = results.getInt("representativeID");
                int permitNum = results.getInt("permitNum");
                PhoneNumber phoneNumber = new PhoneNumber(results.getString("phoneNum"));
                EmailAddress emailAddress = new EmailAddress(results.getString("emailAddress"));
                return new ManufacturerInfo(authorizedName, physicalAddress, company, repID, permitNum, phoneNumber, emailAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
