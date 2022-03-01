package com.bataindonesia.internal.job.posting.service;

import java.text.ParseException;

import org.springframework.data.domain.Page;

import com.bataindonesia.internal.job.posting.bean.IjpJobDescSearchDTO;
import com.bataindonesia.internal.job.posting.entity.IjpJobDescription;
import com.bataindonesia.internal.job.posting.util.Constants.IjpJobDescStatus;


public interface IIjpJobDescriptionService {
	
	//Page<IjpJobDescription> findAll(int pageNo, int pageSize);
	
	IjpJobDescription findById(long jbdId);
	
	IjpJobDescription addJob(IjpJobDescription jobDescription);
	
	IjpJobDescription updateJob(IjpJobDescription jobDescription,long jbdId);

	IjpJobDescription updateStatus(String empCode, long jrtDescId, IjpJobDescStatus ijpStatus);

	//Page<IjpJobDescription> findWithinActiveDateRange(String cat, int pageNo, int pageSize);

	Page<IjpJobDescription> findWithinActiveDateRange(String cat, IjpJobDescSearchDTO dto) throws ParseException;

	Page<IjpJobDescription> findAll(IjpJobDescSearchDTO dto) throws ParseException;

}
