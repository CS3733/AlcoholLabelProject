package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.AppState;
import com.emeraldElves.alcohollabelproject.Applicant;
import com.emeraldElves.alcohollabelproject.Log;

import java.sql.JDBCType;
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
    public static Database getDatabaseHandle(){
        return Storage.getInstance().db;
    }
    public static Schema getApplicationSchema(){
        final Schema appSchema = new Schema("SubmittedApplications",
                new Attribute("applicantID", JDBCType.INTEGER),
                new Attribute("status", JDBCType.INTEGER),
                new Attribute("statusMsg", JDBCType.VARCHAR),
                new Attribute("submissionTime", JDBCType.BIGINT),
                new Attribute("expirationDate", JDBCType.BIGINT),
                new Attribute("agentName", JDBCType.VARCHAR),
                new Attribute("approvalDate", JDBCType.BIGINT),
                new Attribute("TTBUsername", JDBCType.VARCHAR),
                new Attribute("submitterUsername", JDBCType.VARCHAR),
                new Attribute("extraInfo", JDBCType.VARCHAR),
                new Attribute("labelApproval", JDBCType.BOOLEAN),
                new Attribute("stateOnly", JDBCType.VARCHAR),
                new Attribute("bottleCapacity", JDBCType.INTEGER),
                new Attribute("imageURL", JDBCType.VARCHAR),
                new Attribute("qualifications", JDBCType.VARCHAR)
        );
        return appSchema;
    }
    public static Schema getAlcoholSchema(){
        final Schema alcoholSchema = new Schema("AlcoholInfo",
                new Attribute("applicationID", JDBCType.INTEGER),
                new Attribute("alcoholContent", JDBCType.INTEGER),
                new Attribute("fancifulName", JDBCType.VARCHAR),
                new Attribute("brandName", JDBCType.VARCHAR),
                new Attribute("origin", JDBCType.INTEGER),
                new Attribute("type", JDBCType.INTEGER),
                new Attribute("formula", JDBCType.VARCHAR),
                new Attribute("serialNumber", JDBCType.VARCHAR),
                new Attribute("pH", JDBCType.REAL),
                new Attribute("vintageYear", JDBCType.INTEGER),
                new Attribute("varietals", JDBCType.VARCHAR),
                new Attribute("wineAppellation", JDBCType.VARCHAR)
        );
        return alcoholSchema;
    }
    public static Schema getManufacturerSchema(){
        final Schema manufacturerSchema = new Schema("ManufacturerInfo",
                new Attribute("applicationID", JDBCType.INTEGER),
                new Attribute("authorizedName", JDBCType.VARCHAR),
                new Attribute("physicalAddress", JDBCType.VARCHAR),
                new Attribute("company", JDBCType.VARCHAR),
                new Attribute("representativeID", JDBCType.INTEGER),
                new Attribute("permitNum", JDBCType.INTEGER),
                new Attribute("phoneNum", JDBCType.VARCHAR),
                new Attribute("emailAddress", JDBCType.VARCHAR)
        );
        return manufacturerSchema;
    }
    /*
    public static AlcoholModel getAlcoholModel(){
        final AlcoholModel<AlcoholEntity> appModel = new AlcoholModel();
        return appModel;
    }
    */

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
                            new Database.TableField("representativeID", "INTEGER NOT NULL"),
                            new Database.TableField("permitNum", "INTEGER NOT NULL"),
                            new Database.TableField("address", "VARCHAR (255)"),
                            new Database.TableField("phoneNumber", "VARCHAR (255)"),
                            new Database.TableField("email", "VARCHAR (255) UNIQUE NOT NULL"));
            Log.console("Created new TTBAgentLogin table");
        }
        catch (SQLException e){
            Log.console("Used existing TTBAgentLogin table");
        }

        try{
            database.createTable("ApplicantLogin",
                    new Database.TableField("name", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("representativeID", "INTEGER NOT NULL"),
                    new Database.TableField("permitNum", "INTEGER NOT NULL"),
                    new Database.TableField("address", "VARCHAR (255)"),
                    new Database.TableField("phoneNumber", "VARCHAR (255)"),
                    new Database.TableField("email", "VARCHAR (255) UNIQUE NOT NULL"));
            Log.console("Created new ApplicantLogin table");
        }
        catch (SQLException e){
            Log.console("Used existing ApplicantLogin table");
        }

        try {
            database.createTable("SubmittedApplications", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
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
                    new Database.TableField("qualifications", "VARCHAR (10000)"));
            Log.console("Created new SubmittedApplications table");
        } catch (SQLException e) {
            Log.console("Used existing SubmittedApplications table");
        }

        try {
            database.createTable("ManufacturerInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("authorizedName", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("physicalAddress", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("company", "VARCHAR (10000) NOT NULL"),
                    new Database.TableField("representativeID", "INTEGER NOT NULL"),
                    new Database.TableField("permitNum", "INTEGER NOT NULL"),
                    new Database.TableField("phoneNum", "VARCHAR (255) NOT NULL"), //check with kyle
                    new Database.TableField("emailAddress", "VARCHAR (255) NOT NULL"));
            Log.console("Created new ManufacturerInfo table");
        } catch (SQLException e) {
            Log.console("Used existing ManufacturerInfo table");
        }

        try {
            database.createTable("AlcoholInfo", new Database.TableField("applicationID", "INTEGER UNIQUE NOT NULL"),
                    new Database.TableField("alcoholContent", "INTEGER NOT NULL"),
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
                    new Database.TableField("name", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("password", "VARCHAR (255) NOT NULL"),
                    new Database.TableField("type", "INTEGER NOT NULL"), //0 Man, 1 TTB
                    new Database.TableField("representativeID", "INTEGER NOT NULL"),
                    new Database.TableField("permitNum", "INTEGER NOT NULL"),
                    new Database.TableField("address", "VARCHAR (255)"),
                    new Database.TableField("phoneNumber", "VARCHAR (255)"),
                    new Database.TableField("email", "VARCHAR (255) UNIQUE NOT NULL"),
                    new Database.TableField("date", "BIGINT"));
            Log.console("Created new NewApplicant table");
        }
        catch (SQLException e){
            Log.console("Used existing NewApplicant table");
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

    public boolean approveApplication(SubmittedApplication application, String agentName, Date expirationDate) {
        return alcoholDB.approveApplication(application, agentName, expirationDate);
    }

    public boolean rejectApplication(SubmittedApplication application, String reason) {
        return alcoholDB.rejectApplication(application, reason);
    }

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
        else if( usersDB.isValidTTBAgentAccount(username)) {
            usersDB.updatePasswordApplicant(password,username);
        }
    }

    public boolean isValidUser( String username) {
        if( usersDB.isValidTTBAgentAccount(username)){
            return(true);
        }
        else if( usersDB.isValidTTBAgentAccount(username)) {
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

    public List<SubmittedApplication> getAssignedApplications(String agentName) {
        return alcoholDB.getAssignedApplications(agentName);
    }


    public List<String> getAllTTBUsernames() {
        return usersDB.getAllTTBUsernames();
    }

    public Applicant getUserFromEmail(String email) {
        Applicant applicant = usersDB.getUserFromEmail(email);
        if (applicant != null) { return applicant; }

        else return new Applicant(email,"", 0, 0, "", "");

    }

    public void modifyRepresentativeID(String email, int repID) {
        usersDB.setRepIDFromEmail(repID, email);
    }
    public void modifypermitNum(String email, int permitNum) {

    }
    public void modifyAddress(String email, String address) {

    }
    public void modifyphoneNum(String email, String phoneNum) {

    }
    public void modifyName(String email, String name) {

    }
}
