package com.emeraldElves.alcohollabelproject.Data;

import java.io.IOException;
import java.sql.JDBCType;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Essam on 4/9/2017.
 */
public class ApplicationEntity extends Entity {
    ManufacturerEntity manufacturerEntity;
    AlcoholEntity alcoholEntity;
    public ApplicationEntity() {
        super(new ApplicationModel());
        this.manufacturerEntity = new ManufacturerModel().create();
        this.alcoholEntity = new AlcoholModel().create();
    }
    public ManufacturerEntity getManufacturerEntity(){
        return manufacturerEntity;
    }
    public AlcoholEntity getAlcoholEntity(){
        return alcoholEntity;
    }
    @Override
    public void save() throws IOException {
        getManufacturerEntity().save();
        getAlcoholEntity().save();
        super.save();
    }
    public void setApplicantId(int id){
        this.set("applicantID", id);
    }
    public void setStatusCode(int code){
        this.set("status", code);
    }
    public void setStatusMessage(String msg){this.set("statusMsg", msg);}
    public void setStatus(ApplicationStatus status){setStatusCode(status.getValue()); setStatusMessage(status.getMessage());}
    public void setAgentName(String agentName){this.set("agentName", agentName);}
    public void setTTBUsername(String username){this.set("TTBUsername", username);}
    public void setApplicantUsername(String username){this.set("submitterUsername", username);}
    public void setExtraInfo(String extrainfo){this.set("extraInfo", extrainfo);}
    public void setApproved(boolean approved){this.set("labelApproval", approved);}
    public void setImageURL(String imageURL){this.set("imageURL", imageURL);}
    public void setQualifications(String qualifications){this.set("qualifications", qualifications);}
    public void setStateOnly(String stateOnly){this.set("stateOnly", stateOnly);}
    public void setCapacity(int cap){this.set("bottleCapacity", cap);}

    public void setSubmissionTime(Long submitTime){this.set("submissionTime", submitTime);}
    public void setSubmissionDate(Date submitDate){setSubmissionTime(submitDate.getTime());}


    public void setExpirationTime(Long expirationTime){this.set("expirationDate", expirationTime);}
    public void setExpirationDate(Date expirationDate){setExpirationTime(expirationDate.getTime());}

    public void setApprovalTime(Long approvalTime){this.set("approvalDate", approvalTime);}
    public void setApprovalDate(Date approvalDate){setApprovalTime(approvalDate.getTime());}

    public int getApplicantId(){
        return this.getInt("applicantID");
    }
    public int getStatusCode(){
        return this.getInt("status");
    }
    public String getStatusMessage(){return this.getString("statusMsg");}
    public ApplicationStatus getStatus(){
        ApplicationStatus status = ApplicationStatus.fromInt(getStatusCode());
        status.setMessage(getStatusMessage());
        return status;
    }
    public String getAgentName(){return this.getString("agentName");}
    public String getTTBUsername(){return this.getString("TTBUsername");}
    public String getApplicantUsername(){return this.getString("submitterUsername");}
    public String getExtraInfo(){return this.getString("extraInfo");}
    public boolean getApproved(){return this.getBoolean("labelApproval");}
    public String getImageURL(){return this.getString("imageURL");}
    public String getQualifications(){return this.getString("qualifications");}
    public String getStateOnly(){return this.getString("stateOnly");}
    public int getCapacity(int cap){return this.getInt("bottleCapacity");}

    public long getSubmissionTime(){return this.getLong("submissionTime");}
    public Date getSubmissionDate(){return new Date(getSubmissionTime());}

    public long getExpirationTime(){return this.getLong("expirationDate");}
    public Date getExpirationDate(){return new Date(getExpirationTime());}

    public long getApprovalTime(){return this.getLong("approvalDate");}
    public Date getApprovalDate(){return new Date(getApprovalTime());}


}
