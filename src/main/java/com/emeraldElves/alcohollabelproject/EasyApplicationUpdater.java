package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.*;

import java.util.Date;

/**
 * Created by Wyatt on 4/18/2017.
 */
public class EasyApplicationUpdater {

    public static void main(String args[]){
        CSVReader reader = new CSVReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("labels.csv"));
        for (int i = 0; i < reader.getNumRows(); i++) {
            String brand = reader.getString("brand", i);
            String fanciful = reader.getString("fanciful", i);
            int alcoholContent = reader.getInt("alcoholContent", i);
            ProductSource origin = ProductSource.fromInt(reader.getInt("origin", i));
            AlcoholType type = AlcoholType.fromInt(reader.getInt("type", i));
            String serialNo = reader.getString("serialNo", i);
            int vintageYear = reader.getInt("vintageYear", i);
            String appelation = reader.getString("appellation", i);
            String address = reader.getString("address", i);
            PhoneNumber phoneNo = new PhoneNumber(reader.getString("phoneNo", i));
            EmailAddress email = new EmailAddress(reader.getString("email", i));


            ManufacturerInfo manufacturerInfo = new ManufacturerInfo("", address, "", (int) (Math.random() * 10000), (int)(Math.random() * 10000), phoneNo, email);
            AlcoholInfo alcoholInfo;
            if(type == AlcoholType.WINE){
                alcoholInfo = new WineInfo(alcoholContent, fanciful, brand, origin, vintageYear, 0, null, appelation);
            } else {
                alcoholInfo = new AlcoholInfo(alcoholContent, fanciful, brand, origin, type, null);
            }

            alcoholInfo.setSerialNumber(serialNo);

            ApplicationInfo applicationInfo = new ApplicationInfo(new Date(), manufacturerInfo, alcoholInfo, null, null);

            SubmittedApplication application = new SubmittedApplication(applicationInfo, ApplicationStatus.PENDINGREVIEW, null);

            Storage.getInstance().submitApplication(application, manufacturerInfo.getEmailAddress().getEmailAddress());
        }
    }

}
