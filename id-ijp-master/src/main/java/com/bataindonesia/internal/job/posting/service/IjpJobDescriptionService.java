package com.bataindonesia.internal.job.posting.service;


import java.text.ParseException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bataindonesia.internal.job.posting.bean.IjpJobDescSearchDTO;
import com.bataindonesia.internal.job.posting.entity.IjpJobDescription;
import com.bataindonesia.internal.job.posting.repository.IjpJobDescriptionRepository;
import com.bataindonesia.internal.job.posting.util.CommonUtils;
import com.bataindonesia.internal.job.posting.util.Constants;

@Service
@Transactional
public class IjpJobDescriptionService implements IIjpJobDescriptionService{
	
	@Autowired
	IjpJobDescriptionRepository ijpJobDescriptionRepository;

	
	@Override
	public Page<IjpJobDescription> findAll(IjpJobDescSearchDTO dto) throws ParseException {
		validateDto(dto);
		Sort sort = Sort.by(dto.getSortBy()).descending();
		Pageable pagingSort = PageRequest.of(dto.getPageNo(), dto.getPageSize(), sort);
		return ijpJobDescriptionRepository.findAllNative(dto.getJbdTitle(),
				dto.getJbdDesigName(), dto.getJbdDeptName(), dto.getJbdLocName(), dto.getJbdPubFrmDate(),
				dto.getJbdPubToDate(), pagingSort);
	}
	
	@Override
	public IjpJobDescription findById(long jbdId) {
		Optional<IjpJobDescription> jobDesc= ijpJobDescriptionRepository.findById(jbdId);
		if(jobDesc.isPresent()) {
			return jobDesc.get();
		}
		return null;
	}

	@Override
	public IjpJobDescription addJob(IjpJobDescription jobDescription) {
		jobDescription.setJbdCode(CommonUtils.getGeneratedCode(Constants.IJP, ijpJobDescriptionRepository.count()));
		jobDescription.setIjpStatus(Constants.IjpJobDescStatus.SAVEASDRAFT.ijpStatusLabel);
		return ijpJobDescriptionRepository.save(jobDescription);
	}

	@Override
	public IjpJobDescription updateJob(IjpJobDescription jobDescription, long jbdId) {
		IjpJobDescription jDescription=findById(jbdId);
        if(jDescription!=null) {
        	jDescription.setJbdTitle(jobDescription.getJbdTitle());
        	jDescription.setJbdDesc(jobDescription.getJbdDesc());
        	jDescription.setJbdYOExp(jobDescription.getJbdYOExp());
        	jDescription.setJbdDesigCode(jobDescription.getJbdDesigCode());
        	jDescription.setJbdDesigName(jobDescription.getJbdDesigName());
        	jDescription.setJbdDeptCode(jobDescription.getJbdDeptCode());
        	jDescription.setJbdDeptName(jobDescription.getJbdDeptName());
        	jDescription.setJbdLocCode(jobDescription.getJbdLocCode());
        	jDescription.setJbdLocName(jobDescription.getJbdLocName());
        	jDescription.setJbdPubFrmDate(jobDescription.getJbdPubFrmDate());
        	jDescription.setJbdPubToDate(jobDescription.getJbdPubToDate());
        	jDescription.setUpdatedBy(jobDescription.getUpdatedBy());
        	jDescription.setJbdNumVacancy(jobDescription.getJbdNumVacancy());
        	jDescription.setOtherLocCode(jobDescription.getOtherLocCode());
        	jDescription.setJbdDescFileName(jobDescription.getJbdDescFileName());
        	jDescription.setJbdApplyEmpCats(jobDescription.getJbdApplyEmpCats());
        	return ijpJobDescriptionRepository.save(jDescription);
        }
		return null;
	}

	@Override
	public IjpJobDescription updateStatus(String empCode, long jrtDescId, Constants.IjpJobDescStatus status) {
		IjpJobDescription jobDescription = findById(jrtDescId);
		if (jobDescription != null) {
			jobDescription.setIjpStatus(status.ijpStatusLabel);
			jobDescription.setUpdatedBy(empCode);
			return ijpJobDescriptionRepository.save(jobDescription);
		}
		return null;
	}

	
	@Override
	public Page<IjpJobDescription> findWithinActiveDateRange(String cat,IjpJobDescSearchDTO dto) throws ParseException {
		validateDto(dto);
		Sort sort = Sort.by(dto.getSortBy()).descending();
		Pageable pagingSort = PageRequest.of(dto.getPageNo(), dto.getPageSize(), sort);
		return ijpJobDescriptionRepository.findWithJbdPubToDateAfter(cat,dto.getJbdTitle(),dto.getJbdDesigName(), dto.getJbdDeptName(), dto.getJbdLocName(),
				dto.getJbdPubFrmDate(), dto.getJbdPubToDate(), pagingSort);
	}

	private void validateDto(IjpJobDescSearchDTO dto) throws ParseException {
		if (null == dto.getSortBy()) {
			dto.setSortBy("updated_on");
		}
		if (null == dto.getJbdTitle()) {
			dto.setJbdTitle("%");
		}
		if (null == dto.getJbdDesigName()) {
			dto.setJbdDesigName("%");
		}

		if (null == dto.getJbdDeptName()) {
			dto.setJbdDeptName("%");
		}

		if (null == dto.getJbdLocName()) {
			dto.setJbdLocName("%");
		}
		if (null == dto.getJbdPubFrmDate()) {
			dto.setJbdPubFrmDate(CommonUtils.getCalendarFromString(Constants.MIN_DATE));
		}
		if (null == dto.getJbdPubToDate()) {
			dto.setJbdPubToDate(CommonUtils.getCalendarFromString(Constants.MAX_DATE));
		}
	}
	

}
