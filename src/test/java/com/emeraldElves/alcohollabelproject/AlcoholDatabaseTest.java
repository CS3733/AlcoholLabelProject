package com.emeraldElves.alcohollabelproject;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

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
        alcoholDatabase = new AlcoholDatabase(db);
    }
    @Test
    public void testSubmitApplication(){
        //AlcoholInfo alc = new AlcoholInfo()
    }

    @Test
    public void testUnapproved(){
        assertTrue(alcoholDatabase.getMostRecentUnapproved(10).isEmpty());
    }


}
