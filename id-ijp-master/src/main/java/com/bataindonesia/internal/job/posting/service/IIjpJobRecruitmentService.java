package com.bataindonesia.internal.job.posting.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.bataindonesia.internal.job.posting.bean.IjpJobRecrSearchDTO;
import com.bataindonesia.internal.job.posting.bean.IjpListDto;
import com.bataindonesia.internal.job.posting.bean.TokenResponse;
import com.bataindonesia.internal.job.posting.bean.UserModel;
import com.bataindonesia.internal.job.posting.bean.WorkFlowDto;
import com.bataindonesia.internal.job.posting.entity.IjpJobRecruitment;


public interface IIjpJobRecruitmentService {
	
	IjpJobRecruitment addJobRecr(IjpJobRecruitment jobRecruitment, TokenResponse tokenResponse);

	void updateTransaction(WorkFlowDto workFlowDto, TokenResponse tokenResponse);

	Page<IjpListDto> getIjpTaskListData(String empId, int pageNo, int pageSize);

	IjpListDto getIjpTaskListDataByJrtRecrId(Long jrtRecrId);

	IjpJobRecruitment findByJrtJobIdAndCreatedBy(Long jrtJobId, String createdBy);

	//Page<IjpListDto> findByPendingWithAndCurrentStatusCodeIn(String empId, List<String> list, int pageNo, int pageSize);

	IjpJobRecruitment findById(long jrtRecrId);

	UserModel updateUser(UserModel userModel, long jrtRecrId, IjpJobRecruitment jobRecruitmentFromDB, TokenResponse tokenResponse)
			throws Exception; 

	Page<IjpListDto> findByEmpCode(String empCode, int pageNo, int pageSize);

	Page<IjpListDto> findByPendingWithAndCurrentStatusCodeIn(List<String> list, IjpJobRecrSearchDTO searchDTO,
			boolean isMyPendingIjp) throws ParseException;

	Map<String, Object> getAllApprovedData(List<String> list, IjpJobRecrSearchDTO searchDTO, String empCode)
			throws ParseException;	
	
}
