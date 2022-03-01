package com.bataindonesia.internal.job.posting.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bataindonesia.internal.job.posting.entity.ActivityLog;
import com.bataindonesia.internal.job.posting.entity.TransactionModel;
import com.bataindonesia.internal.job.posting.repository.ActivityLogDao;
import com.bataindonesia.internal.job.posting.repository.TransactionDao;
import com.bataindonesia.internal.job.posting.util.Constants;

@Service
@Transactional
public class TransactionServicesImpl {

	@Autowired
	private TransactionDao trxDao;

	@Autowired
	private ActivityLogDao activityDao;
	
	static Map<String,String> state= new HashMap<String,String>();
	
	static
	{
		state.put("NEW", "");
	}
	
	public String generateTrxNumber() {
		String trxNumber="";
		String mname=Constants.MODULE;
		if(mname!=null && mname.length()>4) {
			mname=mname.toUpperCase().substring(0, 4);
		}
		trxNumber=mname+generateTrxId();
		return trxNumber;
	}
	
	private String generateTrxId() {
		StringBuilder sb=new StringBuilder();
		Calendar cal=Calendar.getInstance();
		sb.append(cal.get(Calendar.YEAR)).append(cal.get(Calendar.MONTH)<10?"0"+cal.get(Calendar.MONTH):cal.get(Calendar.MONTH));
		sb.append(cal.get(Calendar.DATE)<10?"0"+cal.get(Calendar.DATE):cal.get(Calendar.DATE));
		sb.append(cal.get(Calendar.HOUR)<10?"0"+cal.get(Calendar.HOUR):cal.get(Calendar.HOUR));
		sb.append(cal.get(Calendar.MINUTE)<10?"0"+cal.get(Calendar.MINUTE):cal.get(Calendar.MINUTE));
		sb.append(cal.get(Calendar.SECOND)<10?"0"+cal.get(Calendar.SECOND):cal.get(Calendar.SECOND));
		String milis=""+cal.get(Calendar.MILLISECOND);
		while(milis.length()<5) {
			milis="0"+milis;
		}
		sb.append(milis);
		return sb.toString();
	}

	public TransactionModel findByTrxNo(String trxNo) {
		return trxDao.findByTrxNumber(trxNo);
	}

	public TransactionModel save(TransactionModel trxModel) {
		return trxDao.save(trxModel);
	}

	public ActivityLog save(ActivityLog log) {
		return activityDao.save(log);
	}
	
	public Page<TransactionModel> findByStatusCode(String statusCode, int pageNo, int pageSize) {
		Sort sort = Sort.by("updatedOn").descending();
		Pageable pagingSort = PageRequest.of(pageNo, pageSize,sort);
		return trxDao.findByStatusCode(statusCode,pagingSort);
	}
}