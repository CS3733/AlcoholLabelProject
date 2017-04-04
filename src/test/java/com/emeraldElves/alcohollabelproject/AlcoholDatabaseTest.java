package com.emeraldElves.alcohollabelproject;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by elijaheldredge on 3/31/17.
 */
public class AlcoholDatabaseTest {
    AlcoholDatabase alcoholDatabase;
    Database db;

    @Before
    public void setup() {
        db = DatabaseController.getInstance().initDatabase("testDB");
        try {
            db.dropTable("SubmittedApplications");
            db.dropTable("ManufacturerInfo");
            db.dropTable("AlcoholInfo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
        db = DatabaseController.getInstance().initDatabase("testDB");
        alcoholDatabase = new AlcoholDatabase(db);
    }
    @Test
    public void testSubmitApplication(){
        AlcoholInfo alc = new AlcoholInfo(5,"name","brand", ProductSource.DOMESTIC
        , AlcoholType.BEER, null); //alcohol info for test
        Date subDate = new Date(1997, 5, 20);
        ManufacturerInfo man = new ManufacturerInfo("dan", "WPI", "Dell-EMC", 69, 96, new PhoneNumber("5088888888"),
                new EmailAddress("dbmckay@wpi.edu"));//manufacturer info for test

        ApplicationInfo appInfo = new ApplicationInfo(subDate, man, alc);

        Applicant applicant = new Applicant(null);

        SubmittedApplication test = new SubmittedApplication(appInfo, ApplicationStatus.PENDINGREVIEW, applicant);
        assertTrue(alcoholDatabase.submitApplication(test));
    }

    @Test
    public void testUnapproved(){
        assertTrue(alcoholDatabase.getMostRecentUnapproved(10).isEmpty());
    }


}
