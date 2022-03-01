package com.bataindonesia.internal.job.posting.bean;

import java.util.Calendar;

public class IjpJobRecrSearchDTO {

	private String ijpTrxNo;
	
	private String jbdTitle;
	
	private String fullname;
	
	private String currentStatusName;
	
	private Calendar ijpCreatedOnFromDate;
	
	private Calendar ijpCreatedOnToDate;
	
	private String empCode;
	
	private String pendingWith;
	
	private int pageNo;

	private int pageSize;

	private String sortBy;

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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Calendar getIjpCreatedOnFromDate() {
		return ijpCreatedOnFromDate;
	}

	public void setIjpCreatedOnFromDate(Calendar ijpCreatedOnFromDate) {
		this.ijpCreatedOnFromDate = ijpCreatedOnFromDate;
	}

	public Calendar getIjpCreatedOnToDate() {
		return ijpCreatedOnToDate;
	}

	public void setIjpCreatedOnToDate(Calendar ijpCreatedOnToDate) {
		this.ijpCreatedOnToDate = ijpCreatedOnToDate;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getCurrentStatusName() {
		return currentStatusName;
	}

	public void setCurrentStatusName(String currentStatusName) {
		this.currentStatusName = currentStatusName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPendingWith() {
		return pendingWith;
	}

	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	
	
}
