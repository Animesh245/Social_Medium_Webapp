package com.animesh245.social_medium.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReqUserDto
{
    private String firstName;
    private String lastName;
    private String emailId;
    private Date dateOfBirth;
    private String password;
    private String role;
    private String location;
    private String profileImagePath;
}
