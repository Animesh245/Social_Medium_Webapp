package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.config.Properties;
import com.animesh245.social_medium.dto.request.RequestUserDto;
import com.animesh245.social_medium.dto.response.ResponseLocationDto;
import com.animesh245.social_medium.dto.response.ResponseUserDto;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.entity.Status;
import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.enums.AccountStatus;
import com.animesh245.social_medium.enums.Role;
import com.animesh245.social_medium.exception.NotFoundException;
import com.animesh245.social_medium.repository.UserRepo;
import com.animesh245.social_medium.service.definition.IAttachmentService;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IStatusService;
import com.animesh245.social_medium.service.definition.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService
{
    private final EntityManagerFactory emf;
    private final UserRepo userRepo;
    private final IStatusService istatusService;
    private final ILocationService iLocationService;
    private final IAttachmentService iAttachmentService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, IStatusService istatusService, ILocationService iLocationService, IAttachmentService iAttachmentService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, EntityManagerFactory emf)
    {
        this.userRepo = userRepo;
        this.istatusService = istatusService;
        this.iLocationService = iLocationService;
        this.iAttachmentService = iAttachmentService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.emf = emf;
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException
    {
        var user = userRepo.findByEmailId(emailId);

        if(user == null)
        {
            throw new UsernameNotFoundException(emailId);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public List<ResponseUserDto> getUsers() throws Exception {
        var userList = userRepo.findAll();
        var resUserDtoList = new ArrayList<ResponseUserDto>();

        for (User user: userList)
        {
        var resUserDto = entityToDto(user);
        resUserDtoList.add(resUserDto);
        }
        return resUserDtoList;
    }

    @Override
    public void saveUser(RequestUserDto requestUserDto) throws Exception
    {
        var user = dtoToEntity(requestUserDto);
        userRepo.save(user);
        System.out.println(user);
    }

    @Override
    public ResponseUserDto getUser(String id) throws Exception {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        return entityToDto(user);
    }

    @Override
    public void updateUser(String id, RequestUserDto requestUserDto) throws Exception
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        var user1 = dtoToEntity(requestUserDto);
        user1.setId(user.getId());
        if(userRepo.existsById(user.getId()))
        {
            userRepo.save(user1);
        }
    }

    @Override
    public void deleteUser(String id)
    {
        userRepo.deleteById(Long.parseLong(id));
    }

    @Override
    public void deactivateUser(String id)
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getAccountStatus() == AccountStatus.ACTIVE)
        {
            user.setAccountStatus(AccountStatus.DEACTIVATED);
            userRepo.save(user);
        }
    }

    //Dto to Entity Conversion

    @Override
    public User dtoToEntity(RequestUserDto requestUserDto) throws Exception
    {
        LocalDate date = LocalDate.parse(requestUserDto.getDateOfBirth());
        Location location = iLocationService.getLocationByName(requestUserDto.getLocationName());
        var attachment = iAttachmentService.saveAttachment(requestUserDto.getProfileImage(), Properties.USER_FOLDER);

//        var user = modelMapper.map(reqUserDto,User.class);
        var user = new User();
        BeanUtils.copyProperties(requestUserDto, user);
        user.setDateOfBirth(date);
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAttachment(attachment);
        user.setLocation(location);
        return user;
    }


    //Entity to Dto Conversion
    @Override
    public ResponseUserDto entityToDto(User user) throws Exception {
        if (user.getAccountStatus() == AccountStatus.ACTIVE)
        {

            //To deal with lazy initialization exception
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<User> q = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.statusList WHERE u.id=: id", User.class).setParameter("id", user.getId());
            List<User> userList = q.getResultList();

            em.getTransaction().commit();
            em.close();

            var responseStatusDtoTextList = new ArrayList<String >();
            var attachmentPathList = new ArrayList<String>();
            for (User user1: userList)
            {
                for (Status status: user1.getStatusList()) {
                    var responseStatusDto = istatusService.entityToDto(status);
                    responseStatusDtoTextList.add(responseStatusDto.getStatusText());
                    attachmentPathList.addAll(responseStatusDto.getAttachmentPathList());
                }
            }

            ResponseLocationDto location = iLocationService.getLocation(user.getLocation().getId().toString());
            var attachment = iAttachmentService.getAttachment(user.getAttachment().getId());
            var path = attachment.getAttachmentPath();

//            var resUserDto = modelMapper.map(user,ResUserDto.class);
            var resUserDto = new ResponseUserDto();
            BeanUtils.copyProperties(user, resUserDto);
            resUserDto.setId(user.getId().toString());
            resUserDto.setDateOfBirth(user.getDateOfBirth().toString());
            resUserDto.setRole(user.getRole().toString());
            resUserDto.setPassword(user.getPassword());
            resUserDto.setLocationName(location.getLocationName());
            resUserDto.setStatusDtoTextList(responseStatusDtoTextList);
            resUserDto.setStatusDtoImagePathList(attachmentPathList);
            resUserDto.setProfileImagePath(path);
            return resUserDto;
        }
        return null;
    }
}
