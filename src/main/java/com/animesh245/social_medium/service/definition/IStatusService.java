package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.RequestStatusDto;
import com.animesh245.social_medium.dto.response.ResponseStatusDto;
import com.animesh245.social_medium.entity.Status;

import java.util.List;

public interface IStatusService 
{
    List<ResponseStatusDto> getStatuses() throws Exception;

    void saveStatus(RequestStatusDto requestStatusDto) throws Exception;


    ResponseStatusDto getStatus(String id) throws Exception;

    void updateStatus(String id , RequestStatusDto requestStatusDto) throws Exception;

    void deleteStatus(String id) throws Exception;

    void deactivateStatus(String id);

    Status dtoToEntity(RequestStatusDto requestStatusDto) throws Exception;

    ResponseStatusDto entityToDto(Status status) throws Exception;
}
