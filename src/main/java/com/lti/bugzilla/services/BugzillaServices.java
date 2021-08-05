package com.lti.bugzilla.services;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.lti.bugzilla.dao.BugzillaDAO;
import com.lti.bugzilla.resquest.bug.JsonObject;
import com.lti.bugzilla.resquest.bug.ProjectDetails;
import com.lti.bugzilla.util.BugzillaUtil;
import com.lti.bugzilla.util.SecurityConstants;

@Repository
public class BugzillaServices implements BugzillaDAO {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment environmentVariable;

	@Autowired
	BugzillaUtil bugzillaUtil;

	String service_url = new String();
	public static int countHeaders = 1;

	private static final Logger LOGGER = LoggerFactory.getLogger(BugzillaServices.class);

	private ResponseEntity<String> getBugDetails(String service_url) {
		ResponseEntity<String> result = null;
		try {
			result = bugzillaUtil.apiCall(service_url);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return result;
	}

	public String serviceUrl(String key, String ref) {
		String service_url = "";
		switch (key) {
		case "bug":
			service_url = environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + SecurityConstants.BUG + "/"
					+ Integer.parseInt(ref);
			break;
		case "history":
			String BUG_ID_HISTORY = SecurityConstants.BUG + "/" + Integer.parseInt(ref) + "/history";
			service_url = environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + BUG_ID_HISTORY;
			break;
		case "comment":
			String BUG_ID_COMMENT = SecurityConstants.BUG + "/" + Integer.parseInt(ref) + "/comment";
			service_url = environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + BUG_ID_COMMENT;
			break;
		case "productaccessable":
			String PRODUCT_ACCESSIBLE = SecurityConstants.PRODUCT + "?type=" + ref;
			service_url = environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + PRODUCT_ACCESSIBLE;
			break;
		case "productid":
			String PRODUCT_ID = SecurityConstants.PRODUCT + "/" + ref;
			service_url = environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + PRODUCT_ID;
			break;
		case "productname":
			String PRODUCT_NAME = SecurityConstants.PRODUCT + "?names=" + ref;
			service_url = environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + PRODUCT_NAME;
			break;

		default:
			break;
		}
		return service_url;
	}

	@Override
	public ResponseEntity<String> getBugById(String bugId) {
		service_url = serviceUrl("bug", bugId);
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getBugHistoryById(String bugId) {
		service_url = serviceUrl("history", bugId);
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getBugHistoryByIdDate(String bugId, String till_date) {
		service_url = serviceUrl("history", bugId);
		service_url += "?new_since=" + till_date;
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getBugCommentById(String bugId) {
		service_url = serviceUrl("comment", bugId);
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getProductAccessible(String productType) {
		service_url = serviceUrl("productaccessable", productType);
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getProductByID(String productId) {
		service_url = serviceUrl("productid", productId);
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getProductByName(String productName) {
		service_url = serviceUrl("productname", productName);
		return bugzillaUtil.apiCall(service_url);
	}

	@Override
	public ResponseEntity<String> getBugByProductComponents(ProjectDetails projectDetals) {
		// String service_url = createProductDetailsParameter(projectDetals.getJsonObject());
		return bugzillaUtil.apiCall(service_url);

	}

	private String createBugDetailsParameter(JsonObject jsonObject) {

		String bugStatus = jsonObject.getBugStatus().trim().length() == 0 || jsonObject.getBugStatus() == null ? "__all__" : jsonObject.getBugStatus();
		String resolution = jsonObject.getResolution().trim().length() == 0 || jsonObject.getResolution() == null ? "---" : jsonObject.getResolution();

		String BUG_PARAM = SecurityConstants.BUGLIST + "?query_format=advanced"+ "&classification="
				+ jsonObject.getClassificationName() + "&component=" + jsonObject.getComponentName() + "&product="
				+ jsonObject.getProductName()+ "&bug_status=" + bugStatus + "&resolution=" + resolution;
		return environmentVariable.getProperty("bugzilla.SERVICE_URL").trim() + BUG_PARAM;
	}

	@Override
	public ResponseEntity<String> auth(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public String extractBugUsingURLParsing(ProjectDetails details) {
		JSONArray arr = new JSONArray();
		String URL = "";
		
		URL = "http://bugzilla.mozilla.org/buglist.cgi?query_format=advanced&product=AUS Graveyard&classification=Graveyard";
		// URL = "https://bugzilla.mozilla.org/buglist.cgi?product=AUS%20Graveyard&query_format=advanced&classification=Graveyard&component=Administration&resolution=---";
		// URL = "https://bugzilla.mozilla.org/buglist.cgi?product=AUS%20Graveyard&classification=Graveyard&query_format=advanced";
		URL = createBugDetailsParameter(details.getJsonObject());
		try {
			Document doc = Jsoup.connect(URL).get();
			// Elements count = doc.select("thead tr th");
			/*
			 * for (Element head : doc.select("thead tr")) { if
			 * (head.select("th:nth-of-type(1)").text().equals("")) continue; else for (int
			 * i = 1; i <= count.size(); i++) {
			 * System.out.print(head.select("th:nth-of-type(" + i + ")").text() + "    ");
			 * countHeaders += 1; } }
			 */
			Elements rows = doc.select("tbody");
			for (Element row : rows.select("tr")) {
				if (row.select("td:nth-of-type(1)").text().equals(""))
					continue;
				else
					arr.put(formJsonObject(row));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	public JSONObject formJsonObject(Element row) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", row.select("td:nth-of-type(1)").text());
		jsonObj.put("type", row.select("td:nth-of-type(2)").text());
		jsonObj.put("Summary", row.select("td:nth-of-type(3)").text());
		jsonObj.put("product", row.select("td:nth-of-type(4)").text());
		jsonObj.put("component", row.select("td:nth-of-type(5)").text());
		jsonObj.put("assignee", row.select("td:nth-of-type(6)").text());
		jsonObj.put("status", row.select("td:nth-of-type(7)").text());
		jsonObj.put("resolution", row.select("td:nth-of-type(8)").text());
		jsonObj.put("updated", row.select("td:nth-of-type(9)").text());
		return jsonObj;
	}

}
