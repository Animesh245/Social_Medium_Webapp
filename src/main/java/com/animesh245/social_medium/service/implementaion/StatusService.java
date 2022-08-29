package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.dto.request.RequestStatusDto;
import com.animesh245.social_medium.dto.response.ResponseLocationDto;
import com.animesh245.social_medium.dto.response.ResponseStatusDto;
import com.animesh245.social_medium.entity.Attachment;
import com.animesh245.social_medium.entity.Status;
import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.enums.Privacy;
import com.animesh245.social_medium.repository.StatusRepo;
import com.animesh245.social_medium.repository.UserRepo;
import com.animesh245.social_medium.service.definition.IAttachmentService;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StatusService implements IStatusService
{
    private final EntityManagerFactory emf;
    private final StatusRepo statusRepo;
    private final UserRepo userRepo;
    private final ILocationService iLocationService;
    private final IAttachmentService iAttachmentService;

    public StatusService(EntityManagerFactory emf, StatusRepo statusRepo, UserRepo userRepo, ILocationService iLocationService, IAttachmentService iAttachmentService) {
        this.emf = emf;
        this.statusRepo = statusRepo;
        this.userRepo = userRepo;
        this.iLocationService = iLocationService;
        this.iAttachmentService = iAttachmentService;
    }

    @Override
    public List<ResponseStatusDto> getStatuses() throws Exception {
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
    public ResponseStatusDto getStatus(String id) throws Exception {
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

        var user = userRepo.findByEmailId(SecurityContextHolder.getContext().getAuthentication().getName());

        var status = new Status();
        BeanUtils.copyProperties(requestStatusDto,status);
        status.setCreatedDate(LocalDateTime.now());
        status.setLocation(location);
        status.setUser(user);
        status.setPrivacy(Privacy.valueOf(requestStatusDto.getPrivacy()));
        status.setIsDeleted(false);
        status.setAttachmentList(attachments);

        return status;
    }

    @Override
    public ResponseStatusDto entityToDto(Status status) throws Exception {
        //To deal with lazy initialization exception
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // want to fetch the attachments of a status
        TypedQuery<Status> q = em.createQuery("SELECT s FROM Status s LEFT JOIN FETCH s.attachmentList WHERE s.id=: id", Status.class).setParameter("id",status.getId());
        TypedQuery<Status> q2 = em.createQuery("SELECT s FROM Status s LEFT JOIN FETCH  s.likedBy WHERE s.id=: id", Status.class).setParameter("id",status.getId());
        List<Status> statusList = q.getResultList();//9
        List<Status> statusList1 = q2.getResultList();

        em.getTransaction().commit();
        em.close();

        //Fetching list of userNames from status table
        List<String> userNames = new ArrayList<>();
        var attachmentPathList = new ArrayList<String>();


        var status1 = statusList.get(0);

//        for (Status status1: statusList)
//        {
                for (Attachment attachment: status1.getAttachmentList())
                {
                    attachmentPathList.add(attachment.getAttachmentPath());
                }
//        }

        for (Status status2: statusList1)
        {
            for (User user: status2.getLikedBy())
            {
                String name = user.getFullName();
                userNames.add(name);
            }
        }
        var user = status.getUser();
        Attachment profileImage = user.getAttachment();
        String path = iAttachmentService.getAttachment(profileImage.getId()).getAttachmentPath();
        ResponseLocationDto location = iLocationService.getLocation(status.getLocation().getId().toString());

        if(!status.getIsDeleted())
        {
            var responseStatusDto = new ResponseStatusDto();
            BeanUtils.copyProperties(status,responseStatusDto);
            responseStatusDto.setId(status.getId().toString());
            responseStatusDto.setUserName(user.getFullName());
            responseStatusDto.setProfileImagePath(path);
            responseStatusDto.setLocationName(location.getLocationName());
            responseStatusDto.setPrivacy(status.getPrivacy().toString());
            responseStatusDto.setCreatedDate(status.getCreatedDate().toString());
            responseStatusDto.setAttachmentPathList(attachmentPathList);
            responseStatusDto.setLikedByUser(userNames);
            return responseStatusDto;
        }
        return null;
    }
}
