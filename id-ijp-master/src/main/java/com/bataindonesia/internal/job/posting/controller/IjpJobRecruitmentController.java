package com.bataindonesia.internal.job.posting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bataindonesia.internal.job.posting.bean.IjpJobRecrSearchDTO;
import com.bataindonesia.internal.job.posting.bean.IjpListDto;
import com.bataindonesia.internal.job.posting.bean.ResponseModel;
import com.bataindonesia.internal.job.posting.bean.TokenResponse;
import com.bataindonesia.internal.job.posting.bean.UserModel;
import com.bataindonesia.internal.job.posting.bean.WorkFlowDto;
import com.bataindonesia.internal.job.posting.entity.IjpJobRecruitment;
import com.bataindonesia.internal.job.posting.service.IIjpJobRecruitmentService;
import com.bataindonesia.internal.job.posting.service.TransactionServicesImpl;
import com.bataindonesia.internal.job.posting.util.CommonUtils;
import com.bataindonesia.internal.job.posting.util.Constants;

@RestController
@CrossOrigin
@RequestMapping(value = "/ijp-job-recruitment")
public class IjpJobRecruitmentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IIjpJobRecruitmentService ijpJobRecruitmentService;
	
	@Autowired
	TransactionServicesImpl trxService;

	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/save")
	public ResponseEntity<ResponseModel> addJobRecr(@RequestBody IjpJobRecruitment ijpJobRecrObj,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			IjpJobRecruitment ijpJobRecruitmentAlreadyPresent=ijpJobRecruitmentService.findByJrtJobIdAndCreatedBy
					(ijpJobRecrObj.getJrtJobId(),tokenResponse.getEmpCode());
			if(ijpJobRecruitmentAlreadyPresent!=null) {
				return ResponseEntity.ok().body(new ResponseModel(400,Constants.ALREADY_EXIST));
			}
			IjpJobRecruitment ijpJobRecruitment = ijpJobRecruitmentService.addJobRecr(ijpJobRecrObj,tokenResponse);

			return ResponseEntity.ok().body(new ResponseModel(200, Constants.ADDED_SUCCESSFULLY, ijpJobRecruitment));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/run-workflow")
	public ResponseEntity<ResponseModel> updateTransactionData(@RequestBody WorkFlowDto workFlowDto,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			ijpJobRecruitmentService.updateTransaction(workFlowDto,tokenResponse);
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.DATA_SUBMITTED_SUCCESSFULLY, workFlowDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/ijp-task-list")
	public ResponseEntity<ResponseModel> getIjpTaskList(@RequestBody IjpJobRecrSearchDTO dto, HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			List<String> list=new ArrayList<>();
			if (dto.getCurrentStatusName() == null) {
				list.add(Constants.PENDING_WITH_RM_NAME);
				list.add(Constants.PENDING_WITH_IJP_APPROVER_NAME);
				list.add(Constants.PENDING_WITH_TALENT_HR_NAME);
				list.add(Constants.APPROVED);
			}else {
				list.add(dto.getCurrentStatusName());
			}
			dto.setPendingWith(tokenResponse.getEmpCode());
			Page<IjpListDto> dataList = ijpJobRecruitmentService.findByPendingWithAndCurrentStatusCodeIn(list,dto,false);
			Map<String, Object> response = new HashMap<>();
			response.put(Constants.data, dataList.getContent());
			response.put(Constants.currentPage, dataList.getNumber());
			response.put(Constants.totalItems, dataList.getTotalElements());
			response.put(Constants.totalPages, dataList.getTotalPages());
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.RECORD_FOUND,response));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
//	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/hr-task-list")
//	public ResponseEntity<ResponseModel> getHrTaskList(@RequestParam(defaultValue = "0") int pageNo,
//			@RequestParam(defaultValue = "10") int pageSize,HttpServletRequest req) {
//		try {
//			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
//			List<String> list=new ArrayList<>();
//			List<String> inputRoles = Arrays.asList(tokenResponse.getUserrole().split(",\\s"));
//			if (inputRoles.contains("TALENT-HR")) {
//				list.add(Constants.PENDING_WITH_TALENT_HR);
//			}
//			if (inputRoles.contains("INDUCTION-HR")) {
//				list.add(Constants.APPROVED);
//			}
//			Page<IjpListDto> dataList = ijpJobRecruitmentService.findByPendingWithAndCurrentStatusCodeIn(tokenResponse.getEmpCode(),list,pageNo,pageSize);
//			Map<String, Object> response = new HashMap<>();
//			response.put(Constants.data, dataList.getContent());
//			response.put(Constants.currentPage, dataList.getNumber());
//			response.put(Constants.totalItems, dataList.getTotalElements());
//			response.put(Constants.totalPages, dataList.getTotalPages());
//			return ResponseEntity.ok().body(new ResponseModel(200, Constants.ADDED_SUCCESSFULLY,response));
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
//		}
//	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/update-user-details/{jrtRecrId}")
	public ResponseEntity<ResponseModel> joinEmployee(@RequestBody UserModel userModelObj, @PathVariable long jrtRecrId,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			userModelObj.setCreatedBy(tokenResponse.getEmpCode());
			IjpJobRecruitment jobRecruitmentFromDB=ijpJobRecruitmentService.findById(jrtRecrId);
			if(jobRecruitmentFromDB==null) {
				return ResponseEntity.ok().body(new ResponseModel(404, Constants.RECORD_NOT_FOUND));
			}
			UserModel userModel= ijpJobRecruitmentService.updateUser(userModelObj,jrtRecrId,jobRecruitmentFromDB, tokenResponse);
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.UPDATED_SUCCESSFULLY, userModel));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/ijp-task-list-by-id/{jrtRecrId}")
	public ResponseEntity<ResponseModel> getIjpTaskListByJrtRecrId(@PathVariable Long jrtRecrId,HttpServletRequest req) {
		try {
			//TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			IjpListDto data = ijpJobRecruitmentService.getIjpTaskListDataByJrtRecrId(jrtRecrId);
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.RECORD_FOUND,data));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/my-pending-ijp")
	public ResponseEntity<ResponseModel> getPendingIJP(@RequestBody IjpJobRecrSearchDTO dto,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			List<String> list=new ArrayList<>();
			if (dto.getCurrentStatusName() == null) {
				list.add(Constants.PENDING_WITH_RM_NAME);
				list.add(Constants.PENDING_WITH_IJP_APPROVER_NAME);
				list.add(Constants.PENDING_WITH_TALENT_HR_NAME);
				list.add(Constants.APPROVED);
				list.add(Constants.Completed);
			}else {
				list.add(dto.getCurrentStatusName());
			}
			dto.setEmpCode(tokenResponse.getEmpCode());
			Page<IjpListDto> dataList = ijpJobRecruitmentService.findByPendingWithAndCurrentStatusCodeIn(list,dto,true);
			Map<String, Object> response = new HashMap<>();
			response.put(Constants.data, dataList.getContent());
			response.put(Constants.currentPage, dataList.getNumber());
			response.put(Constants.totalItems, dataList.getTotalElements());
			response.put(Constants.totalPages, dataList.getTotalPages());
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.RECORD_FOUND,response));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/get-approved-list-by-empCode")
	public ResponseEntity<ResponseModel> getApprovedIjpByEmpCode(@RequestBody IjpJobRecrSearchDTO dto, HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			List<String> list=new ArrayList<>();
			if (dto.getCurrentStatusName() == null) {
				list.add(Constants.PENDING_WITH_RM_NAME);
				list.add(Constants.PENDING_WITH_IJP_APPROVER_NAME);
				list.add(Constants.PENDING_WITH_TALENT_HR_NAME);
				list.add(Constants.APPROVED);
				list.add(Constants.REJECTED);
				list.add(Constants.Completed);
			}else {
				list.add(dto.getCurrentStatusName());
			}
			//dto.setPendingWith(tokenResponse.getEmpCode());
			Map<String, Object> response = ijpJobRecruitmentService.getAllApprovedData(list, dto, tokenResponse.getEmpCode());
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.RECORD_FOUND,response));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}

}
