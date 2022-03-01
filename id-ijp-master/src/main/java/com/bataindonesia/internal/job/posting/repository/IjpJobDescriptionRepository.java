package com.bataindonesia.internal.job.posting.repository;


import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bataindonesia.internal.job.posting.entity.IjpJobDescription;

@Repository
public interface IjpJobDescriptionRepository extends JpaRepository<IjpJobDescription, Long>{

	
//	@Query(value="select * from tt_ijp_job_desc JOB where JOB.jbd_pub_to_date >= CURRENT_DATE AND JOB.ijp_status like 'Open' And "
//			+ "FIND_IN_SET(:category,JOB.jbd_apply_emp_categories)", nativeQuery = true)
//	Page<IjpJobDescription> findWithJbdPubToDateAfter(@Param("category") String cat,Pageable pagingSort);
	
	@Query(value="select * from tt_ijp_job_desc JOB where JOB.jbd_pub_to_date >= CURRENT_DATE AND JOB.ijp_status like 'Open' And "
			+ "FIND_IN_SET(?1,JOB.jbd_apply_emp_categories) "
			+ "AND JOB.jbd_title like ?2 AND JOB.jbd_desig_name like ?3 AND JOB.jbd_dept_name like ?4 AND JOB.jbd_loc_name like ?5 "
			+ "AND JOB.jbd_pub_frm_date<=?7 AND JOB.jbd_pub_to_date>=?6 "
			, nativeQuery = true)
	Page<IjpJobDescription> findWithJbdPubToDateAfter(String cat,String jbdTitle,String jbdDesigName,String jbdDeptName,
			String jbdLocName,Calendar jbdPubFrmDate,Calendar jbdPubToDate,Pageable pagingSort);

	@Query(value="select * from tt_ijp_job_desc JOB where "
			+ "JOB.jbd_title like ?1 AND JOB.jbd_desig_name like ?2 AND JOB.jbd_dept_name like ?3 AND JOB.jbd_loc_name like ?4 "
			+ "AND JOB.jbd_pub_frm_date<=?6 AND JOB.jbd_pub_to_date>=?5 "
			, nativeQuery = true)
	Page<IjpJobDescription> findAllNative(String jbdTitle,String jbdDesigName,String jbdDeptName,String jbdLocName,Calendar jbdPubFrmDate,Calendar jbdPubToDate,Pageable pagingSort);

}