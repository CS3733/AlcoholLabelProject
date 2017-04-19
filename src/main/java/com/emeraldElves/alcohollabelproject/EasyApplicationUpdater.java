package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.*;

import java.util.Date;

/**
 * Created by Wyatt on 4/18/2017.
 */
public class EasyApplicationUpdater {

    public static void main(String args[]){
        Storage.getInstance().removeApplications();

        CSVReader reader = new CSVReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("labels.csv"));
        for (int i = 0; i < reader.getNumRows(); i++) {
            String brand = reader.getString("brand", i);
            String fanciful = reader.getString("fanciful", i);
            int alcoholContent = reader.getInt("alcoholContent", i);
            ProductSource origin = ProductSource.fromInt(reader.getInt("origin", i));
            String alcType = reader.getString("type", i);
            AlcoholType type;
            if(alcType.equalsIgnoreCase("w")){
                type = AlcoholType.WINE;
            } else {
                type = AlcoholType.BEER;
            }
            String serialNo = reader.getString("serialNo", i);
            int vintageYear = 0;
            String appellation = "";
            if(type == AlcoholType.WINE) {
                vintageYear = reader.getInt("vintageYear", i);
                appellation = reader.getString("appellation", i);
            }
            String address = reader.getString("address", i);
            PhoneNumber phoneNo = new PhoneNumber(reader.getString("phoneNo", i));
            EmailAddress email = new EmailAddress(reader.getString("email", i));
//            String img = reader.getString("img", i);


            ManufacturerInfo manufacturerInfo = new ManufacturerInfo("Test", address, "company", (int) (Math.random() * 10000), (int)(Math.random() * 10000), phoneNo, email);
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
