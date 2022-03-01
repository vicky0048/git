package com.bataindonesia.internal.job.posting.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationUtil {
    
	@Value("${EMAIL_SERVICE}")
	private String emailUrl;
	  
		public void sendEmail(EmailNotification email) {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity(emailUrl, email, email.getClass());
		}
	  
	 
}
