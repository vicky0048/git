package com.bataindonesia.internal.job.posting.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bataindonesia.internal.job.posting.entity.ActivityLog;

@Repository
public interface ActivityLogDao extends CrudRepository<ActivityLog, Long> {
	
	
}
