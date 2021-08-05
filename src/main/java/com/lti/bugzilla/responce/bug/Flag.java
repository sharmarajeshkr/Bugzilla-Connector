package com.lti.bugzilla.responce.bug;

import java.util.Date;

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
public class Flag{
    public Date creation_date;
    public String setter;
    public String status;
    public String name;
    public Date modification_date;
    public int type_id;
    public int id;
}