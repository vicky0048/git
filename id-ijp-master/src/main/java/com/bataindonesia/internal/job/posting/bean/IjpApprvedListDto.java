package com.bataindonesia.internal.job.posting.bean;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class IjpApprvedListDto {

	private Long ijpRecrId;
	
	private String ijpTrxNo;
	
	private String jbdTitle;

	private String jbdDesigName;

	private String jbdDeptName;

	private String jbdLocName;
	
	private String fullname;
	
	private String currentStatusName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ijpCreatedOn;
	
	private String ijpResumeFileName;
	
	private String empCode;

	public Long getIjpRecrId() {
		return ijpRecrId;
	}

	public void setIjpRecrId(Long ijpRecrId) {
		this.ijpRecrId = ijpRecrId;
	}

	public String getIjpTrxNo() {
		return ijpTrxNo;
	}

	public void setIjpTrxNo(String ijpTrxNo) {
		this.ijpTrxNo = ijpTrxNo;
	}

	public String getJbdTitle() {
		return jbdTitle;
	}

	public void setJbdTitle(String jbdTitle) {
		this.jbdTitle = jbdTitle;
	}

	public String getJbdDesigName() {
		return jbdDesigName;
	}

	public void setJbdDesigName(String jbdDesigName) {
		this.jbdDesigName = jbdDesigName;
	}

	public String getJbdDeptName() {
		return jbdDeptName;
	}

	public void setJbdDeptName(String jbdDeptName) {
		this.jbdDeptName = jbdDeptName;
	}

	public String getJbdLocName() {
		return jbdLocName;
	}

	public void setJbdLocName(String jbdLocName) {
		this.jbdLocName = jbdLocName;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCurrentStatusName() {
		return currentStatusName;
	}

	public void setCurrentStatusName(String currentStatusName) {
		this.currentStatusName = currentStatusName;
	}

	public Calendar getIjpCreatedOn() {
		return ijpCreatedOn;
	}

	public void setIjpCreatedOn(Calendar ijpCreatedOn) {
		this.ijpCreatedOn = ijpCreatedOn;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getIjpResumeFileName() {
		return ijpResumeFileName;
	}

	public void setIjpResumeFileName(String ijpResumeFileName) {
		this.ijpResumeFileName = ijpResumeFileName;
	}

}
