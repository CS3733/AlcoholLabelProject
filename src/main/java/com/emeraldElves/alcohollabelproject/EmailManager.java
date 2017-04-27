package com.emeraldElves.alcohollabelproject;

import com.emeraldElves.alcohollabelproject.Data.ApplicationEntity;
import com.emeraldElves.alcohollabelproject.Data.ApplicationStatus;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Created by keionbis on 4/15/17.
 */

public class EmailManager {

    private Properties props;
    private Session session;

    public static EmailManager getInstance(){
        return EmailManagerHolder.instance;
    }

    private EmailManager(){
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("cs3733teame@gmail.com", "EmeraldElvesD17");
                    }
                });
    }

    private static class EmailManagerHolder{
        private static final EmailManager instance = new EmailManager();
    }

    public void sendEmail(ApplicationEntity application) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cs3733teame@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(application.getApplicant().getEmailAddress()));
            message.setSubject("Update to your recent application status");
            if (application.getStatus() == ApplicationStatus.REJECTED) {
                message.setText("Your application for " + application.getBrandName() + " has been " + application.getStatus() + "for the reason " + application.getStatus().toString());
            } else if (application.getStatus() == ApplicationStatus.APPROVED) {
                message.setText("Your application for " + application.getBrandName() + " has been " + application.getStatus());
            }
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}