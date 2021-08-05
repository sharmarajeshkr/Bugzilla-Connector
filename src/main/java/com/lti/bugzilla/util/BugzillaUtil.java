package com.lti.bugzilla.util;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BugzillaUtil {

	@Autowired
	Environment environmentVariable;

	@Autowired
	RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(BugzillaUtil.class);

	public ResponseEntity<String> apiCall(String service_url) {
		LOGGER.info(service_url);
		ResponseEntity<String> result = null;
		HttpHeaders headers = getHeader();
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		try {
			result = restTemplate.exchange(service_url, HttpMethod.POST, entity, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Set Header with Auth Key
	private HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("X-BUGZILLA-API-KEY", environmentVariable.getProperty("bugzilla.key"));
		return headers;
	}
	
	private String urlFormation() {
		// environmentVariable.getProperty("bugzilla.SERVICE_URL")+"";
		return "";
	}

}
