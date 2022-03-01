package com.bataindonesia.internal.job.posting.util;

public interface Constants {

	
	enum JrtStatus {
		SHORTLISTED("Shortlisted"), NOT_SHORTLISTED("Not Shortlisted"),
		SUITABLE_FOR_ANOTHER_ROLE("Suitable for another role"), SAVED_FOR_LATER("Saved for later"),
		SELECTED("Selected"), DROPPED_OUT("Dropped out"), NOT_SELECTED("Not Selected"), REFERRED("Referred"),
		JOINED("Joined");

		public final String label;

		private JrtStatus(String label) {
			this.label = label;
		}
	}

	enum JrtViewedStatus {
		READ, UNREAD;
	}
	
	enum IjpJobDescStatus {
			EXPIRED("Expired"),OPEN("Open"),SAVEASDRAFT("Save As Draft");
		
		public final String ijpStatusLabel;

		private IjpJobDescStatus(String ijpStatusLabel) {
			this.ijpStatusLabel = ijpStatusLabel;
		}
	}

	enum IjpStatus {
		SUBMITTED
	}
	
	String IJP = "IJP";
	String TOKEN_RESPONSE="token_response";
	String URL="url";
	String CURRENT_ROLE="current_role";
	String REQUEST_TYPE="request_type";
	String AUTHORIZED="authorized";
	String IJPSTATUS="ijpStatus";
	String MODULE="IJP";
	String PENDING_WITH_RM = "PENDING_WITH_RM";
	String PENDING_WITH_RM_NAME = "Pending with RM";
	String PENDING_WITH_IJP_APPROVER = "PENDING_WITH_IJP_APPROVER";
	String PENDING_WITH_IJP_APPROVER_NAME = "Pending with ijp Approver";
	String PENDING_WITH_TALENT_HR = "PENDING_WITH_TALENT_HR";
	String PENDING_WITH_TALENT_HR_NAME = "Pending with TALENT HR";
//	String PENDING_WITH_INDUCTION_HR = "PENDING_WITH_INDUCTION_HR";
//	String PENDING_WITH_INDUCTION_HR_NAME = "Pending with INDUCTION HR";
	String NEW = "NEW";
	String New = "New";
	String SUBMIT = "SUBMIT";
	String APPROVE = "APPROVE";
	String APPROVED = "APPROVED";
	String Approved ="Approved";
	String REJECT = "REJECT";
	String REJECTED = "REJECTED";
	String Rejected = "Rejected";
	String COMPLETE = "COMPLETE";
	String COMPLETED = "COMPLETED";
	String Completed = "Completed";
	String ALREADY_EXIST="Already Exist";
	String ADDED_SUCCESSFULLY="Added Successfully";
	String INTERNAL_SERVER_ERROR="Internal Server Error";
	String RECORD_FOUND="Record Found";
	String RECORD_NOT_FOUND="Record Not Found";
	String UPDATED_SUCCESSFULLY="Updated Successfully";
	String STATUS_NOT_VALID="Status Not Valid";
	String UNAUTHORIZED_ACCESS="UnAuthorized Access";
	String DATA_SUBMITTED_SUCCESSFULLY="Data Submitted SuccessFully";
	String data="data";
	String currentPage="currentPage";
	String totalItems="totalItems";
	String totalPages="totalPages";
	String MIN_DATE="1970-01-01";
	String MAX_DATE="2080-01-01";
	String IJP_APPLICATION_SUB="IJP APPLICATION";
	String IJP_APPROVAL_SUB="IJP APPROVAL";
	String IJP_REJECTION_SUB="IJP REJECTION";
	
}
