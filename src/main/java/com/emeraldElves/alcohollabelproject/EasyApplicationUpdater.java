package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.SplittableRandom;

/**
 * Created by Wyatt on 4/18/2017.
 */
public class EasyApplicationUpdater {

    public static void main(String args[]){
        Storage.getInstance().removeApplications();

        CSVReader reader = new CSVReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("ttbsample.csv"));
        for (int i = 0; i < reader.getNumRows() && i < 1000; i++) {
            long applicationID = -1;
            try {
                applicationID = reader.getLong("CFM_APPL_ID", i);
            } catch(Exception e){}
            System.out.println(applicationID);
            String repID = reader.getString("REP_ID", i);
            int classTypeCode = 0;
            classTypeCode = reader.getInt("CLASS_TYPE_CODE", i);
            String originCode = reader.getString("ORIGIN_CODE", i);
            String permitID = reader.getString("PERMIT_ID", i);

            ProductSource source = ProductSource.DOMESTIC;
            String origin = reader.getString("SOURCE_OF_PRODUCT", i);
            if(origin.equalsIgnoreCase("import")){
                source = ProductSource.IMPORTED;
            }

            String serialNum = reader.getString("SERIAL_NUM", i);
            String alcType = reader.getString("PRODUCT_TYPE", i);
            AlcoholType type;
            if(alcType.equalsIgnoreCase("Wine")){
                type = AlcoholType.WINE;
            }
            else if(alcType.equalsIgnoreCase("Malt Beverage")){
                type = AlcoholType.BEER;
            }
            else {
                type = AlcoholType.DISTILLEDSPIRITS;
            }
            String brandName = reader.getString("PRODUCT_NAME", i).replace("\"", "");
            String fancifulName = reader.getString("FANCIFUL_NAME", i).replace("\"", "");
            String permitName = reader.getString("PERMIT_NAME", i);
            String permitAddress = reader.getString("PERMIT_FRST_STRT_ADDR", i);
            String permitState = reader.getString("PERMIT_STATE_ADDR", i);
            String zip = reader.getString("PERMIT_ZIP_ADDR", i);
            String permitCity = reader.getString("PERMIT_CITY_ADDR", i);
            permitAddress += ", " + permitCity + ", " + permitState + " " + zip;
            String varietal = reader.getString("VARTL_NAME", i);
            String alcoholContent = reader.getString("ALCOHOL_PCT", i);
            int vintageYear = 0;
            String appellation = "";
            if(type == AlcoholType.WINE) {
                try {
                    vintageYear = reader.getInt("VINTAGE", i);
                } catch (Exception e){
                    vintageYear = 0;
                }
                appellation = reader.getString("APPELLATION_DESC", i);
            }
//            long completedDate = reader.getLong("COMPLETED_DATE", i);
//            long issuedDate = reader.getLong("ISSUED_DATE", i);
//            long receivedDate = reader.getLong("RECEIVED_DATE", i);

            int receivedMonth = reader.getInt("RECEIVED_DATE_MONTH", i);
            int receivedDay = reader.getInt("RECEIVED_DATE_DAY", i);
            int receivedYear = reader.getInt("RECEIVED_DATE_YEAR", i);


//            ApplicationStatus appStatus;
//            appStatus = ApplicationStatus.fromInt(0);
            String qual = reader.getString("QUALIFICATION", i);


            ManufacturerInfo manufacturerInfo = new ManufacturerInfo(permitName, permitAddress, brandName, repID, permitID, new PhoneNumber(""), new EmailAddress("asfdsaf@fsadf.com"));
            AlcoholInfo alcoholInfo;
            if(type == AlcoholType.WINE){
                alcoholInfo = new WineInfo(alcoholContent, fancifulName, brandName, source, vintageYear, 0, "", appellation);
            } else {
                alcoholInfo = new AlcoholInfo(alcoholContent, fancifulName, brandName, source, type, null);
            }

            alcoholInfo.setSerialNumber(serialNum);
            alcoholInfo.setFormula("");
            Date submittedDate = DateHelper.getDate(receivedDay, receivedMonth, receivedYear);
            ApplicationInfo applicationInfo = new ApplicationInfo(submittedDate, manufacturerInfo, alcoholInfo, "", new ApplicationType(true, "", -1));

            SubmittedApplication application = new SubmittedApplication(applicationInfo, ApplicationStatus.APPROVED, null);

            application.setImage(new ProxyLabelImage(""));

            application.getApplication().setQualifications(qual);
            application.setApplicationID(applicationID);

            Log.console("Submitted");
            Storage.getInstance().submitApplication(application, manufacturerInfo.getEmailAddress().getEmailAddress());
        }
    }

}
