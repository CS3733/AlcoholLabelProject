package com.emeraldElves.alcohollabelproject.UserInterface;

import com.emeraldElves.alcohollabelproject.Data.PotentialUser;
import com.emeraldElves.alcohollabelproject.Data.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dan on 4/15/2017.
 */
public class SuperUserWorkflowController implements IController {
    @FXML
    Label Name, Type, PhoneNumber, RepresentativeID, Email, Date, physicalAddress, permitNo, company;
    List<PotentialUser> users;
    PotentialUser UserforApproval;
    Main main;

    public void init(Bundle bundle){
        this.init(bundle.getMain("main"), bundle.getPotentialUser("user"));
    }

    public void init(Main main,PotentialUser UserforApproval){
        this.main = main;
        this.UserforApproval = UserforApproval;
        //add to displayed table, copy from search functionality
        Name.setText(UserforApproval.getName());
        Type.setText(UserforApproval.getUserType().toString());
        PhoneNumber.setText(UserforApproval.getPhoneNumber().getPhoneNumber());
        RepresentativeID.setText(String.valueOf(UserforApproval.getRepresentativeID()));
        Email.setText(UserforApproval.getEmail().getEmailAddress());
        Date.setText(UserforApproval.getDate().toString());
        physicalAddress.setText(UserforApproval.getAddress());
        permitNo.setText(String.valueOf(UserforApproval.getPermitNum()));
        company.setText(UserforApproval.getCompany());
    }

    public void Approve() {
        Storage.getInstance().createUser(UserforApproval);
        SendEmail(UserforApproval.getEmail().getEmailAddress(), "Approved");
        Storage.getInstance().deleteUser(UserforApproval);
        main.loadFXML("/fxml/SuperagentWorkflowPage.fxml");
        //main.loadNewUserApplicationDisplayController();
    }

    public void Reject() {
        SendEmail(UserforApproval.getEmail().getEmailAddress(), "Rejected");
        Storage.getInstance().deleteUser(UserforApproval);
        main.loadFXML("/fxml/SuperagentWorkflowPage.fxml");

    }

  

    public void printPage(){
        main.printPage();
    }

    //on click, load detailed user page
    //on accept/reject(not this controller), send it to the email address attached
    //on accept, add it the TTB agent / applicant table (method is in storage)


    public void goHome(){ main.loadFXML("/fxml/HomePage.fxml");}

    public static void SendEmail(String Email, String Status){
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
                    InternetAddress.parse(Email));
            message.setSubject("Update to your recent Account application status");
            message.setText("Your account has been " + Status);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
