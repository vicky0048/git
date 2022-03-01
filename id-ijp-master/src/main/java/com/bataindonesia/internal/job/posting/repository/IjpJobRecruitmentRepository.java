package com.bataindonesia.internal.job.posting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bataindonesia.internal.job.posting.entity.IjpJobRecruitment;

@Repository
public interface IjpJobRecruitmentRepository extends JpaRepository<IjpJobRecruitment,Long>{
	
//	Page<IjpJobRecruitment> findByJrtStatusIn(List<String> status, Pageable pagingSort);
	
	IjpJobRecruitment findByIjpTrxNo(String trxNo);

	IjpJobRecruitment findByJrtJobIdAndCreatedBy(Long jrtJobId,String createdBy);
	
	
	
}
