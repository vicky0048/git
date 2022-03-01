package com.bataindonesia.internal.job.posting.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

import com.bataindonesia.internal.job.posting.bean.IjpJobDescSearchDTO;
import com.bataindonesia.internal.job.posting.bean.ResponseModel;
import com.bataindonesia.internal.job.posting.bean.TokenResponse;
import com.bataindonesia.internal.job.posting.entity.IjpJobDescription;
import com.bataindonesia.internal.job.posting.service.IIjpJobDescriptionService;
import com.bataindonesia.internal.job.posting.util.Constants;

@RestController
@CrossOrigin
@RequestMapping(value = "/ijp-job-description")
public class IjpJobDescriptionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IIjpJobDescriptionService jobDescriptionService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/save")
	public ResponseEntity<ResponseModel> addJob(@RequestBody IjpJobDescription jobDescObj,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			jobDescObj.setCreatedBy(tokenResponse.getEmpCode());
			IjpJobDescription jobDescription = jobDescriptionService.addJob(jobDescObj);
			return ResponseEntity.ok().body(new ResponseModel(200, Constants.ADDED_SUCCESSFULLY, jobDescription));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/update-status/{jbdId}")
	public ResponseEntity<ResponseModel> updateJobStatusToRead(@PathVariable long jbdId,
			@RequestBody Map<String, String> reqMap, HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			String status=reqMap.get(Constants.IJPSTATUS);
			Optional<Constants.IjpJobDescStatus> st = Arrays.stream(Constants.IjpJobDescStatus.values())
					.filter(e -> e.ijpStatusLabel.equalsIgnoreCase(status)).findFirst();
			if (!st.isPresent()) {
				return ResponseEntity.ok().body(new ResponseModel(400, Constants.STATUS_NOT_VALID));
			}
			IjpJobDescription jobDesc = jobDescriptionService.updateStatus(tokenResponse.getEmpCode(),
					jbdId,st.get());
			if (jobDesc != null)
				return ResponseEntity.ok().body(new ResponseModel(200, Constants.UPDATED_SUCCESSFULLY, jobDesc));
			else {
				return ResponseEntity.ok().body(new ResponseModel(404, Constants.RECORD_NOT_FOUND));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/update-by-id/{jbdId}")
	public ResponseEntity<ResponseModel> updateJob(@RequestBody IjpJobDescription jobDescObj, @PathVariable long jbdId,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			jobDescObj.setUpdatedBy(tokenResponse.getEmpCode());
			IjpJobDescription jobDescription = jobDescriptionService.updateJob(jobDescObj, jbdId);
			if (jobDescription != null)
				return ResponseEntity.ok().body(new ResponseModel(200, Constants.UPDATED_SUCCESSFULLY, jobDescription));
			else {
				return ResponseEntity.ok().body(new ResponseModel(404, Constants.RECORD_NOT_FOUND));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/get-all")
	public ResponseEntity<ResponseModel> findAll(@RequestBody IjpJobDescSearchDTO searchDto,HttpServletRequest req) {
		try {
			Page<IjpJobDescription> pagedJdList = jobDescriptionService.findAll(searchDto);
			for (IjpJobDescription ijpJobDesc : pagedJdList.getContent()) {
				if (ijpJobDesc.getJbdPubToDate() != null
						&& ijpJobDesc.getJbdPubToDate().getTimeInMillis() < System.currentTimeMillis()
						&& ijpJobDesc.getIjpStatus().equalsIgnoreCase(Constants.IjpJobDescStatus.OPEN.ijpStatusLabel)) {
					ijpJobDesc.setIjpStatus(Constants.IjpJobDescStatus.EXPIRED.ijpStatusLabel);
				}
			}
			Map<String, Object> response = new HashMap<>();
			response.put(Constants.data, pagedJdList.getContent());
			response.put(Constants.currentPage, pagedJdList.getNumber());
			response.put(Constants.totalItems, pagedJdList.getTotalElements());
			response.put(Constants.totalPages, pagedJdList.getTotalPages());
			ResponseModel rs = new ResponseModel(200, Constants.RECORD_FOUND, response);
			return ResponseEntity.ok().body(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/get-all-active-jobs")
	public ResponseEntity<ResponseModel> findAllBasedOnActiveDateRange(@RequestBody IjpJobDescSearchDTO searchDto,HttpServletRequest req) {
		try {
			TokenResponse tokenResponse= (TokenResponse) req.getAttribute(Constants.TOKEN_RESPONSE);
			//jobDescObj.setUpdatedBy(tokenResponse.getEmpCode());
			Page<IjpJobDescription> pagedJdList = jobDescriptionService.findWithinActiveDateRange(tokenResponse.getEmpCategory(), searchDto);
			Map<String, Object> response = new HashMap<>();
			response.put(Constants.data, pagedJdList.getContent());
			response.put(Constants.currentPage, pagedJdList.getNumber());
			response.put(Constants.totalItems, pagedJdList.getTotalElements());
			response.put(Constants.totalPages, pagedJdList.getTotalPages());
			ResponseModel rs = new ResponseModel(200, Constants.RECORD_FOUND, response);
			return ResponseEntity.ok().body(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}
	}

	@GetMapping(path = "get-by-id/{jbdId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> findById(@PathVariable long jbdId) {
		try {
			IjpJobDescription jobDescription = jobDescriptionService.findById(jbdId);
			if (jobDescription != null)
				return ResponseEntity.ok().body(new ResponseModel(200, Constants.RECORD_FOUND, jobDescription));
			else {
				return ResponseEntity.ok().body(new ResponseModel(404, Constants.RECORD_NOT_FOUND));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.ok().body(new ResponseModel(500, Constants.INTERNAL_SERVER_ERROR));
		}

	}

}
