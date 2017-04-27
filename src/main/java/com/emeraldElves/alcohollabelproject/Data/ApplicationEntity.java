package com.emeraldElves.alcohollabelproject.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Set;

/**
 * Created by Essam on 4/9/2017.
 */
@Entity
@Table(name = "applications")
public class ApplicationEntity extends BaseEntity {
    private String id;
    private ApplicantEntity applicant;
    private AgentEntity agent;
    private Date submissionDate = new Date();
    private int alcoholContent;
    private String fancifulName;
    private String brandName;
    private AlcoholType alcohol_type;
    private String formula;
    private double pH;
    private String serialNumber;
    private boolean domestic;
    private int vintageYear;
    private String wineAppellation;
    private String varietals;
    private ApplicationStatus status = ApplicationStatus.PROCESSING;
    private int capacity;
    private Set<FileEntity> attachments;
    private String qualifications;
    private String onlyState;
    private String TTBId;
    private String representativeId;

    @Column(name = "ttb_id")
    public String getTTBId() {
        return TTBId;
    }

    public void setTTBId(String TTBId) {
        this.TTBId = TTBId;
    }

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    public ApplicantEntity getApplicant() {
        return applicant;
    }
    public void setApplicant(ApplicantEntity applicant) {
        this.applicant = applicant;
    }

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    public AgentEntity getAgent() {
        return agent;
    }
    public void setAgent(AgentEntity agent) {
        this.agent = agent;
    }

    @Id @GeneratedValue(generator="uuid-generator")
    @GenericGenerator(name="uuid-generator", strategy="uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id;}

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public ApplicationStatus getStatus() {
        return status;
    }
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    @Transient
    public void setStatus(int status) {
        this.status = ApplicationStatus.values()[status];
    }

    @Column(name="submission_date", nullable = false)
    public Date getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Column(name="fanciful_name")
    public String getFancifulName() {
        return fancifulName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    @Column(name="brand_name", nullable = false)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(name="alcohol_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public AlcoholType getAlcoholType() {
        return alcohol_type;
    }
    public void setAlcoholType(AlcoholType alcohol_type) {
        this.alcohol_type = alcohol_type;
    }

    @Column(name="formula")
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Column(name="ph_value")
    public double getpH() {
        return pH;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    @Column(name="serial", nullable = false)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(name="domestic", nullable = false)
    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    @Column(name="vintage_year")
    public int getVintageYear() {
        return vintageYear;
    }

    public void setVintageYear(int vintageYear) {
        this.vintageYear = vintageYear;
    }

    @Column(name="wine_appellation")
    public String getWineAppellation() {
        return wineAppellation;
    }

    public void setWineAppellation(String wineAppellation) {
        this.wineAppellation = wineAppellation;
    }

    @Column(name="wine_varietals")
    public String getVarietals() {
        return varietals;
    }

    @Column(name="bottle_capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name="qualifications")
    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    @Column(name="alcohol_content")
    public int getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(int alcoholContent) {
        this.alcoholContent = alcoholContent;
    }


    @Column(name="only_state")
    public String getOnlyState() {
        return onlyState;
    }

    public void setOnlyState(String onlyState) {
        this.onlyState = onlyState;
    }

    public void setVarietals(String varietals) {
        this.varietals = varietals;
    }

    @ManyToMany
    @JoinTable(name = "application_files")
    public Set<FileEntity> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<FileEntity> attachments) {
        this.attachments = attachments;
    }

    @Column(name="representativeId")
    public String getRepresentativeId() {
        return representativeId;
    }
    public void setRepresentativeId(String representativeId) {
        this.representativeId = representativeId;
    }

}
