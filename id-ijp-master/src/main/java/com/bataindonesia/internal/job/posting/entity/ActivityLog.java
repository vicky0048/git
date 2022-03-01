package com.bataindonesia.internal.job.posting.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "ActivityLog")
@Table(name = "TT_ACTIVITY_LOG")
public class ActivityLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TRX_NUMBER")
	private String trxNumber;
	
	@Column(name = "OLD_STATUS_CODE")
	private String oldStatusCode;
	
	@Column(name = "OLD_STATUS_NAME")
	private String oldStatusName;
	
	@Column(name = "OUT_COME")
	private String outcome;
	
	@Column(name = "NEXT_STATUS_CODE")
    private String nextStatusCode;
    
    @Column(name = "NEXT_STATUS_NAME")
	private String nextStatusName;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITY_START_DATE", updatable = false)
	private Calendar activityStartDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVITY_END_DATE")
	private java.util.Calendar activityEndDate;

	@Column(name = "ACTIVITY_OWNER")
	private String activityOwner;
	
	@Column(name = "ACTIVITY_OWNER_NAME")
	private String activityOwnerName;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	public ActivityLog(String trxNumber,String activityOwner, String oldStatusCode, String oldStatusName,
			String nextStatusCode, String nextStatusName, String outcome) {
		super();
		this.trxNumber = trxNumber;
		this.activityOwner = activityOwner;
		this.oldStatusCode = oldStatusCode;
		this.oldStatusName = oldStatusName;
		this.nextStatusCode = nextStatusCode;
		this.nextStatusName = nextStatusName;
		this.outcome = outcome;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrxNumber() {
		return trxNumber;
	}

	public void setTrxNumber(String trxNumber) {
		this.trxNumber = trxNumber;
	}

	public String getOldStatusCode() {
		return oldStatusCode;
	}

	public void setOldStatusCode(String oldStatusCode) {
		this.oldStatusCode = oldStatusCode;
	}

	public String getOldStatusName() {
		return oldStatusName;
	}

	public void setOldStatusName(String oldStatusName) {
		this.oldStatusName = oldStatusName;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getNextStatusCode() {
		return nextStatusCode;
	}

	public void setNextStatusCode(String nextStatusCode) {
		this.nextStatusCode = nextStatusCode;
	}

	public String getNextStatusName() {
		return nextStatusName;
	}

	public void setNextStatusName(String nextStatusName) {
		this.nextStatusName = nextStatusName;
	}

	public Calendar getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(Calendar activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public java.util.Calendar getActivityEndDate() {
		return activityEndDate;
	}

	public void setActivityEndDate(java.util.Calendar activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public String getActivityOwner() {
		return activityOwner;
	}

	public void setActivityOwner(String activityOwner) {
		this.activityOwner = activityOwner;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getActivityOwnerName() {
		return activityOwnerName;
	}

	public void setActivityOwnerName(String activityOwnerName) {
		this.activityOwnerName = activityOwnerName;
	}
	
	
	
}
