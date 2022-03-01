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

@Entity
@Table(name = "tt_ijp_job_recruitment")
public class IjpJobRecruitment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ijp_recr_id")
	private Long ijpRecrId;
	
	@Column(name = "jrt_job_id")
	private Long jrtJobId;

	@Column(name = "ijp_current_role_since")
	private String ijpCurrRoleSince;

	@Column(name = "ijp_total_exp")
	private String ijpTotalExp;

	@Column(name = "ijp_resume_file_name")
	private String ijpResumeFileName;

	@Column(name = "ijp_emp_remark")
	private String ijpEmpRemark;
	
	@Column(name = "ijp_viewed_status")
	private String ijpViewedStatus;

	@Column(name = "ijp_emp_rm")
	private String ijpEmpRm;
	
	@Column(name = "ijp_status")
	private String ijpStatus;

	@Column(name = "ijp_trx_no")
	private String ijpTrxNo;

	@Column(name = "created_by", updatable = false)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", updatable = false)
	private Calendar createdOn;

	@Column(name = "updated_by")
	private String updatedBy;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on")
	private Calendar updatedOn;

	public Long getIjpRecrId() {
		return ijpRecrId;
	}

	public void setIjpRecrId(Long ijpRecrId) {
		this.ijpRecrId = ijpRecrId;
	}

	public Long getJrtJobId() {
		return jrtJobId;
	}

	public void setJrtJobId(Long jrtJobId) {
		this.jrtJobId = jrtJobId;
	}

	public String getIjpCurrRoleSince() {
		return ijpCurrRoleSince;
	}

	public void setIjpCurrRoleSince(String ijpCurrRoleSince) {
		this.ijpCurrRoleSince = ijpCurrRoleSince;
	}

	public String getIjpTotalExp() {
		return ijpTotalExp;
	}

	public void setIjpTotalExp(String ijpTotalExp) {
		this.ijpTotalExp = ijpTotalExp;
	}

	public String getIjpResumeFileName() {
		return ijpResumeFileName;
	}

	public void setIjpResumeFileName(String ijpResumeFileName) {
		this.ijpResumeFileName = ijpResumeFileName;
	}

	public String getIjpEmpRemark() {
		return ijpEmpRemark;
	}

	public void setIjpEmpRemark(String ijpEmpRemark) {
		this.ijpEmpRemark = ijpEmpRemark;
	}

	public String getIjpViewedStatus() {
		return ijpViewedStatus;
	}

	public void setIjpViewedStatus(String ijpViewedStatus) {
		this.ijpViewedStatus = ijpViewedStatus;
	}

	public String getIjpEmpRm() {
		return ijpEmpRm;
	}

	public void setIjpEmpRm(String ijpEmpRm) {
		this.ijpEmpRm = ijpEmpRm;
	}

	public String getIjpStatus() {
		return ijpStatus;
	}

	public void setIjpStatus(String ijpStatus) {
		this.ijpStatus = ijpStatus;
	}

	public String getIjpTrxNo() {
		return ijpTrxNo;
	}

	public void setIjpTrxNo(String ijpTrxNo) {
		this.ijpTrxNo = ijpTrxNo;
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

	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}

	

}
