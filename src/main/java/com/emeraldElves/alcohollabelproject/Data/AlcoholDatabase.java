package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.*;

import com.emeraldElves.alcohollabelproject.IDGenerator.ApplicationIDGenerator;
import com.emeraldElves.alcohollabelproject.IDGenerator.TTBIDGenerator;
import com.emeraldElves.alcohollabelproject.IDGenerator.TimeIDGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class AlcoholDatabase {

    private Database db;
    private AuthenticatedUsersDatabase usersDatabase;
    private ApplicationIDGenerator generator;

    /**
     * Creates an AlcoholDatabase
     *
     * @param db the main database that contains the data
     */
    public AlcoholDatabase(Database db) {
        this.db = db;
        usersDatabase = new AuthenticatedUsersDatabase(db);
        generator = new TTBIDGenerator(getAppCount());
    }

    private int getAppCount() {
        ResultSet resultSet = db.select("*", "IDGenerator", "id=1");
        try {
            if (resultSet.next()) {
                return resultSet.getInt("appCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void incrementAppCount(){
        int appCount = getAppCount() + 1;
        db.update("IDGenerator", "appCount = " + String.valueOf(appCount), "id=1");
    }

    // TODO: finish getMostRecentApproved

    /**
     * Get the most recently approved applications.
     *
     * @param numApplications The maximum number of applications to receive.
     * @return A list of the most recently approved applications ordered from most recent to least recent.
     */
    public List<SubmittedApplication> getMostRecentApproved(int numApplications) {
        ResultSet results = db.selectOrdered("*", "SubmittedApplications", "status = " + ApplicationStatus.APPROVED.getValue(), "approvalDate DESC");
        return getApplicationsFromResultSet(results, numApplications);
    }


    public boolean removeApplication(SubmittedApplication application){
        return db.delete("SubmittedApplications", "applicationID = " + application.getApplicationID());
    }

    /**
     * Get applications from a {@link ResultSet}
     *
     * @param results         The results of an database query
     * @param numApplications The number of applications to get.
     * @return A list of {@link SubmittedApplication} from the {@link ResultSet}
     */
    private List<SubmittedApplication> getApplicationsFromResultSet(ResultSet results, int numApplications) {
        List<SubmittedApplication> applications = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        try {
            while (results.next() && ids.size() < numApplications) {
                ids.add(results.getInt("applicationID"));
            }
            for (Integer id : ids) {
                applications.add(getApplicationByID(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    /**
     * Get applications from a {@link ResultSet}
     *
     * @param results The results of an database query
     * @return A list of {@link SubmittedApplication} from the {@link ResultSet}
     */
    private List<SubmittedApplication> getApplicationsFromResultSet(ResultSet results) {
        List<SubmittedApplication> applications = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();

        try {
            while (results.next()) {
                ids.add(results.getInt("applicationID"));
            }
            for (Integer id : ids) {
                applications.add(getApplicationByID(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    /**
     * Search by the brand or fanciful name of alcohol.
     *
     * @param name The name to search.
     * @return A list of approved alcohols containing the name ordered by time approved.
     */
    public List<SubmittedApplication> searchByName(String name) {
        ResultSet results = db.select("*", "AlcoholInfo", "UPPER(brandName)  LIKE UPPER('%" + name + "%') OR UPPER(fancifulName) LIKE UPPER('%" + name + "%')");

        List<SubmittedApplication> applications = getApplicationsFromResultSet(results);

        for (int i = applications.size() - 1; i >= 0; i--) {
            if (applications.get(i).getStatus() != ApplicationStatus.APPROVED)
                applications.remove(i);
        }
        return applications;
    }

    /**
     * Search by the brand name of alcohol.
     *
     * @param brandName The brand name to search.
     * @return A list of approved alcohols containing the brandName ordered by time approved.
     */
    public List<SubmittedApplication> searchByBrandName(String brandName) {
        ResultSet results = db.select("*", "AlcoholInfo", "UPPER(brandName) LIKE UPPER('%" + brandName + "%')");

        List<SubmittedApplication> applications = getApplicationsFromResultSet(results);

        for (int i = applications.size() - 1; i >= 0; i--) {
            if (applications.get(i).getStatus() != ApplicationStatus.APPROVED)
                applications.remove(i);
        }
        return applications;
    }

    /**
     * Search by the fanciful name of alcohol.
     *
     * @param fancifulName The fanciful name to search.
     * @return A list of approved alcohols containing the brandName ordered by time approved.
     */
    public List<SubmittedApplication> searchByFancifulName(String fancifulName) {
        ResultSet results = db.select("*", "AlcoholInfo", "UPPER(fancifulName) LIKE UPPER('%" + fancifulName + "%')");

        List<SubmittedApplication> applications = getApplicationsFromResultSet(results);

        for (int i = applications.size() - 1; i >= 0; i--) {
            if (applications.get(i).getStatus() != ApplicationStatus.APPROVED)
                applications.remove(i);
        }
        return applications;
    }

    /**
     * Submit an application for review.
     *
     * @param application The application to submit. If this is an update to an application, be sure to include the correct unique ID number in the application.
     * @return True if the application was submitted without error.
     */
    public boolean submitApplication(SubmittedApplication application, String username) {

        if (AppState.getInstance().ttbAgents == null) {
            AppState.getInstance().ttbAgents = new MultiApplicationAssigner(usersDatabase.getAllAgents(), 10, 0);
        }
        //making application type
        ApplicationType appType = application.getApplication().getApplicationType();

        //getting all info needed from submitted application into variables
        ApplicationStatus status = application.getStatus();
        ApplicationInfo info = application.getApplication();
        AlcoholInfo alcInfo = info.getAlcohol();
        ManufacturerInfo manInfo = info.getManufacturer();

        //Image name
        String image;
        if (application.getImage().getFileName() != null && !application.getImage().getFileName().isEmpty())
            //if(false)
            image = application.getImage().getFileName();
        else {
            image = "";
        }


        //
        boolean worked;//whether or not it added stuff to database

        int appID;

        if (application.getApplicationID() == -1) {
            appID = generateApplicationID();
            application.setApplicationID(appID);
        } else {
            appID = application.getApplicationID();
        }
        /*
        try {
            db.createTable("SubmittedApplications", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("applicantID", "INTEGER NOT NULL"),
                    new Database.TableField("status", "INTEGER NOT NULL"),
                    new Database.TableField("statusMsg", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("submissionTime", "BIGINT NOT NULL"),
                    new Database.TableField("expirationDate", "BIGINT"),
                    new Database.TableField("agentName", "VARCHAR (255)"),
                    new Database.TableField("approvalDate", "BIGINT"),
                    new Database.TableField("TTBUsername", "VARCHAR (255)"));
            Log.console("Created new SubmittedApplications table");
        } catch (SQLException e) {
            Log.console("Used existing SubmittedApplications table");
        }*/

        ResultSet resultsSubmitted = db.select("*", "SubmittedApplications", "applicationID = " + appID);

        try {
            if (resultsSubmitted.next()) {
                db.update("SubmittedApplications", "applicantID = " + manInfo.getRepresentativeID() + ", status = " //applicant ID
                        + status.getValue() + ", statusMsg = '" //status
                        + status.getMessage() + "', submissionTime = " //status message
                        + info.getSubmissionDate().getTime() + ", expirationDate = " //submission time
                        + info.getSubmissionDate().getTime() + ", agentName = '"//no field for expiration date
                        + manInfo.getName() + "', approvalDate = " //agent name
                        + info.getSubmissionDate().getTime() + ", submitterUsername = '"
                        + username + "', extraInfo = '"
                        + info.getExtraInfo() + "', labelApproval = "
                        + appType.isLabelApproval() + ", stateOnly = '"
                        + appType.getStateOnly() + "', bottleCapacity = "
                        + appType.getBottleCapacity() + ", imageURL = '"
                        + image + "'", "applicationID = "
                        + application.getApplicationID() + ", qualifications = '"
                        + info.getQualifications() + "'");


                db.update("ManufacturerInfo", "authorizedName = '"
                        + manInfo.getName() + "', physicalAddress = '" //authorized name: i assume this is just the name of the applicant???
                        + manInfo.getPhysicalAddress() + "', company = '" //physical address
                        + manInfo.getCompany() + "', representativeID = " //company
                        + manInfo.getRepresentativeID() + ", permitNum = " //representative id
                        + manInfo.getPermitNum() + ", phoneNum = '"//permit num
                        + manInfo.getPhoneNumber().getPhoneNumber() + "', emailAddress = '" //phone num. It may look stupid but it works
                        + manInfo.getEmailAddress().getEmailAddress() + "'", "applicationID = " + application.getApplicationID());

                if (alcInfo.getAlcoholType() == AlcoholType.WINE) {
                    db.update("AlcoholInfo", "alcoholContent = " + alcInfo.getAlcoholContent() + ", fancifulName = '" //alcohol content
                                    + alcInfo.getName() + "', brandName = '" //fanciful name
                                    + alcInfo.getBrandName() + "', origin = " //brand name
                                    + alcInfo.getOrigin().getValue() + ", type = " //origin: still not sure how it handles enums...
                                    + alcInfo.getAlcoholType().getValue() + ", formula = '"
                                    + alcInfo.getFormula() + "', serialNumber = '"
                                    + alcInfo.getSerialNumber() + "', pH = "
                                    + alcInfo.getWineInfo().pH + ", vintageYear = "
                                    + alcInfo.getWineInfo().vintageYear + ", varietals = '"
                                    + alcInfo.getWineInfo().grapeVarietal + "', wineAppellation = '"
                                    + alcInfo.getWineInfo().appellation + "'"
                            , "applicationID = " + application.getApplicationID());
                } else {
                    db.update("AlcoholInfo", "alcoholContent = " + alcInfo.getAlcoholContent() + ", fancifulName = '" //alcohol content
                                    + alcInfo.getName() + "', brandName = '" //fanciful name
                                    + alcInfo.getBrandName() + "', origin = " //brand name
                                    + alcInfo.getOrigin().getValue() + ", type = " //origin: still not sure how it handles enums...
                                    + alcInfo.getAlcoholType().getValue() + ", formula = '"
                                    + alcInfo.getFormula() + "', serialNumber = '"
                                    + alcInfo.getSerialNumber() + "'"
                            , "applicationID = " + application.getApplicationID());
                }
            } else {
                String assignedAgent = AppState.getInstance().ttbAgents.assignAgent();
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
                                + assignedAgent + "', '" //TTB username
                                + username + "', '" //submitter username
                                + info.getExtraInfo() + "', " //extra info
                                + appType.isLabelApproval() + ", '"
                                + appType.getStateOnly() + "', "
                                + appType.getBottleCapacity() + ", '"
                                + image + "', '"
                                + info.getQualifications() + "'"
                        //TTBUsername
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
                                    + alcInfo.getAlcoholType().getValue() + ", '" //type: I think you said you would sort this out with the 1, 2, 3 label for beer, wine, other........ :)
                                    + alcInfo.getFormula() + "', '" //formula
                                    + alcInfo.getSerialNumber() + "', "//serial number
                                    + alcInfo.getWineInfo().pH + ", " //pH: to get ph, have to call wineinfo in alcinfo. Not sure if good
                                    + alcInfo.getWineInfo().vintageYear + ", '" //vintage year: see above comment
                                    + alcInfo.getWineInfo().grapeVarietal + "', '" //grape vaietal
                                    + alcInfo.getWineInfo().appellation + "'" //appalation
                            , "AlcoholInfo");
                } else {
                    worked = db.insert(appID + ", "
                                    + alcInfo.getAlcoholContent() + ", '" //alcohol content
                                    + alcInfo.getName() + "', '" //fanciful name
                                    + alcInfo.getBrandName() + "', " //brand name
                                    + alcInfo.getOrigin().getValue() + ", " //origin: still not sure how it handles enums...
                                    + alcInfo.getAlcoholType().getValue() + ", '"
                                    + alcInfo.getFormula() + "', '" //formula
                                    + alcInfo.getSerialNumber() + "'"
                            , "AlcoholInfo (applicationID, alcoholContent, fancifulName, brandName, origin, type, formula, serialNumber)");
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


    public List<SubmittedApplication> getApplicationsByRepresentative(int representativeID) {
        ResultSet results = db.select("*", "ManufacturerInfo", "representativeID = " + representativeID);
        return getApplicationsFromResultSet(results);
    }

    public List<SubmittedApplication> getApplicationsByApplicantUsername(String username) {
        ResultSet results = db.select("*", "SubmittedApplications", "submitterUsername = '" + username + "'");
        return getApplicationsFromResultSet(results);
    }


    /**
     * Get the most recent unapproved applications.
     *
     * @param numApplications The maximum number of applications to receive.
     * @return A list of the most recent unapproved applications ordered from most recent to least recent.
     */
    public List<SubmittedApplication> getMostRecentUnapproved(int numApplications) {
        ResultSet results = db.selectOrdered("*", "SubmittedApplications", "status = " + ApplicationStatus.PENDINGREVIEW.getValue(), "submissionTime ASC");
        return getApplicationsFromResultSet(results, numApplications);
    }

    private SubmittedApplication getApplicationByID(int id) {
        ResultSet submittedResult = db.select("*", "SubmittedApplications", "applicationID = " + id);

        try {
            if (submittedResult.next()) {
                Date subDate = new Date(submittedResult.getLong("submissionTime"));

                Applicant applicant = new Applicant(null); // TODO: implement this
                ApplicationStatus status = ApplicationStatus.fromInt(submittedResult.getInt("status"));
                String message = submittedResult.getString("statusMsg");
                String extraInfo = submittedResult.getString("extraInfo");
                Boolean labelApproval = submittedResult.getBoolean("labelApproval");
                String stateOnly = submittedResult.getString("stateOnly");
                int bottleCapacity = submittedResult.getInt("bottleCapacity");
                String fileName = submittedResult.getString("imageURL");


                ManufacturerInfo manufacturerInfo = getManufacturerInfoByID(id);

                AlcoholInfo alcoholInfo = getAlcoholInfoByID(id);


                ApplicationInfo info = new ApplicationInfo(subDate, manufacturerInfo, alcoholInfo,
                        extraInfo, new ApplicationType(labelApproval, stateOnly, bottleCapacity));

                SubmittedApplication application = new SubmittedApplication(info, status, applicant);
                application.setImage(new ProxyLabelImage(fileName));
                application.setApplicationID(id);
                application.setTtbMessage(message);
                return application;
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

    public boolean approveApplication(SubmittedApplication application, String agentUsername, Date expirationDate) {
        application.setStatus(ApplicationStatus.APPROVED);
        //TODO: Fix expiration date, should be set by agent when approving application
        return db.update("SubmittedApplications", "status = " + ApplicationStatus.APPROVED.getValue() + ", TTBUsername = '" + agentUsername + "', approvalDate = " +
                +(new Date().getTime()) + ", expirationDate = " + expirationDate.getTime()
                + ", qualifications = '" + application.getApplication().getQualifications() + "'", "applicationID = " + application.getApplicationID());
    }

    public boolean rejectApplication(SubmittedApplication application, String message) {
        application.setStatus(ApplicationStatus.REJECTED);
        return db.update("SubmittedApplications", "status = " + ApplicationStatus.REJECTED.getValue() + ", statusMsg = '" + message + "'", "applicationID = " + application.getApplicationID());
    }

    public boolean changeVintageYear(SubmittedApplication application, int vintageYear) {
        if (application.getApplication().getAlcohol().getAlcoholType() != AlcoholType.WINE) {
            return false;
        }

        application.getApplication().getAlcohol().getWineInfo().vintageYear = vintageYear;

        return db.update("AlcoholInfo", "vintageYear = " + vintageYear, "applicationID = " + application.getApplicationID());
    }

    public boolean changePH(SubmittedApplication application, double pH) {
        if (application.getApplication().getAlcohol().getAlcoholType() != AlcoholType.WINE) {
            return false;
        }

        application.getApplication().getAlcohol().getWineInfo().pH = pH;

        return db.update("AlcoholInfo", "pH = " + pH, "applicationID = " + application.getApplicationID());
    }

    public boolean changeAlcoholContent(SubmittedApplication application, int alcoholContent) {
        application.getApplication().getAlcohol().setAlcoholContent(alcoholContent);

        return db.update("AlcoholInfo", "alcoholContent = " + alcoholContent, "applicationID = " + application.getApplicationID());

    }


    public SubmittedApplication getRandomApproved() {
        ResultSet alcoholResult = db.select("*", "SubmittedApplications", "status = " + ApplicationStatus.APPROVED.getValue());
        List<SubmittedApplication> applications = getApplicationsFromResultSet(alcoholResult);
        Random random = new Random();
        if (applications.isEmpty())
            return null;
        int pos = random.nextInt(applications.size());
        return applications.get(pos);
    }

    private AlcoholInfo getAlcoholInfoByID(int applicationID) {
        ResultSet alcoholResult = db.select("*", "AlcoholInfo", "applicationID = " + applicationID);
        try {
            if (alcoholResult.next()) {
                AlcoholType type = AlcoholType.fromInt(alcoholResult.getInt("type"));
                if (type == AlcoholType.WINE) {
                    return new WineInfo(alcoholResult.getInt("alcoholContent"),
                            alcoholResult.getString("fancifulName"), alcoholResult.getString("brandName"),
                            ProductSource.fromInt(alcoholResult.getInt("origin")),
                            alcoholResult.getInt("vintageYear"), (double) alcoholResult.getFloat("pH"),
                            alcoholResult.getString("varietals"), alcoholResult.getString("wineAppellation"));
                } else {
                    return new AlcoholInfo(alcoholResult.getInt("alcoholContent"),
                            alcoholResult.getString("fancifulName"), alcoholResult.getString("brandName"),
                            ProductSource.fromInt(alcoholResult.getInt("origin")), type, null,
                            alcoholResult.getString("serialNumber"), alcoholResult.getString("formula"));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public List<SubmittedApplication> getAssignedApplications(String ttbAgentUsername) {
        ResultSet results = db.selectOrdered("*", "SubmittedApplications", "status = " + ApplicationStatus.PENDINGREVIEW.getValue() + " AND TTBUsername = '" + ttbAgentUsername + "'", "submissionTime ASC");

        return getApplicationsFromResultSet(results);
    }


    private int generateApplicationID() {
        incrementAppCount();
        return Integer.valueOf(generator.generateID());
    }

    public List<SubmittedApplication> getApproved() {
        ResultSet results = db.select("*", "SubmittedApplications", "status = " + ApplicationStatus.APPROVED.getValue());
        return getApplicationsFromResultSet(results);
    }

}
