package com.bataindonesia.internal.job.posting.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bataindonesia.internal.job.posting.bean.IjpListDto;

@Repository
public interface IjpListRepository extends JpaRepository<IjpListDto, Long>{

	Page<IjpListDto> findByPendingWith(String empId,Pageable page);
	
	@Query(value="Select IJP from IjpListDto IJP where IJP.ijpTrxNo like ?1 AND IJP.jbdTitle like ?2 AND IJP.fullname like ?3"
			+ " AND IJP.currentStatusName in(?4) AND IJP.ijpCreatedOn >=?5 AND IJP.ijpCreatedOn <=?6 AND IJP.pendingWith like ?7 AND IJP.empCode like ?8")
	Page<IjpListDto> findByPendingWithAndCurrentStatusCodeIn(String ijpTrxNo,String jbdTitle,String fullname,
			List<String> currentStatusCode,Calendar ijpCreatedOnFromDate,Calendar ijpCreatedOnToDate,String pendingWithEmpId,String appliedByEmpCode,Pageable page);
	
	@Query(value="Select IJP from IjpListDto IJP where IJP.ijpTrxNo like ?1 AND IJP.jbdTitle like ?2 AND IJP.fullname like ?3"
			+ " AND IJP.currentStatusName in(?4) AND IJP.ijpCreatedOn >=?5 AND IJP.ijpCreatedOn <=?6 AND IJP.empCode like ?7")
	Page<IjpListDto> findMyPendingIjp(String ijpTrxNo,String jbdTitle,String fullname,
			List<String> currentStatusCode,Calendar ijpCreatedOnFromDate,Calendar ijpCreatedOnToDate,String appliedByEmpCode,Pageable page);

	
	Page<IjpListDto> findByPendingWithAndCurrentStatusCodeLike(String empId,String currentStatusCode, Pageable page);
	
	IjpListDto findByIjpRecrId(Long ijpRecrId);
	
	/*@Query(value="Select can.ijp_recr_id,job.jbd_title,dsg.dsg_name,dep.dpt_name,job.jbd_loc_name,user.fullname,can.created_on,trx.status_name,can.ijp_resume_file_name,al.activity_owner from tt_activity_log al left join tt_transaction trx on al.trx_number=trx.trx_number  LEFT JOIN tt_ijp_job_recruitment can ON trx.trx_number =can.ijp_trx_no LEFT JOIN tt_ijp_job_desc job ON can.jrt_job_id = job.jbd_id LEFT JOIN tm_user user ON  can.created_by  =  user.emp_code  LEFT JOIN tm_department dep ON user.dpt_code = dep.dpt_code\r\n"
			+ " LEFT JOIN tm_designation dsg ON user.dsg_code = dsg.dsg_code \r\n"
			+ " where trx.trx_number like ?1 \r\n"
			+ " and job.jbd_title like ?2\r\n"
			+ " and user.fullname like ?3\r\n"
			+ " and trx.status_name in(?4)\r\n"
			+ " and can.created_on >=?5\r\n"
			+ " and can.created_on <=?6\r\n"
			+ " and user.emp_code like ?7\r\n"
			+ " and al.activity_owner=?8 order by trx.updated_on",nativeQuery = true)
	Page<Object[]> findApprovedByFilteredNative(String ijpTrxNo,String jbdTitle,String fullname,
			List<String> currentStatusCode,Calendar ijpCreatedOnFromDate,Calendar ijpCreatedOnToDate,String appliedByEmpCode,String activityOwner,Pageable page);*/
	
	@Query(value="Select can.ijp_recr_id,job.jbd_title,dsg.dsg_name,dep.dpt_name,job.jbd_loc_name,user.fullname,can.created_on,trx.status_name,can.ijp_resume_file_name,al.activity_owner from tt_activity_log al left join tt_transaction trx on al.trx_number=trx.trx_number  LEFT JOIN tt_ijp_job_recruitment can ON trx.trx_number =can.ijp_trx_no LEFT JOIN tt_ijp_job_desc job ON can.jrt_job_id = job.jbd_id LEFT JOIN tm_user user ON  can.created_by  =  user.emp_code  LEFT JOIN tm_department dep ON user.dpt_code = dep.dpt_code\r\n"
			+ " LEFT JOIN tm_designation dsg ON user.dsg_code = dsg.dsg_code \r\n"
			+ " where trx.trx_number like ?1 \r\n"
			+ " and job.jbd_title like ?2\r\n"
			+ " and user.fullname like ?3\r\n"
			+ " and trx.status_name in(?4)\r\n"
			+ " and can.created_on >=?5\r\n"
			+ " and can.created_on <=?6\r\n"
			+ " and user.emp_code like ?7\r\n"
			+ " and al.activity_owner=?8 order by trx.updated_on",
			countQuery="Select count(*) \r\n" + 
					"from tt_activity_log al \r\n" + 
					"left join tt_transaction trx on al.trx_number=trx.trx_number  \r\n" + 
					"LEFT JOIN tt_ijp_job_recruitment can ON trx.trx_number =can.ijp_trx_no \r\n" + 
					"LEFT JOIN tt_ijp_job_desc job ON can.jrt_job_id = job.jbd_id \r\n" + 
					"LEFT JOIN tm_user user ON  can.created_by  =  user.emp_code  \r\n" + 
					"LEFT JOIN tm_department dep ON user.dpt_code = dep.dpt_code\r\n" + 
					"LEFT JOIN tm_designation dsg ON user.dsg_code = dsg.dsg_code \r\n" + 
					"where trx.trx_number like ?1\r\n" + 
					"and job.jbd_title like ?2\r\n" + 
					"and user.fullname like ?3\r\n" + 
					"and trx.status_name in(?4)\r\n" + 
					"and can.created_on >=?5\r\n" + 
					"and can.created_on <=?6\r\n" + 
					"and user.emp_code like ?7\r\n" + 
					"and al.activity_owner=?8\r\n" + 
					"order by trx.updated_on",
			nativeQuery = true)
	Page<Object[]> findApprovedByFilteredNative(String ijpTrxNo,String jbdTitle,String fullname,
			List<String> currentStatusCode,Calendar ijpCreatedOnFromDate,Calendar ijpCreatedOnToDate,String appliedByEmpCode,String activityOwner,Pageable page);

	
	Page<IjpListDto> findByEmpCode(String empCode,Pageable page);
	
}
