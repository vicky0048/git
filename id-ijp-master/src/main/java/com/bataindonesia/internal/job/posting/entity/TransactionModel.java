package com.bataindonesia.internal.job.posting.entity;

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
@Entity(name = "TransactionModel")
@Table(name = "TT_TRANSACTION")
public class TransactionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TRX_NUMBER")
	private String trxNumber;
	
	@Column(name = "MODULENAME")
	private String moduleName;
	
	@Column(name = "STATUS_CODE")
	private String statusCode;
	
	@Column(name = "STATUS_NAME")
	private String statusName;
	
	@Column(name = "PENDING_WITH")
    private String pendingWith;
    
    @Column(name = "CREATED_BY")
	private String createdBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private java.util.Calendar updatedOn;

	@Column(name = "REMARKS")
	private String remarks;


	
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

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPendingWith() {
		return pendingWith;
	}

	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public java.util.Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(java.util.Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
