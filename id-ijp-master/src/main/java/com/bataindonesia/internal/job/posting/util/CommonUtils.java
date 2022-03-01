package com.bataindonesia.internal.job.posting.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

public class CommonUtils {
	
	public static String getGeneratedCode(String prefix, long count) {
		return prefix + String.format("%04d", ++count);
	}
	
	public static String getToken(HttpServletRequest req) {
		final String requestTokenHeader = req.getHeader("Authorization");
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);
		}
		return jwtToken;
	}


	public static Calendar getCalendarFromString(String dateInString) throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		cal.setTime(sdf.parse(dateInString));
		return cal;
	}
	
	public static Calendar getCalendarObject(Object obj) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(((Timestamp)obj).getTime());
		return calendar;
	}
}
