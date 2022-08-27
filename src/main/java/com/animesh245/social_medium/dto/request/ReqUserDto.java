package com.animesh245.social_medium.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Getter
@Setter
public class ReqUserDto
{
    private String fullName;
    private String emailId;
    private String dateOfBirth;
    private String password;
    private String locationName;
    private CommonsMultipartFile profileImage;
}
