package com.bataindonesia.internal.job.posting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bataindonesia.internal.job.posting.entity.TransactionModel;

@Repository
public interface TransactionDao extends CrudRepository<TransactionModel, Long> {
	
	public TransactionModel findByTrxNumber(String trxNo);
		
	@Query(value="select t from TransactionModel t where t.statusCode=?1 and t.trxNumber like 'LOCA%' ORDER BY updatedOn desc")
	Page<TransactionModel> findByStatusCode(String statusCode,Pageable page);
	
}
