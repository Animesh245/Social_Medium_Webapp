package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.dto.request.RequestStatusDto;
import com.animesh245.social_medium.dto.response.ResponseStatusDto;
import com.animesh245.social_medium.entity.Attachment;
import com.animesh245.social_medium.entity.Status;
import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.enums.Privacy;
import com.animesh245.social_medium.repository.StatusRepo;
import com.animesh245.social_medium.service.definition.IAttachmentService;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StatusService implements IStatusService
{
    private final StatusRepo statusRepo;
    private final ILocationService iLocationService;
    private final IAttachmentService iAttachmentService;

    public StatusService(StatusRepo statusRepo, ILocationService iLocationService, IAttachmentService iAttachmentService) {
        this.statusRepo = statusRepo;
        this.iLocationService = iLocationService;
        this.iAttachmentService = iAttachmentService;
    }

    @Override
    public List<ResponseStatusDto> getStatuses()
    {
        List<Status> statuses = statusRepo.findAll();
        List<ResponseStatusDto> responseStatusDtos = new ArrayList<>();

        for (Status status: statuses)
        {
         var responseStatusDto = entityToDto(status);
         responseStatusDtos.add(responseStatusDto);
        }
        return responseStatusDtos;
    }

    @Override
    public void saveStatus(RequestStatusDto requestStatusDto) throws Exception
    {
        var status = dtoToEntity(requestStatusDto);
        statusRepo.save(status);
    }

    @Override
    public ResponseStatusDto getStatus(String id)
    {
        var status = statusRepo.findById(Long.parseLong(id)).orElseThrow();

        return entityToDto(status);
    }

    @Override
    public void updateStatus(String id, RequestStatusDto requestStatusDto)
    {

    }

    @Override
    public void deleteStatus(String id)
    {

    }

    @Override
    public void deactivateStatus(String id)
    {

    }

    @Override
    public Status dtoToEntity(RequestStatusDto requestStatusDto) throws Exception
    {
        var location = iLocationService.getLocationByName(requestStatusDto.getLocationName());
        List<Attachment> attachments = iAttachmentService.insertInBulks(requestStatusDto.getFileList());

        var status = new Status();
        BeanUtils.copyProperties(requestStatusDto,status);
        status.setCreatedDate(LocalDateTime.now());
        status.setLocation(location);
        status.setPrivacy(Privacy.valueOf(requestStatusDto.getPrivacy()));
        status.setIsDeleted(false);
        status.setAttachmentList(attachments);

        return status;
    }

    @Override
    public ResponseStatusDto entityToDto(Status status)
    {
        //Fetching list of attachmentPath from status table
        List<Attachment> attachments =status.getAttachmentList();
        List<String > attachmentPathList = new ArrayList<>();

        for (Attachment attachment: attachments)
        {
         String path = attachment.getAttachmentPath();
         attachmentPathList.add(path);
        }
        //Fetching list of userNames from status table
        List<User> users = status.getLikedBy();
        List<String> userNames = new ArrayList<>();
        for (User user: users)
        {
            String name = user.getFullName();
            userNames.add(name);
        }
        if(!status.getIsDeleted())
        {
            var responseStatusDto = new ResponseStatusDto();
            BeanUtils.copyProperties(status,responseStatusDto);
            responseStatusDto.setId(status.getId().toString());
            responseStatusDto.setLocationName(status.getLocation().getLocationName());
            responseStatusDto.setPrivacy(status.getPrivacy().toString());
            responseStatusDto.setCreatedDate(status.getCreatedDate().toString());
            responseStatusDto.setAttachmentPathList(attachmentPathList);
//            responseStatusDto.setLikedByUser(userNames);
            return responseStatusDto;
        }
        return null;
    }
}
