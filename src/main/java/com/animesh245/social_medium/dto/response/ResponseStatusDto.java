package com.animesh245.social_medium.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseStatusDto
{
    private String id;
    private String statusText;
    private String createdDate;
    private String locationName;
    private String privacy;
    private String userName;
    private String profileImagePath;
    private List<String> attachmentPathList;
    private List<String> likedByUser;
}
