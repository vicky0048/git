package com.bataindonesia.internal.job.posting.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.bataindonesia.internal.job.posting.bean.ResponseModel;
import com.bataindonesia.internal.job.posting.bean.TokenRequest;
import com.bataindonesia.internal.job.posting.bean.TokenResponse;
import com.bataindonesia.internal.job.posting.util.CommonUtils;
import com.bataindonesia.internal.job.posting.util.Constants;

@Component
@Aspect
public class TokenValidation {

	Logger logg = LoggerFactory.getLogger(TokenValidation.class);

	@Value("${TOKEN_URL}")
	private String tokenurl;

	@Value("${VALIDATE_URL}")
	private String validateUrl;

	RestTemplate restTemplate = new RestTemplate();

	@Pointcut("within(com.bataindonesia.internal.job.posting.controller..*)")
	private void myPointcut() {
	}

	@SuppressWarnings("unchecked")
	@Around("myPointcut()")
	public ResponseEntity<Object> applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		try {

			HttpServletRequest httpservletReq = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			TokenRequest request = new TokenRequest();
			request.setToken(CommonUtils.getToken(httpservletReq));
			HttpEntity<TokenRequest> re = new HttpEntity<>(request);
			TokenResponse tokenResponse = restTemplate.exchange(tokenurl, HttpMethod.POST, re, TokenResponse.class)
					.getBody();
			if (tokenResponse != null && tokenResponse.getStatus().contentEquals("True")) {
				// if (checkRoleBasedAuthorization(httpservletReq, tokenResponse)) {
				httpservletReq.setAttribute(Constants.TOKEN_RESPONSE, tokenResponse);
				return (ResponseEntity<Object>) pjp.proceed();
				// }
			}
			return ResponseEntity.ok().body(new ResponseModel(300, Constants.UNAUTHORIZED_ACCESS));
		} catch (Exception e) {
			logg.error(e.getMessage(), e);
			e.printStackTrace();
			return ResponseEntity.ok().body(new ResponseModel(300, Constants.UNAUTHORIZED_ACCESS));
		}

	}

	public boolean checkRoleBasedAuthorization(HttpServletRequest httpservletReq, TokenResponse tokenResponse) {
		Map<String, String> req = new HashMap<>();
		Map<String, String> pathVariableMap = (Map<String, String>) httpservletReq
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String url = removePathVariablesFromUrl(httpservletReq.getRequestURI(), pathVariableMap);
		System.out.println("path after removal" + url);
		req.put(Constants.URL, url);
		req.put(Constants.CURRENT_ROLE, tokenResponse.getUserrole());
		req.put(Constants.REQUEST_TYPE, httpservletReq.getMethod());
		HttpEntity<Map<String, String>> request = new HttpEntity<>(req);
		RestTemplate rest = new RestTemplate();
		@SuppressWarnings("unchecked")
		Map<String, String> response = restTemplate.exchange(validateUrl, HttpMethod.POST, request, Map.class)
				.getBody();
		if (response != null && response.get(Constants.AUTHORIZED).equalsIgnoreCase("True")) {
			return true;
		}
		return false;
	}

	public String removePathVariablesFromUrl(String url, Map<String, String> map) {
		for (Map.Entry<String, String> entryset : map.entrySet()) {
			String s = "/" + entryset.getValue();
			url = url.replaceFirst(s, "");
		}
		return url;
	}
}
