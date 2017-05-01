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

        CSVReader reader = new CSVReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("labels.csv"));
        for (int i = 0; i < reader.getNumRows(); i++) {
            Long applicationID = reader.getLong("CFM_APPL_ID", i);
            String repID = reader.getString("REP_ID", i);
            Integer classTypeCode = 0;
            classTypeCode = reader.getInt("CLASS_TYPE_CODE", i);
            String originCode = reader.getString("ORIGIN_CODE", i);
            String permitID = reader.getString("PERMIT_ID", i);
            ProductSource productSource = ProductSource.fromInt(reader.getInt("SOURCE_OF_PRODUCT", i));
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
            String brandName = reader.getString("PRODUCT_NAME", i);
            String fancifulName = reader.getString("FANCIFUL_NAME", i);
            String permitName = reader.getString("PERMIT_NAME", i);
            String permitAddress = reader.getString("addresscomp", i);
            String varietal = reader.getString("VARTL_NAME", i);
            String alcoholContent = reader.getString("ALCOHOL_PCT", i);
            int vintageYear = 0;
            String appellation = "";
            if(type == AlcoholType.WINE) {
                vintageYear = reader.getInt("VINTAGE", i);
                appellation = reader.getString("APPELLATION_DESC", i);
            }
            BigInteger completedDate = reader.getBigint("COMPLETED_DATE", i);
            BigInteger issuedDate = reader.getBigint("ISSUED_DATE", i);
            BigInteger receivedDate = reader.getBigint("RECEIVED_DATE", i);
            ApplicationStatus appStatus;
            appStatus = ApplicationStatus.fromInt(0);
            String qual = reader.getString("QUALIFICATION", i);


            ManufacturerInfo manufacturerInfo = new ManufacturerInfo(permitName, permitAddress, brandName, repID), (int)(Math.random() * 10000), phoneNo, email);
            AlcoholInfo alcoholInfo;
            if(type == AlcoholType.WINE){
                alcoholInfo = new WineInfo(alcoholContent, fanciful, brand, origin, vintageYear, 0, "", appellation);
            } else {
                alcoholInfo = new AlcoholInfo(alcoholContent, fanciful, brand, origin, type, null);
            }

            alcoholInfo.setSerialNumber(serialNo);
            alcoholInfo.setFormula("");

            ApplicationInfo applicationInfo = new ApplicationInfo(new Date(), manufacturerInfo, alcoholInfo, "", new ApplicationType(true, "", 0));

            SubmittedApplication application = new SubmittedApplication(applicationInfo, ApplicationStatus.PENDINGREVIEW, null);

           // application.setImage(new ProxyLabelImage(img));

            application.getApplication().setQualifications("");


            Storage.getInstance().submitApplication(application, manufacturerInfo.getEmailAddress().getEmailAddress());
        }
    }

}
