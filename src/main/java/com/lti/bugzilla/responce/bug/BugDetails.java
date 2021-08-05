package com.lti.bugzilla.responce.bug;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class BugDetails {    
private String ID;
private String Type;
private String Summary;
private String Product;
private String Comp;
private String Assignee;
private String Status;
private String Resolution;
private String Updated;
}
