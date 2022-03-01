package com.bataindonesia.internal.job.posting.service;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bataindonesia.internal.job.posting.bean.IjpApprvedListDto;
import com.bataindonesia.internal.job.posting.bean.IjpJobRecrSearchDTO;
import com.bataindonesia.internal.job.posting.bean.IjpListDto;
import com.bataindonesia.internal.job.posting.bean.ResponseModel;
import com.bataindonesia.internal.job.posting.bean.TokenResponse;
import com.bataindonesia.internal.job.posting.bean.UserModel;
import com.bataindonesia.internal.job.posting.bean.WorkFlowDto;
import com.bataindonesia.internal.job.posting.entity.ActivityLog;
import com.bataindonesia.internal.job.posting.entity.IjpJobRecruitment;
import com.bataindonesia.internal.job.posting.entity.TransactionModel;
import com.bataindonesia.internal.job.posting.notification.MailContents;
import com.bataindonesia.internal.job.posting.notification.NotificationUtil;
import com.bataindonesia.internal.job.posting.repository.IjpJobRecruitmentRepository;
import com.bataindonesia.internal.job.posting.repository.IjpListRepository;
import com.bataindonesia.internal.job.posting.repository.TransactionDao;
import com.bataindonesia.internal.job.posting.util.CommonUtils;
import com.bataindonesia.internal.job.posting.util.Constants;

