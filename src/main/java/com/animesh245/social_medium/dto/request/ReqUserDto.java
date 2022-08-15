package com.animesh245.social_medium.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUserDto
{
    private String firstName;
    private String lastName;
    private String emailId;
    private String dateOfBirth;
    private String password;
    private String locationName;
    private String profileImagePath;
}
