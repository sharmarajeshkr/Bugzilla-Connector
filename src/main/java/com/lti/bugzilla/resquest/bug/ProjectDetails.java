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
public class ProjectDetails {
	 public String url;	    
	 public JsonObject jsonObject;

}
