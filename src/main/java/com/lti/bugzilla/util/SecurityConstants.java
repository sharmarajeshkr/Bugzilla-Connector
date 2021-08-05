package com.lti.bugzilla.util;

import org.springframework.stereotype.Service;

@Service
public class SecurityConstants {
	  public static String BUG = "rest/bug";
	  // Product	  
	  public static  String PRODUCT = "/rest/product";	 
	  public static  String PRODUCT_IDS = "/rest/product?ids=1&ids=2&ids=3";
	  public static  String BUGLIST = "/buglist.cgi";
	}