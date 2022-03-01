package com.bataindonesia.internal.job.posting.bean;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "ijp_task_list")
public class IjpListDto {
	
	@Id
	private Long ijpRecrId;
	
	private String ijpTrxNo;
	
	private String jbdTitle;

	private String jbdDesigCode;

	private String jbdDesigName;

	private String jbdDeptCode;

	private String jbdDeptName;

	private String jbdLocCode;

	private String jbdLocName;

	private String jbdYoExp;

	private String ijpTotalExp;

	private String ijpCurrentRoleSince;

	private String ijpResumeFileName;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ijpCreatedOn;

	private String fullname;

	private String empCode;

	private String contactNo;

	private String officialEmailId;

	private String empCurrentDesigName;

	private String empCurrentDeptName;

	private String empCurrentLocName;

	private String empRmName;
	
	private String empRmStatus;
	
	private String empRmRemark;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar empRmApproveDate;
	
	private String ijpApproverName;
	
	private String ijpApproverStatus;
	
	private String ijpApproverRemark;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ijpApproverDate;

	private String ijpTalentHrName;
	
	private String ijpTalentHrStatus;
	
	private String ijpTalentHrRemark;

	private String currentStatusCode;
	
	private String currentStatusName;
	
	private String pendingWith;
	
	public String getJbdTitle() {
		return jbdTitle;
	}
	public void setJbdTitle(String jbdTitle) {
		this.jbdTitle = jbdTitle;
	}
	public String getJbdDesigCode() {
		return jbdDesigCode;
	}
	public void setJbdDesigCode(String jbdDesigCode) {
		this.jbdDesigCode = jbdDesigCode;
	}
	public String getJbdDesigName() {
		return jbdDesigName;
	}
	public void setJbdDesigName(String jbdDesigName) {
		this.jbdDesigName = jbdDesigName;
	}
	public String getJbdDeptCode() {
		return jbdDeptCode;
	}
	public void setJbdDeptCode(String jbdDeptCode) {
		this.jbdDeptCode = jbdDeptCode;
	}
	public String getJbdDeptName() {
		return jbdDeptName;
	}
	public void setJbdDeptName(String jbdDeptName) {
		this.jbdDeptName = jbdDeptName;
	}
	public String getJbdLocCode() {
		return jbdLocCode;
	}
	public void setJbdLocCode(String jbdLocCode) {
		this.jbdLocCode = jbdLocCode;
	}
	public String getJbdLocName() {
		return jbdLocName;
	}
	public void setJbdLocName(String jbdLocName) {
		this.jbdLocName = jbdLocName;
	}
	
	public String getIjpTotalExp() {
		return ijpTotalExp;
	}
	public void setIjpTotalExp(String ijpTotalExp) {
		this.ijpTotalExp = ijpTotalExp;
	}
	public String getIjpCurrentRoleSince() {
		return ijpCurrentRoleSince;
	}
	public void setIjpCurrentRoleSince(String ijpCurrentRoleSince) {
		this.ijpCurrentRoleSince = ijpCurrentRoleSince;
	}
	public String getIjpResumeFileName() {
		return ijpResumeFileName;
	}
	public void setIjpResumeFileName(String ijpResumeFileName) {
		this.ijpResumeFileName = ijpResumeFileName;
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
	
	public String getOfficialEmailId() {
		return officialEmailId;
	}
	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}
	
	public String getEmpRmName() {
		return empRmName;
	}
	public void setEmpRmName(String empRmName) {
		this.empRmName = empRmName;
	}
	public String getEmpRmStatus() {
		return empRmStatus;
	}
	public void setEmpRmStatus(String empRmStatus) {
		this.empRmStatus = empRmStatus;
	}
	public String getEmpRmRemark() {
		return empRmRemark;
	}
	public void setEmpRmRemark(String empRmRemark) {
		this.empRmRemark = empRmRemark;
	}
	public String getIjpApproverName() {
		return ijpApproverName;
	}
	public void setIjpApproverName(String ijpApproverName) {
		this.ijpApproverName = ijpApproverName;
	}
	public String getIjpApproverStatus() {
		return ijpApproverStatus;
	}
	public void setIjpApproverStatus(String ijpApproverStatus) {
		this.ijpApproverStatus = ijpApproverStatus;
	}
	public String getIjpApproverRemark() {
		return ijpApproverRemark;
	}
	public void setIjpApproverRemark(String ijpApproverRemark) {
		this.ijpApproverRemark = ijpApproverRemark;
	}
	public String getIjpTalentHrName() {
		return ijpTalentHrName;
	}
	public void setIjpTalentHrName(String ijpTalentHrName) {
		this.ijpTalentHrName = ijpTalentHrName;
	}
	public String getIjpTalentHrStatus() {
		return ijpTalentHrStatus;
	}
	public void setIjpTalentHrStatus(String ijpTalentHrStatus) {
		this.ijpTalentHrStatus = ijpTalentHrStatus;
	}
	public String getIjpTalentHrRemark() {
		return ijpTalentHrRemark;
	}
	public void setIjpTalentHrRemark(String ijpTalentHrRemark) {
		this.ijpTalentHrRemark = ijpTalentHrRemark;
	}
	public String getCurrentStatusCode() {
		return currentStatusCode;
	}
	public void setCurrentStatusCode(String currentStatusCode) {
		this.currentStatusCode = currentStatusCode;
	}
	public String getCurrentStatusName() {
		return currentStatusName;
	}
	public void setCurrentStatusName(String currentStatusName) {
		this.currentStatusName = currentStatusName;
	}
	public Long getIjpRecrId() {
		return ijpRecrId;
	}
	public void setIjpRecrId(Long ijpRecrId) {
		this.ijpRecrId = ijpRecrId;
	}
	public String getJbdYoExp() {
		return jbdYoExp;
	}
	public void setJbdYoExp(String jbdYoExp) {
		this.jbdYoExp = jbdYoExp;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	public String getIjpTrxNo() {
		return ijpTrxNo;
	}
	public void setIjpTrxNo(String ijpTrxNo) {
		this.ijpTrxNo = ijpTrxNo;
	}
	public Calendar getEmpRmApproveDate() {
		return empRmApproveDate;
	}
	public void setEmpRmApproveDate(Calendar empRmApproveDate) {
		this.empRmApproveDate = empRmApproveDate;
	}
	public Calendar getIjpApproverDate() {
		return ijpApproverDate;
	}
	public void setIjpApproverDate(Calendar ijpApproverDate) {
		this.ijpApproverDate = ijpApproverDate;
	}
	public String getEmpCurrentDesigName() {
		return empCurrentDesigName;
	}
	public void setEmpCurrentDesigName(String empCurrentDesigName) {
		this.empCurrentDesigName = empCurrentDesigName;
	}
	public String getEmpCurrentDeptName() {
		return empCurrentDeptName;
	}
	public void setEmpCurrentDeptName(String empCurrentDeptName) {
		this.empCurrentDeptName = empCurrentDeptName;
	}
	public String getEmpCurrentLocName() {
		return empCurrentLocName;
	}
	public void setEmpCurrentLocName(String empCurrentLocName) {
		this.empCurrentLocName = empCurrentLocName;
	}

}
