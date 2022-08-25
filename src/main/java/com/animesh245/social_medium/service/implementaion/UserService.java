package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.config.Properties;
import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.enums.AccountStatus;
import com.animesh245.social_medium.enums.Role;
import com.animesh245.social_medium.exception.NotFoundException;
import com.animesh245.social_medium.repository.UserRepo;
import com.animesh245.social_medium.service.definition.IAttachmentService;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService
{
    private final UserRepo userRepo;
    private final ILocationService iLocationService;

    private final IAttachmentService iAttachmentService;

    private final ModelMapper modelMapper;

    public UserService(UserRepo userRepo, ILocationService iLocationService, IAttachmentService iAttachmentService, ModelMapper modelMapper)
    {
        this.userRepo = userRepo;
        this.iLocationService = iLocationService;
        this.iAttachmentService = iAttachmentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ResUserDto> getUsers()
    {
        var userList = userRepo.findAll();
        var resUserDtoList = new ArrayList<ResUserDto>();

        for (User user: userList)
        {
        var resUserDto = entityToDto(user);
        resUserDtoList.add(resUserDto);
        }
        return resUserDtoList;
    }

    @Override
    public void saveUser(ReqUserDto reqUserDto) throws Exception
    {
        var user = dtoToEntity(reqUserDto);
        userRepo.save(user);
        System.out.println(user);
    }

    @Override
    public ResUserDto getUser(String id)
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        return entityToDto(user);
    }

    @Override
    public void updateUser(String id, ReqUserDto reqUserDto) throws Exception
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        var user1 = dtoToEntity(reqUserDto);
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
    public User dtoToEntity(ReqUserDto reqUserDto) throws Exception
    {
        LocalDate date = LocalDate.parse(reqUserDto.getDateOfBirth());
        Location location = iLocationService.getLocationByName(reqUserDto.getLocationName());
        var attachment = iAttachmentService.saveAttachment(reqUserDto.getProfileImage(), Properties.USER_FOLDER);

//        var user = modelMapper.map(reqUserDto,User.class);
        var user = new User();
        BeanUtils.copyProperties(reqUserDto, user);
        user.setDateOfBirth(date);
        user.setRole(Role.ROLE_USER);
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setAttachment(attachment);
        user.setLocation(location);
        return user;
    }

    //Entity to Dto Conversion

    @Override
    public ResUserDto entityToDto(User user)
    {
        if (user.getAccountStatus() == AccountStatus.ACTIVE)
        {
            var resUserDto = modelMapper.map(user,ResUserDto.class);
            resUserDto.setId(user.getId().toString());
            resUserDto.setDateOfBirth(user.getDateOfBirth().toString());
            resUserDto.setRole(user.getRole().toString());
            resUserDto.setLocationName(user.getLocation().getLocationName());
            resUserDto.setProfileImagePath(user.getAttachment().getAttachmentPath());
            return resUserDto;
        }
        return null;
    }
}
