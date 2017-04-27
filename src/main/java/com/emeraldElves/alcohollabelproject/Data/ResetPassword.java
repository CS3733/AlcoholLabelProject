package com.emeraldElves.alcohollabelproject.Data;

import com.emeraldElves.alcohollabelproject.Authenticator;
import com.emeraldElves.alcohollabelproject.UserInterface.Main;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by keionbis on 4/21/17.
 */
public class ResetPassword {
    private String accountEmail;
    private String randomNum;
    private int minimum = 10000000, maximum = 99999999;
    private Properties props;
    private Session session;
    private StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    Main main;

    public void resetEmail(String accountEmail) {
        this.accountEmail = accountEmail;
        if (Authenticator.getInstance().isvalidAccount(accountEmail)) {
            randomNum = String.valueOf(minimum + (int) (Math.random() * maximum));

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
                            return new PasswordAuthentication("cs3733teame@gmail.com", "EmeraldElvesD17");
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("cs3733teame@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(Authenticator.getInstance().getUsername()));
                message.setSubject("Update to your recent application status");
                message.setText("Please log in using " + randomNum+
                        " as your password");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

                Storage.getInstance().updatePassword(accountEmail, passwordEncryptor.encryptPassword(String.valueOf(randomNum)));
                main.loadHomepage();

        }
    }
}

