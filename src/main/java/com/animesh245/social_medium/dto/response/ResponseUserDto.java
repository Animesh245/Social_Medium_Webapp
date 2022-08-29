package com.animesh245.social_medium.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseUserDto
{
    private String id;
    private String fullName;
    private String emailId;
    private String dateOfBirth;
    private String password;
    private String role;
    private String locationName;
    private String profileImagePath;
    private List<String> statusDtoTextList;
    private List<String> statusDtoImagePathList;
}
