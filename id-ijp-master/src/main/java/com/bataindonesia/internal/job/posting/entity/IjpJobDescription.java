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
@Table(name = "tt_ijp_job_desc")
public class IjpJobDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "jbd_id")
	private Long jbdId;

	@Column(name = "jbd_code")
	private String jbdCode;

	@Column(name = "jbd_title")
	private String jbdTitle;

	@Column(name = "jbd_desc")
	private String jbdDesc;

	@Column(name = "jbd_year_of_exp")
	private String jbdYOExp;

	@Column(name = "jbd_desig_code")
	private String jbdDesigCode;

	@Column(name = "jbd_desig_name")
	private String jbdDesigName;

	@Column(name = "jbd_dept_code")
	private String jbdDeptCode;

	@Column(name = "jbd_dept_name")
	private String jbdDeptName;

	@Column(name = "jbd_loc_code")
	private String jbdLocCode;

	@Column(name = "jbd_loc_name")
	private String jbdLocName;

	@Column(name = "jbd_pub_frm_date", columnDefinition = "TIMESTAMP")
	private Calendar jbdPubFrmDate;

	@Column(name = "jbd_pub_to_date", columnDefinition = "TIMESTAMP")
	private Calendar jbdPubToDate;

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

	@Column(name = "jbd_no_of_vacancy")
	private String jbdNumVacancy;

	@Column(name = "other_loc_code")
	private String otherLocCode;

	@Column(name = "jbd_desc_file_name")
	private String jbdDescFileName;
	
	@Column(name = "jbd_apply_emp_categories")
	private String jbdApplyEmpCats;
	
	@Column(name = "ijp_status")
	private String ijpStatus;

	public Long getJbdId() {
		return jbdId;
	}

	public void setJbdId(Long jbdId) {
		this.jbdId = jbdId;
	}

	public String getJbdCode() {
		return jbdCode;
	}

	public void setJbdCode(String jbdCode) {
		this.jbdCode = jbdCode;
	}

	public String getJbdTitle() {
		return jbdTitle;
	}

	public void setJbdTitle(String jbdTitle) {
		this.jbdTitle = jbdTitle;
	}

	public String getJbdDesc() {
		return jbdDesc;
	}

	public void setJbdDesc(String jbdDesc) {
		this.jbdDesc = jbdDesc;
	}

	public String getJbdYOExp() {
		return jbdYOExp;
	}

	public void setJbdYOExp(String jbdYOExp) {
		this.jbdYOExp = jbdYOExp;
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

	public String getJbdNumVacancy() {
		return jbdNumVacancy;
	}

	public void setJbdNumVacancy(String jbdNumVacancy) {
		this.jbdNumVacancy = jbdNumVacancy;
	}

	public String getOtherLocCode() {
		return otherLocCode;
	}

	public void setOtherLocCode(String otherLocCode) {
		this.otherLocCode = otherLocCode;
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

	public Calendar getJbdPubFrmDate() {
		return jbdPubFrmDate;
	}

	public void setJbdPubFrmDate(Calendar jbdPubFrmDate) {
		this.jbdPubFrmDate = jbdPubFrmDate;
	}

	public Calendar getJbdPubToDate() {
		return jbdPubToDate;
	}

	public void setJbdPubToDate(Calendar jbdPubToDate) {
		this.jbdPubToDate = jbdPubToDate;
	}

	public String getJbdApplyEmpCats() {
		return jbdApplyEmpCats;
	}

	public void setJbdApplyEmpCats(String jbdApplyEmpCats) {
		this.jbdApplyEmpCats = jbdApplyEmpCats;
	}

	public String getIjpStatus() {
		return ijpStatus;
	}

	public void setIjpStatus(String ijpStatus) {
		this.ijpStatus = ijpStatus;
	}

	public String getJbdDescFileName() {
		return jbdDescFileName;
	}

	public void setJbdDescFileName(String jbdDescFileName) {
		this.jbdDescFileName = jbdDescFileName;
	}

}
