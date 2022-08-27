package com.animesh245.social_medium.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUserDto
{
    private String id;
    private String fullName;
    private String emailId;
    private String dateOfBirth;
    private String password;
    private String role;
    private String locationName;
    private String profileImagePath;
}
