package com.lti.bugzilla.resquest.bug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JsonObject {

	public String toolUrl;
	public String classificationName;
	public String productName;
	public String componentName;
	public String toolName;
	public Object userName;
	public String accessToken;
	public String release;
	public String bugStatus;
	public String resolution;

}