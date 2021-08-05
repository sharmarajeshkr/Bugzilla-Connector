package com.lti.bugzilla.responce.bug;

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
public class CcDetail{
    public String nick;
    public int id;
    public String email;
    public String real_name;
    public String name;
}
