package com.lti.bugzilla.controller;

import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bugzilla.dao.BugzillaDAO;
import com.lti.bugzilla.resquest.bug.JsonObject;
import com.lti.bugzilla.resquest.bug.ProjectDetails;

@RestController
@RequestMapping("/bugzilla")
@CrossOrigin

public class BugzillaController {
	@Autowired
	BugzillaDAO bugzillaDao;
			
	@GetMapping(path = "/bugdetails")
	public ResponseEntity<String> bugById(@RequestBody ProjectDetails projectDetails) throws Exception {				
		String response = bugzillaDao.extractBugUsingURLParsing(projectDetails);
		if (response != null )
			return ResponseEntity.ok(response);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	@GetMapping(path = "/bug")
	public ResponseEntity<String> bugById( @QueryParam("bugId") String bugId) throws Exception {		
		if (bugId == null || bugId.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Bug Id");		
		ResponseEntity<String> response = bugzillaDao.getBugById(bugId);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/bug/history")
	public ResponseEntity<String> bugHistoryById( @QueryParam("bugId") String bugId) throws Exception {		
		if (bugId == null || bugId.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Bug Id");		
		ResponseEntity<String> response = bugzillaDao.getBugHistoryById(bugId);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/bug/histilldate")
	public ResponseEntity<String> bugHistoryById( @QueryParam("bugId") String bugId , @QueryParam("till_date") String till_date) throws Exception {		
		if (bugId == null || bugId.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Bug Id");		
		ResponseEntity<String> response = bugzillaDao.getBugHistoryByIdDate(bugId,till_date);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/bug/comment")
	public ResponseEntity<String> bugCommentById( @QueryParam("bugId") String bugId) throws Exception {		
		if (bugId == null || bugId.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Bug Id");		
		ResponseEntity<String> response = bugzillaDao.getBugCommentById(bugId);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/product")
	public ResponseEntity<String> productById( @QueryParam("productId") String productId) throws Exception {		
		if (productId == null || productId.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Product Id");		
		ResponseEntity<String> response = bugzillaDao.getProductByID(productId);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/product/accessible")
	public ResponseEntity<String> productAccessible( @QueryParam("type") String type) throws Exception {		
		if (type == null || type.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Product Type");		
		ResponseEntity<String> response = bugzillaDao.getProductAccessible(type);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/product/name")
	public ResponseEntity<String> productbyName( @QueryParam("productName") String productName) throws Exception {		
		if (productName == null || productName.length() == 0) 			
			return ResponseEntity.badRequest().body("\n Please Provide Product Name");		
		ResponseEntity<String> response = bugzillaDao.getProductByName(productName);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path= "/bugsearch")
	public ResponseEntity<String> bugProductConsume( @RequestBody ProjectDetails projectDetails) throws Exception {				
		ResponseEntity<String> response = bugzillaDao.getBugByProductComponents(projectDetails);
		if (response != null )
			return response;
		return ResponseEntity.noContent().build();
	}
	
}
