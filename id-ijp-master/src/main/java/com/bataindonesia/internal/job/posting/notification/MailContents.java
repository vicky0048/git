package com.bataindonesia.internal.job.posting.notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bataindonesia.internal.job.posting.bean.TokenResponse;
import com.bataindonesia.internal.job.posting.bean.UserModel;



public class MailContents {
	private static final Logger logger = LogManager.getLogger(MailContents.class);

	public static EmailNotification submitMailToUser(TokenResponse user, String trxNo) {
		logger.debug("IJP submitMailToUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Applied");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullName()).append(",<br/><br/>");
		sb.append("Your IJP application No. " + trxNo
				+ " has been submitted to your reporting manager. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP submitMailToUser :End");
		return email;
	}

	public static EmailNotification submitMailToRM(UserModel user,String trxNo) {
		logger.debug("IJP submitMailToRM :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Request Received");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("You have received a IJP application No. " + trxNo
				+ " request for Approval. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP submitMailToRM :End");
		return email;
	}

	public static EmailNotification submitMailToIJPApprover(UserModel user, String trxNo) {
		logger.debug("IJP submitMailToIJPApprover :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Approval Request Received");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("You have received a IJP application No. " + trxNo + " request for Approval. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP submitMailToIJPApprover :End");
		return email;
	}

	public static EmailNotification submitMailToTalentHR(UserModel user, String trxNo) {
		logger.debug("IJP submitMailToTalentHR :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Approval Request Received");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("You have received a IJP application No. " + trxNo + " request for Approval. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP submitMailToTalentHR :End");
		return email;
	}

	public static EmailNotification submitMailToInductionHr(UserModel user, String trxNo) {
		logger.debug("IJP submitMailToInductionHr :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Joining Request Received");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("You have received a IJP application No. " + trxNo + " request for Joining. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP submitMailToInductionHr :End");
		return email;
	}

	public static EmailNotification approveRMMailToUser(UserModel user, String trxNo,String empCode) {
		logger.debug("IJP approveRMMailToUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Approved by Reporting Manager");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("Your IJP application No. " + trxNo
				+ " has been approved by your reporting manager and now will be sent to IJP approver having empCode "+empCode+". <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP approveRMMailToUser :End");
		return email;
	}

	public static EmailNotification approveIjpApproverMailToUser(UserModel user, String trxNo,String empCode) {
		logger.debug("IJP approveIjpApproverMailToUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Approved");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("Your IJP application No. " + trxNo
				+ " has been approved by IJP Approver having empCode "+empCode+" and now will be sent to Talent-HR. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP approveIjpApproverMailToUser :End");
		return email;
	}

	public static EmailNotification approveTalentHRToUser(UserModel user, String trxNo) {
		logger.debug("IJP approveTalentHRToUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Remarks Submitted");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("Talent-Hr has successfully added the remark to your IJP application No. " + trxNo
				+ " and now will be sent to Induction-HR for joining. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP approveTalentHRToUser :End");
		return email;
	}

	public static EmailNotification approveInductionHrUser(UserModel user, String trxNo) {
		logger.debug("IJP approveInductionHrUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Joining Completed");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append(
				"Your IJP application No. " + trxNo + " joining details has been successfully completed by Induction-Hr. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP approveInductionHrUser :End");
		return email;
	}


	public static EmailNotification rejectRMMailToUser(UserModel user, String trxNo) {
		logger.debug("IJP rejectRMMailToUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Rejected");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("Your IJP application No. " + trxNo
				+ "  has been rejected by your reporting manager. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP rejectRMMailToUser :End");
		return email;
	}

	public static EmailNotification rejectIjpApproverMailToUser(UserModel user, String trxNo) {
		logger.debug("IJP rejectIjpApproverMailToUser :Start");
		EmailNotification email = new EmailNotification();
		email.setSubject("IJP Rejected");
		String[] sendTo = { user.getOfficialEmailId() };
		email.setSendTo(sendTo);
		StringBuilder sb = new StringBuilder();
		sb.append("Dear ").append(user.getFullname()).append(",<br/><br/>");
		sb.append("Your IJP application No. " + trxNo + "  has been rejected by IJP approver. <br/><br/>");
		sb.append("Regards: <br/>").append("Bata Indonesia <br/><br/><br/>");
		email.setBodyContent(sb.toString());
		logger.debug("IJP rejectIjpApproverMailToUser :End");
		return email;
	}

}
