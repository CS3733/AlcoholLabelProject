package com.emeraldElves.alcohollabelproject.Data;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.mindrot.jbcrypt.BCrypt;
import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Essam on 4/26/2017.
 */
@Entity
@Table(name = "accounts")
@DiscriminatorColumn(name="type")
//@Inheritance(strategy = InheritanceType.JOINED)
public class AccountEntity extends BaseEntity {

    @Transient
    private static int PASSWORD_WORKLOAD = 10;

    private String id;
    private String username;
    private String password;
    private AccountStatus status = AccountStatus.INACTIVE;
    private String emailAddress;
    private String phoneNumber;

    @Id @GeneratedValue(generator="uuid-generator")
    @GenericGenerator(name="uuid-generator", strategy="uuid")
    public String getId() {
        return id;
    }

    @Column(name="username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Column(name="password", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Column(name="email_address")
    public String getEmailAddress(){return emailAddress;}

    @Column(name="phone_number")
    public String getPhoneNumber(){return phoneNumber;}

    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setEmailAddress(String emailAddress){
        //if (EmailAddress.isValid(emailAddress)
        this.emailAddress = emailAddress;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Transient
    public void setPlainTextPassword(String plaintext_password){
        setPassword(BCrypt.hashpw(plaintext_password, BCrypt.gensalt(10)));
    }
    @Transient
    public boolean checkPassword(String plaintext_password){
        return BCrypt.checkpw(plaintext_password, getPassword());
    }
}