@Service
@Transactional
public class IjpJobRecruitmentService implements IIjpJobRecruitmentService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate= new RestTemplate();

	@Value("${UPDATE_USER_URL}")
	private String updateUserurl;
	
	@Value("${USER_DETAILS}")
	private String userDtlsUrl;

	@Autowired
	private NotificationUtil notifcationService;
	
	@Autowired
	private IjpJobRecruitmentRepository ijpJobRecruitmentRepository;

	@Autowired
	private TransactionServicesImpl trxService;
	
	@Autowired
	private IjpListRepository ijpListRepo;
	
	@Autowired
	private TransactionDao trxDao;
	
	@Autowired
	private IjpJobDescriptionService ijpJobDescService;

	@Override
	public IjpJobRecruitment addJobRecr(IjpJobRecruitment jobRecruitment, TokenResponse tokenResponse) {
		jobRecruitment.setIjpStatus(Constants.IjpStatus.SUBMITTED.name());
		jobRecruitment.setIjpViewedStatus(Constants.JrtViewedStatus.UNREAD.name());
		jobRecruitment.setIjpTrxNo(trxService.generateTrxNumber());
		jobRecruitment.setCreatedBy(tokenResponse.getEmpCode());
		TransactionModel trxModel = new TransactionModel();
		trxModel.setTrxNumber(jobRecruitment.getIjpTrxNo());
		trxModel.setCreatedBy(jobRecruitment.getCreatedBy());
		trxModel.setModuleName(Constants.MODULE);
		trxModel.setStatusCode(Constants.PENDING_WITH_RM);
		trxModel.setStatusName(Constants.PENDING_WITH_RM_NAME);
		trxModel.setPendingWith(tokenResponse.getRmCode());
		TransactionModel trxModelSaved = trxService.save(trxModel);
		ActivityLog log = new ActivityLog(trxModelSaved.getTrxNumber(), trxModelSaved.getCreatedBy(), Constants.NEW,
				Constants.New, Constants.PENDING_WITH_RM, Constants.PENDING_WITH_RM_NAME, Constants.SUBMIT);
		trxService.save(log);
		IjpJobRecruitment jrt= ijpJobRecruitmentRepository.save(jobRecruitment);
		try {
			notifcationService.sendEmail(MailContents.submitMailToUser(tokenResponse, jobRecruitment.getIjpTrxNo()));
			notifcationService.sendEmail(MailContents.submitMailToRM(getEmailIdFromEmpCode(tokenResponse.getRmCode()), jobRecruitment.getIjpTrxNo()));
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return jrt;
	}

	@Override
	public void updateTransaction(WorkFlowDto workFlowDto, TokenResponse tokenResponse) {
		try {
			if (Constants.APPROVE.equals(workFlowDto.getOutcome())) {
				if (Constants.PENDING_WITH_RM.equalsIgnoreCase(workFlowDto.getCurrentStatus())) {

					TransactionModel model= saveTransactionModel(workFlowDto.getTrxNo(), Constants.PENDING_WITH_IJP_APPROVER,
							Constants.PENDING_WITH_IJP_APPROVER_NAME, workFlowDto.getPendingWith());

					ActivityLog log = new ActivityLog(workFlowDto.getTrxNo(), tokenResponse.getEmpCode(),
							Constants.PENDING_WITH_RM, Constants.PENDING_WITH_RM_NAME,
							Constants.PENDING_WITH_IJP_APPROVER, Constants.PENDING_WITH_IJP_APPROVER_NAME,
							Constants.APPROVE);
					log.setRemarks(workFlowDto.getRemarks());
					trxService.save(log);
					try {
						notifcationService.sendEmail(MailContents.approveRMMailToUser(getEmailIdFromEmpCode(model.getCreatedBy()),workFlowDto.getTrxNo(),workFlowDto.getPendingWith()));
						notifcationService.sendEmail(MailContents.submitMailToIJPApprover(getEmailIdFromEmpCode(workFlowDto.getPendingWith()),workFlowDto.getTrxNo()));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}

				} else if (Constants.PENDING_WITH_IJP_APPROVER.equalsIgnoreCase(workFlowDto.getCurrentStatus())) {

					TransactionModel model= saveTransactionModel(workFlowDto.getTrxNo(), Constants.PENDING_WITH_TALENT_HR,
							Constants.PENDING_WITH_TALENT_HR_NAME, workFlowDto.getPendingWith());

					ActivityLog log = new ActivityLog(workFlowDto.getTrxNo(), tokenResponse.getEmpCode(),
							Constants.PENDING_WITH_IJP_APPROVER, Constants.PENDING_WITH_IJP_APPROVER_NAME,
							Constants.PENDING_WITH_TALENT_HR, Constants.PENDING_WITH_TALENT_HR_NAME, Constants.APPROVE);
					log.setRemarks(workFlowDto.getRemarks());
					trxService.save(log);
					try {
						notifcationService.sendEmail(MailContents.approveIjpApproverMailToUser(getEmailIdFromEmpCode(model.getCreatedBy()),workFlowDto.getTrxNo(),tokenResponse.getEmpCode()));
						notifcationService.sendEmail(MailContents.submitMailToTalentHR(getEmailIdFromEmpCode(workFlowDto.getPendingWith()),workFlowDto.getTrxNo()));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}


				} else if (Constants.PENDING_WITH_TALENT_HR.equalsIgnoreCase(workFlowDto.getCurrentStatus())) {

					TransactionModel model=saveTransactionModel(workFlowDto.getTrxNo(), Constants.APPROVED, Constants.Approved, workFlowDto.getPendingWith());

					ActivityLog log = new ActivityLog(workFlowDto.getTrxNo(), tokenResponse.getEmpCode(),
							Constants.PENDING_WITH_TALENT_HR, Constants.PENDING_WITH_TALENT_HR_NAME, Constants.APPROVED,
							Constants.Approved, Constants.APPROVE);
					log.setRemarks(workFlowDto.getRemarks());
					trxService.save(log);
					try {
						notifcationService.sendEmail(MailContents.approveTalentHRToUser(getEmailIdFromEmpCode(model.getCreatedBy()),workFlowDto.getTrxNo()));
						notifcationService.sendEmail(MailContents.submitMailToInductionHr(getEmailIdFromEmpCode(workFlowDto.getPendingWith()),workFlowDto.getTrxNo()));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				} else if (Constants.APPROVED.equalsIgnoreCase(workFlowDto.getCurrentStatus())) {

					TransactionModel model=saveTransactionModel(workFlowDto.getTrxNo(), Constants.COMPLETED, Constants.Completed, null);

					ActivityLog log = new ActivityLog(workFlowDto.getTrxNo(), tokenResponse.getEmpCode(),
							Constants.APPROVED, Constants.APPROVED, Constants.COMPLETED, Constants.Completed,
							Constants.COMPLETE);
					log.setRemarks(workFlowDto.getRemarks());
					trxService.save(log);
					
				}

			} else if (Constants.REJECT.equals(workFlowDto.getOutcome())) {

				if (Constants.PENDING_WITH_RM.equalsIgnoreCase(workFlowDto.getCurrentStatus())) {

					TransactionModel model= saveTransactionModel(workFlowDto.getTrxNo(), Constants.REJECTED, Constants.Rejected, null);

					ActivityLog log = new ActivityLog(workFlowDto.getTrxNo(), tokenResponse.getEmpCode(),
							Constants.PENDING_WITH_RM, Constants.PENDING_WITH_RM_NAME, Constants.REJECTED,
							Constants.Rejected, Constants.REJECT);
					log.setRemarks(workFlowDto.getRemarks());
					trxService.save(log);
					try {
						notifcationService.sendEmail(MailContents.rejectRMMailToUser(
								getEmailIdFromEmpCode(model.getCreatedBy()), workFlowDto.getTrxNo()));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}

				} else if (Constants.PENDING_WITH_IJP_APPROVER.equalsIgnoreCase(workFlowDto.getCurrentStatus())) {

					TransactionModel model=saveTransactionModel(workFlowDto.getTrxNo(), Constants.REJECTED, Constants.Rejected, null);

					ActivityLog log = new ActivityLog(workFlowDto.getTrxNo(), tokenResponse.getEmpCode(),
							Constants.PENDING_WITH_IJP_APPROVER, Constants.PENDING_WITH_IJP_APPROVER_NAME,
							Constants.REJECTED, Constants.Rejected, Constants.REJECT);
					log.setRemarks(workFlowDto.getRemarks());
					trxService.save(log);
					try {
						notifcationService.sendEmail(MailContents.rejectIjpApproverMailToUser(
								getEmailIdFromEmpCode(model.getCreatedBy()), workFlowDto.getTrxNo()));
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}

				} 
			}
		} catch (Exception e) {
			logger.error("Exception in IjpJobRecruitmentService runWorkflow:::::::::");
			System.out.println("Exception in IjpJobRecruitmentService runWorkflow:::::::::");
			throw e;
		}

	}

	
	public TransactionModel saveTransactionModel(String trxNo, String statusCode, String statusName, String pendingWith) {
		TransactionModel trxModel = trxService.findByTrxNo(trxNo);
		trxModel.setStatusCode(statusCode);
		trxModel.setStatusName(statusName);
		trxModel.setPendingWith(pendingWith);
		return trxService.save(trxModel);
		
	}


	@Override
	public Page<IjpListDto> getIjpTaskListData(String empId,int pageNo,int pageSize) {
		Pageable pagingSort = PageRequest.of(pageNo, pageSize);
		return ijpListRepo.findByPendingWith(empId,pagingSort);
	}
	
	
	@Override
	public IjpListDto getIjpTaskListDataByJrtRecrId(Long jrtRecrId) {
		return ijpListRepo.findByIjpRecrId(jrtRecrId);
	}
	
	
	@Override
	public IjpJobRecruitment findByJrtJobIdAndCreatedBy(Long jrtJobId,String createdBy) {
		return ijpJobRecruitmentRepository.findByJrtJobIdAndCreatedBy(jrtJobId, createdBy);
	}

	
//	@Override
//	public Page<IjpListDto> findByPendingWithAndCurrentStatusCodeLike(String empId,int pageNo,int pageSize){
//		Pageable pagingSort = PageRequest.of(pageNo, pageSize);
//		return ijpListRepo.findByPendingWithAndCurrentStatusCodeLike(empId, Constants.PENDING_WITH_TALENT_HR, pagingSort);
//	}
	
	@Override
	public Page<IjpListDto> findByPendingWithAndCurrentStatusCodeIn(List<String> list, IjpJobRecrSearchDTO searchDTO,
			boolean isMyPendingIjp) throws ParseException {
		validateDto(searchDTO);
		Pageable pagingSort = null;
		if (searchDTO.getSortBy() != null) {
			Sort sort = Sort.by(searchDTO.getSortBy()).descending();
			pagingSort = PageRequest.of(searchDTO.getPageNo(), searchDTO.getPageSize(), sort);
		} else {
			pagingSort = PageRequest.of(searchDTO.getPageNo(), searchDTO.getPageSize());
		}
		if (!isMyPendingIjp) {
			return ijpListRepo.findByPendingWithAndCurrentStatusCodeIn(searchDTO.getIjpTrxNo(), searchDTO.getJbdTitle(),
					searchDTO.getFullname(), list, searchDTO.getIjpCreatedOnFromDate(),
					searchDTO.getIjpCreatedOnToDate(), searchDTO.getPendingWith(), searchDTO.getEmpCode(), pagingSort);
		} else {
			return ijpListRepo.findMyPendingIjp(searchDTO.getIjpTrxNo(), searchDTO.getJbdTitle(),
					searchDTO.getFullname(), list, searchDTO.getIjpCreatedOnFromDate(),
					searchDTO.getIjpCreatedOnToDate(), searchDTO.getEmpCode(), pagingSort);
		}
	}

	@Override
	public IjpJobRecruitment findById(long jrtRecrId) {
		Optional<IjpJobRecruitment> ijpJobRecr= ijpJobRecruitmentRepository.findById(jrtRecrId);
		if(ijpJobRecr.isPresent()) {
			return ijpJobRecr.get();
		}
		return null;
	}

	@Override
	public UserModel updateUser(UserModel userModel, long jrtRecrId, IjpJobRecruitment jobRecruitmentFromDB,
			TokenResponse tokenResponse) throws Exception {
		saveTransactionModel(jobRecruitmentFromDB.getIjpTrxNo(), Constants.COMPLETED,
				Constants.Completed, null);
		ActivityLog log = new ActivityLog(jobRecruitmentFromDB.getIjpTrxNo(), tokenResponse.getEmpCode(),
				Constants.APPROVED, Constants.APPROVED, Constants.COMPLETED, Constants.Completed, Constants.COMPLETE);
		trxService.save(log);
		HttpEntity<UserModel> request = new HttpEntity<>(userModel);
		userModel.setEmpCode(jobRecruitmentFromDB.getCreatedBy());
		ResponseEntity<UserModel> model = restTemplate.exchange(updateUserurl, HttpMethod.POST, request,
				UserModel.class);
		if (model.getStatusCode().equals(HttpStatus.OK)) {
			notifcationService.sendEmail(MailContents.approveInductionHrUser(getEmailIdFromEmpCode(jobRecruitmentFromDB.getCreatedBy()),jobRecruitmentFromDB.getIjpTrxNo()));
			return model.getBody();
		} else {
			throw new RuntimeException("Error While Saving User");
		}

	}


	@Override
	public Page<IjpListDto> findByEmpCode(String empCode,int pageNo,int pageSize) {
		Pageable pagingSort = PageRequest.of(pageNo, pageSize);
		return ijpListRepo.findByEmpCode(empCode, pagingSort);
	}
	
	private void validateDto(IjpJobRecrSearchDTO dto) throws ParseException {
		if (null == dto.getIjpTrxNo()) {
			dto.setIjpTrxNo("%");
		}
		if (null == dto.getJbdTitle()) {
			dto.setJbdTitle("%");
		}
		if (null == dto.getFullname()) {
			dto.setFullname("%");
		}
		if (null == dto.getPendingWith()) {
			dto.setPendingWith("%");
		}
		if (null == dto.getEmpCode()) {
			dto.setEmpCode("%");
		}
		if (null == dto.getIjpCreatedOnFromDate()) {
			dto.setIjpCreatedOnFromDate(CommonUtils.getCalendarFromString(Constants.MIN_DATE));
		}
		if (null == dto.getIjpCreatedOnToDate()) {
			dto.setIjpCreatedOnToDate(CommonUtils.getCalendarFromString(Constants.MAX_DATE));
		}
	}

	
	public UserModel getEmailIdFromEmpCode(String empCode) throws URISyntaxException {
		String url=userDtlsUrl+empCode;
		URI uri = new URI(url);
		ResponseEntity<UserModel> result = restTemplate.getForEntity(uri, UserModel.class);
		return result.getBody();
	}
	
	@Override
	public Map<String, Object> getAllApprovedData(List<String> list, IjpJobRecrSearchDTO searchDTO, String empCode)
			throws ParseException {
		validateDto(searchDTO);
		Pageable pagingSort = null;
		if (searchDTO.getSortBy() != null) {
			Sort sort = Sort.by(searchDTO.getSortBy()).descending();
			pagingSort = PageRequest.of(searchDTO.getPageNo(), searchDTO.getPageSize(),sort);
		} else {
			pagingSort = PageRequest.of(searchDTO.getPageNo(), searchDTO.getPageSize());
		}
		Page<Object[]> pagedData= ijpListRepo.findApprovedByFilteredNative(searchDTO.getIjpTrxNo(), searchDTO.getJbdTitle(),
				searchDTO.getFullname(), list, searchDTO.getIjpCreatedOnFromDate(), searchDTO.getIjpCreatedOnToDate(),
				searchDTO.getEmpCode(), empCode, pagingSort);
		List<IjpApprvedListDto> ijpList = new ArrayList<>();
		for (Object[] object : pagedData.getContent()) {
			IjpApprvedListDto dto=new IjpApprvedListDto();
			dto.setIjpRecrId(((BigInteger) object[0]).longValue());
			dto.setJbdTitle((String) object[1]);
			dto.setJbdDesigName((String) object[2]);
			dto.setJbdDeptName((String) object[3]);
			dto.setJbdLocName((String) object[4]);
			dto.setFullname((String) object[5]);
			dto.setIjpCreatedOn(CommonUtils.getCalendarObject(object[6]));
			dto.setCurrentStatusName((String) object[7]);
			dto.setIjpResumeFileName((String) object[8]);
			ijpList.add(dto);
		}
		Map<String, Object> response = new HashMap<>();
		response.put("data", ijpList);
		response.put("currentPage", pagedData.getNumber());
		response.put("totalItems", pagedData.getTotalElements());
		response.put("totalPages", pagedData.getTotalPages());
		return response;

	}
}
