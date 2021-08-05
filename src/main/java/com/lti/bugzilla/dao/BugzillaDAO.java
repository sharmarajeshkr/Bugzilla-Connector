package com.lti.bugzilla.dao;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lti.bugzilla.resquest.bug.ProjectDetails;

@Service
public interface BugzillaDAO {
	
	public String extractBugUsingURLParsing(ProjectDetails productDetails);
	
	public ResponseEntity<String> auth(String username,String password);
	// Bug
	public ResponseEntity<String> getBugByProductComponents(ProjectDetails projectDetals);	
	public ResponseEntity<String> getBugById(String bugId);	
	public ResponseEntity<String> getBugCommentById(String bugId);
	public ResponseEntity<String> getBugHistoryById(String bugId);
	public ResponseEntity<String> getBugHistoryByIdDate(String bugId,String since_date);
	
	
	// Product
	public ResponseEntity<String> getProductAccessible(String productType);
	public ResponseEntity<String> getProductByID(String productId);
	public ResponseEntity<String> getProductByName(String productName);
	
}