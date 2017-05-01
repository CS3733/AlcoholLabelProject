package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.AppState;
import com.emeraldElves.alcohollabelproject.Applicant;
import com.emeraldElves.alcohollabelproject.Log;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Harry on 4/9/2017.
 */
public class Storage {
    private AlcoholDatabase alcoholDB;
    private AuthenticatedUsersDatabase usersDB;
    private Database db;

    private Storage() {
        if (!AppState.getInstance().isInTestingMode)
            db = initDatabase("ttbDB");
        else
            db = initDatabase("testDB");
        alcoholDB = new AlcoholDatabase(db);
        usersDB = new AuthenticatedUsersDatabase(db);
    }

    private Database initDatabase(String dbName) {
        Database database = new Database(dbName);
        database.connect();

        try {
            database.createTable("IDGenerator", new Database.TableField("id", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("appCount", "INTEGER NOT NULL"));
            database.insert("1, 0", "IDGenerator");
            Log.console("Created new IDGenerator table");
        } catch (SQLException e) {
            Log.console("Used existing IDGenerator table");
        }

        try {
                    database.createTable("TTBAgentLogin",
                            new Database.TableField("name", "VARCHAR (255) UNIQUE NOT NULL"),
                            new Database.TableField("password", "VARCHAR (255) NOT NULL"),
                            new Database.TableField("representativeID", "VARCHAR (255)"),
                            new Database.TableField("permitNum", "VARCHAR (255)"),
                            new Database.TableField("address", "VARCHAR (255)"),
                            new Database.TableField("phoneNumber", "VARCHAR (255)"),
                            new Database.TableField("email", "VARCHAR (255) UNIQUE NOT NULL"),
                            new Database.TableField("company", "VARCHAR (255)"));
            Log.console("Created new TTBAgentLogin table");
        }
        catch (SQLException e){
            Log.console("Used existing TTBAgentLogin table");
        }

        try{
            database.createTable("ApplicantLogin",
                    new Database.TableField("name", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("representativeID", "VARCHAR (255)"),
                    new Database.TableField("permitNum", "VARCHAR (255)"),
                    new Database.TableField("address", "VARCHAR (255)"),
                    new Database.TableField("phoneNumber", "VARCHAR (255)"),
                    new Database.TableField("email", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("company", "VARCHAR (255)"));
            Log.console("Created new ApplicantLogin table");
        }
        catch (SQLException e){
            Log.console("Used existing ApplicantLogin table");
        }

        try {
            database.createTable("SubmittedApplications", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("applicantID", "VARCHAR (255)"),
                    new Database.TableField("status", "INTEGER NOT NULL"),
                    new Database.TableField("statusMsg", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("submissionTime", "BIGINT NOT NULL"),
                    new Database.TableField("expirationDate", "BIGINT"),
                    new Database.TableField("agentName", "VARCHAR (255)"),
                    new Database.TableField("approvalDate", "BIGINT"),
                    new Database.TableField("TTBUsername", "VARCHAR (255)"),
                    new Database.TableField("submitterUsername", "VARCHAR (255)"),
                    new Database.TableField("extraInfo", "VARCHAR (1000)"),
                    new Database.TableField("labelApproval", "BOOLEAN"),
                    new Database.TableField("stateOnly", "VARCHAR (2)"),
                    new Database.TableField("bottleCapacity", "INTEGER"),
                    new Database.TableField("imageURL", "VARCHAR (255)"),
                    new Database.TableField("qualifications", "VARCHAR (10000)"),
                    new Database.TableField("classTypeCode", "INTEGER"),
                    new Database.TableField("applType", "VARCHAR (255)"), // always cola
                    new Database.TableField("specialDesc", "VARCHAR (255)"), // always .
                    new Database.TableField("issueDate", "BIGINT"),
                    new Database.TableField("surrenderedDate", "BIGINT"),
                    new Database.TableField("recievedCode", "VARCHAR (255)"));
            Log.console("Created new SubmittedApplications table");
        } catch (SQLException e) {
            Log.console("Used existing SubmittedApplications table");
        }

        try {
            database.createTable("ManufacturerInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("authorizedName", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("physicalAddress", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("company", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("representativeID", "VARCHAR (255)"),
                    new Database.TableField("permitNum", "VARCHAR (255)"),
                    new Database.TableField("phoneNum", "VARCHAR (255) NOT NULL"), //check with kyle
                    new Database.TableField("emailAddress", "VARCHAR (255) NOT NULL"));
            Log.console("Created new ManufacturerInfo table");
        } catch (SQLException e) {
            Log.console("Used existing ManufacturerInfo table");
        }

        try {
            database.createTable("AlcoholInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("alcoholContent", "VARCHAR (255)"),
                    new Database.TableField("fancifulName", "VARCHAR (255)"),
                    new Database.TableField("brandName", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("origin", "INTEGER NOT NULL"),
                    new Database.TableField("type", "INTEGER NOT NULL"),
                    new Database.TableField("formula", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("serialNumber", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("pH", "REAL"),
                    new Database.TableField("vintageYear", "INTEGER"),
                    new Database.TableField("varietals", "VARCHAR (255)"),
                    new Database.TableField("wineAppellation", "VARCHAR (255)"));
            Log.console("Created new AlcoholInfo table");
        } catch (SQLException e) {
            Log.console("Used existing AlcoholInfo table");
        }
        try{
            database.createTable("NewApplicant",
                    new Database.TableField("name", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("type", "INTEGER NOT NULL"), //0 Man, 1 TTB
                    new Database.TableField("representativeID", "VARCHAR (255)"),
                    new Database.TableField("permitNum", "VARCHAR (255)"),
                    new Database.TableField("address", "VARCHAR (255)"),
                    new Database.TableField("phoneNumber", "VARCHAR (255)"),
                    new Database.TableField("email", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("date", "BIGINT"),
                    new Database.TableField("company", "VARCHAR (255)"));
            Log.console("Created new NewApplicant table");
        }
        catch (SQLException e){
            Log.console("Used existing NewApplicant table");
        }

        try{
            database.createTable("SavedApplications",
                new Database.TableField("labelApproval", "BOOLEAN"),
                new Database.TableField("stateOnly", "VARCHAR (2)"),
                new Database.TableField("bottleCapacity", "INTEGER"),
                new Database.TableField("origin", "INTEGER"),//Domestic or Imported
                new Database.TableField("type", "INTEGER"),//BEER, WINE or SPIRITS
                new Database.TableField("fancifulName", "VARCHAR (255)"),
                new Database.TableField("brandName", "VARCHAR (255)"),
                new Database.TableField("alcoholContent", "DOUBLE"),
                new Database.TableField("formula", "VARCHAR (255)"),
                new Database.TableField("serialNumber", "VARCHAR (255)"),
                new Database.TableField("pH", "REAL"),
                new Database.TableField("vintageYear", "INTEGER"),
                new Database.TableField("varietals", "VARCHAR (255)"),
                new Database.TableField("wineAppellation", "VARCHAR (255)"),
                new Database.TableField("extraInfo", "VARCHAR (255)"),
                new Database.TableField("imageURL", "VARCHAR (255)"),
                new Database.TableField("submitterUsername", "VARCHAR (255)"),
                new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"));

        }
        catch (SQLException e){
            Log.console("Used existing SavedApplications table");
        }
        try {
            database.createTable("HistoricalSubmittedApplications", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("applicantID", "INTEGER NOT NULL"),
                    new Database.TableField("status", "INTEGER NOT NULL"),
                    new Database.TableField("statusMsg", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("submissionTime", "BIGINT NOT NULL"),
                    new Database.TableField("expirationDate", "BIGINT"),
                    new Database.TableField("agentName", "VARCHAR (255)"),
                    new Database.TableField("approvalDate", "BIGINT"),
                    new Database.TableField("TTBUsername", "VARCHAR (255)"),
                    new Database.TableField("submitterUsername", "VARCHAR (255)"),
                    new Database.TableField("extraInfo", "VARCHAR (1000)"),
                    new Database.TableField("labelApproval", "BOOLEAN"),
                    new Database.TableField("stateOnly", "VARCHAR (2)"),
                    new Database.TableField("bottleCapacity", "INTEGER"),
                    new Database.TableField("imageURL", "VARCHAR (255)"),
                    new Database.TableField("qualifications", "VARCHAR (10000)"),
                    new Database.TableField("classTypeCode", "INTEGER"),
                    new Database.TableField("applType", "VARCHAR (255)"), // always cola
                    new Database.TableField("specialDesc", "VARCHAR (255)"), // always .
                    new Database.TableField("issueDate", "BIGINT"),
                    new Database.TableField("surrenderedDate", "BIGINT"),
                    new Database.TableField("recievedCode", "VARCHAR (255)"));
            Log.console("Created new HistorySubmittedApplications table");
        } catch (SQLException e) {
            Log.console("Used existing HistorySubmittedApplications table");
        }

        try {
            database.createTable("HistoryAlcoholInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("alcoholContent", "DOUBLE NOT NULL"),
                    new Database.TableField("fancifulName", "VARCHAR (255)"),
                    new Database.TableField("brandName", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("origin", "INTEGER NOT NULL"),
                    new Database.TableField("type", "INTEGER NOT NULL"),
                    new Database.TableField("formula", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("serialNumber", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("pH", "REAL"),
                    new Database.TableField("vintageYear", "INTEGER"),
                    new Database.TableField("varietals", "VARCHAR (255)"),
                    new Database.TableField("wineAppellation", "VARCHAR (255)"));
            Log.console("Created new HistoryAlcoholInfo table");
        } catch (SQLException e) {
            Log.console("Used existing HistoryAlcoholInfo table");
        }


        return database;
    }


    public static Storage getInstance() {
        return StorageHolder.instance;
    }


    private static class StorageHolder {
        public static final Storage instance = new Storage();
    }


    public boolean submitApplication(SubmittedApplication application, String username) {
        return alcoholDB.submitApplication(application, username);
    }

    public String getAgentPassword(String username){
        return usersDB.getAgentPassword(username);
    }
    public String getUserPassword(String username){
        return usersDB.getUserPassword(username);
    }


    public boolean updateApplication(SubmittedApplication application, String username) {
        return alcoholDB.updateApplication(application, username);
    }


    public boolean approveApplication(SubmittedApplication application, String agentName, Date expirationDate) {
        return alcoholDB.approveApplication(application, agentName, expirationDate);
    }

    public boolean rejectApplication(SubmittedApplication application, String reason) {
        return alcoholDB.rejectApplication(application, reason);
    }

    public boolean removeSavedApplication(SavedApplication application){
        return alcoholDB.removeSavedApplication(application);
    }

    /**
     * Adds application to the given agent
     * @param application Application to add
     * @param agentUsername Agent to get application added to
     * @return Whether or not application was added succesfully
     */
    public boolean addApplication(SubmittedApplication application, String agentUsername){


       return alcoholDB.addApplication(application, agentUsername);
    }
    /*
    /**
     * Removes application from given agent
     * @param application Application to add
     * @param agentUsername Agent to remove application from
     * @return Whether or not application was removed successfully

    public boolean removeApplication(SubmittedApplication application, String agentUsername){
        return alcoholDB.removeApplication(application, agentUsername);
    }
    */


    public boolean createUser(PotentialUser user) {
        return usersDB.createUser(user);
    }

    public void deleteUser(PotentialUser potentialUser) {
        db.delete("NewApplicant","email = '" + potentialUser.getEmail().getEmailAddress() + "'");
    }
//    public PotentialUser getUserFromEmail(String email){
//       return usersDB.getUserFromEmail(email);
//    }

    public boolean applyForUser(PotentialUser user){
        usersDB.addPotentialUser(user);
        //call superuserworkflow controller
        return true;
    }

    public List<PotentialUser> getPotentialUsers(){ return usersDB.getPotentialUsers();  }

    /**
     * calls the saveApplication function in the alcohol database
     * @param application The application to save
     * @param username The username of the person saving the application
     * @return Whether or not the application was successfully saved
     */
    public boolean saveApplication(SavedApplication application, String username){
        return alcoholDB.saveApplication(application,username);
    }

    public boolean saveUpdateHistory(SubmittedApplication application, String username){
        return alcoholDB.saveUpdateHistory(application,username);
    }

    /**
     *
     * @param usertype The type of user
     * @param username The email of the user
     * @param password The password of the user
     * @return Whether or not it is a valid user
     */
    public boolean isValidUser(UserType usertype, String username, String password) {
        if (usertype == UserType.TTBAGENT) {
            return usersDB.isValidTTBAgent(username, password);
        } else if (usertype == UserType.APPLICANT) {
            return usersDB.isValidApplicant(username, password);
        }else if(usertype == UserType.SUPERAGENT){
            return usersDB.isValidSuperUser(username,password);
        }
        return false;
    }

    public void updatePassword(String username, String password){
        if( usersDB.isValidTTBAgentAccount(username)){
            usersDB.updatePasswordTTBAgent(password,username);
        }
        else if( usersDB.isValidUserAccount(username)) {
           usersDB.updatePasswordApplicant(password,username);
       }
    }

    public boolean isValidUser( String username) {
        return usersDB.isValidTTBAgentAccount(username)||(usersDB.isValidUserAccount(username));
//            return(true);
//        }
//        return false;
    }
    public boolean isCurrentNewApplicant( String username) {
        return usersDB.isCurrentNewApplicant(username);
//            return(true);
//        }
//        return false;
    }

    public boolean isValidAgent(String username){
        if( usersDB.isValidTTBAgent(username,"")) {
            return (true);
        }
        return false;
    }
    public boolean isValidApplicant(String username){
        if( usersDB.isValidAccount(username,"")) {
            return (true);
        }
        return false;
    }
    public boolean isValidUser( String username, String password) {
        if( usersDB.isValidAccount(username, password)){
            return(true);
        }
        else if( usersDB.isValidTTBAgent(username, password)) {
            return (true);
        }
        return false;
    }

    public List<SubmittedApplication> getRecentlyApprovedApplications(int numApps) {
        return alcoholDB.getMostRecentApproved(numApps);
    }

    public List<SubmittedApplication> getApprovedApplications() {
        return alcoholDB.getApproved();
    }

    public List<SubmittedApplication> getApplicationsByBrandName(String brandName) {
        return alcoholDB.searchByBrandName(brandName);
    }

    public List<SubmittedApplication> getApplicationsByName(String name) {
        return alcoholDB.searchByName(name);
    }

    public List<SubmittedApplication> getApplicationsByFancifulName(String fancifulName) {
        return alcoholDB.searchByFancifulName(fancifulName);
    }

    public List<SubmittedApplication> getApplicationsByApplicant(String username) {
        return alcoholDB.getApplicationsByApplicantUsername(username);
    }

    public List<SavedApplication> getSavedApplicationsByApplicant(String username){
        return alcoholDB.getSavedApplicationsByApplicant(username);
    }

    public List<SubmittedApplication> getAssignedApplications(String agentName) {
        return alcoholDB.getAssignedApplications(agentName);
    }


    public List<String> getAllTTBUsernames() {
        return usersDB.getAllTTBUsernames();
    }

    public Applicant getUserFromEmail(String email) {
        Applicant applicant = usersDB.getUserFromEmail(email);
        if (applicant != null) { return applicant; }

        else return new Applicant(email,"", "0", "", "", "", "");


    }

    public void modifyRepresentativeID(String email, String repID) {
        usersDB.setRepIDFromEmail(repID, email);
    }

    public void modifypermitNum(String email, String permitNum) {
        usersDB.setPermitNumFromEmail(permitNum, email);
    }
    public void modifyAddress(String email, String address) {
        usersDB.setAddressFromEmail(address, email);
    }
    public void modifyphoneNum(String email, String phoneNum) {
        usersDB.setPhoneNumFromEmail(phoneNum, email);
    }
    public void modifyName(String email, String name) {
        usersDB.setNameFromEmail(name, email);
    }
    public void modifyCompany(String email, String company) {
        usersDB.setCompanyFromEmail(company, email);
    }

    public List<SubmittedApplication> getUnassignedApplications(){
        return alcoholDB.getUnassignedApplications();
    }
}
