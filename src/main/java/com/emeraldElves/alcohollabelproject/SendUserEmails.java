package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.ApplicationStatus;
import com.emeraldElves.alcohollabelproject.Data.SubmittedApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




/**
 * Created by keionbis on 4/15/17.
 */

public class SendUserEmails {
    SubmittedApplication application;
    public static void SendEmails(SubmittedApplication application){
        this.application = application;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("cs3733teame@gmail.com","EmeraldElvesD17");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cs3733teame@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(application.getApplication().getManufacturer().getEmailAddress().toString()));
            message.setSubject("Update to your recent application status");
            if(application.getStatus() == ApplicationStatus.REJECTED) {
                message.setText("Your application for" + application.getApplication().getAlcohol().getName().toString() + "has been " + application.getStatus() + "for the reason " + application.getStatus().getMessage());
            }
            else if (application.getStatus() == ApplicationStatus.APPROVED){
                message.setText("Your application for" + application.getApplication().getAlcohol().getName().toString() + "has been " + application.getStatus());
            }
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}