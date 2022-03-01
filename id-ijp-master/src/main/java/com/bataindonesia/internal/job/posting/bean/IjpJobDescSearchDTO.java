package com.bataindonesia.internal.job.posting.bean;

import java.util.Calendar;


public class IjpJobDescSearchDTO {

	
	private String jbdTitle;
	
	private String jbdDesigName;

	private String jbdDeptName;

	private String jbdLocName;
	
	private Calendar jbdPubFrmDate;
	
	private Calendar jbdPubToDate;
	
	private int pageNo;

	private int pageSize;

	private String sortBy;

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
	
}
