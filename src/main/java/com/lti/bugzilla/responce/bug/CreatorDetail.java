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
public class CreatorDetail{
    public String nick;
    public String name;
    public String real_name;
    public String email;
    public int id;
}