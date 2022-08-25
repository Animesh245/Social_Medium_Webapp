package com.animesh245.social_medium.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Getter
@Setter
public class RequestStatusDto
{
    private String statusText;
    private String locationName;
    private String privacy;
    private CommonsMultipartFile[] fileList;
}
