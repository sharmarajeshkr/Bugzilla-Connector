package com.lti.bugzilla.responce.bug;

import java.util.Date;
import java.util.List;

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
public class Bug{
	
    public List<Object> depends_on;
    public String url;
    public String version;
    public boolean is_open;
    public String update_token;
    public String cf_status_firefox91;
    public String summary;
    public List<Object> blocks;
    public String severity;
    public boolean is_cc_accessible;
    public int votes;
    public String cf_status_thunderbird_esr78;
    public String cf_tracking_firefox92;
    public String cf_fx_iteration;
    public String cf_fission_milestone;
    public String cf_has_str;
    public Date last_change_time;
    public String cf_webcompat_priority;
    public String cf_tracking_firefox_sumo;
    public String target_milestone;
    public String cf_tracking_firefox90;
    public List<Object> see_also;
    public String classification;
    public List<Object> groups;
    public String status;
    public String cf_fx_points;
    public int comment_count;
    public String cf_crash_signature;
    public AssignedToDetail assigned_to_detail;
    public Date cf_last_resolved;
    public String type;
    public String cf_status_firefox92;
    public String product;
    public String cf_tracking_firefox_relnote;
    public List<String> cc;
    public String whiteboard;
    public Object alias;
    public String cf_tracking_firefox_esr91;
    public String op_sys;
    public String cf_tracking_firefox_esr78;
    public String cf_status_firefox_esr78;
    public CreatorDetail creator_detail;
    public List<Object> regressed_by;
    public String platform;
    public int id;
    public String cf_tracking_firefox91;
    public List<Flag> flags;
    public List<Object> regressions;
    public List<Object> mentors;
    public Object cf_rank;
    public String qa_contact;
    public String cf_status_firefox90;
    public Date creation_time;
    public boolean is_creator_accessible;
    public String cf_qa_whiteboard;
    public String priority;
    public String cf_root_cause;
    public String resolution;
    public String cf_tracking_thunderbird_esr91;
    public List<Object> mentors_detail;
    public String cf_a11y_review_project_flag;
    public String cf_user_story;
    public List<Object> duplicates;
    public QaContactDetail qa_contact_detail;
    public boolean is_confirmed;
    public String cf_has_regression_range;
    public List<CcDetail> cc_detail;
    public String assigned_to;
    public String component;
    public List<Object> keywords;
    public String creator;
    public Object dupe_of;
    public String cf_status_thunderbird_esr91;
    public String cf_tracking_thunderbird_esr78;
}