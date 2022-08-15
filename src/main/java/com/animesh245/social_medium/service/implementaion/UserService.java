package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.enums.Role;
import com.animesh245.social_medium.exception.NotFoundException;
import com.animesh245.social_medium.repository.UserRepo;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService
{
    private final UserRepo userRepo;
    private final ILocationService iLocationService;

    private final ModelMapper modelMapper;

    public UserService(UserRepo userRepo, ILocationService iLocationService, ModelMapper modelMapper)
    {
        this.userRepo = userRepo;
        this.iLocationService = iLocationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ResUserDto> getUsers()
    {
        var userList = userRepo.findAll();
        var resUserDto = new ResUserDto();
        var resUserDtoList = new ArrayList<ResUserDto>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        for (User user: userList)
        {
            resUserDto = modelMapper.map(user,ResUserDto.class);
            resUserDto.setId(user.getId().toString());
            resUserDto.setDateOfBirth(dateFormat.format(user.getDateOfBirth()));
            resUserDto.setRole(user.getRole().toString());
            resUserDto.setLocationName(user.getLocation().getLocationName());
//            resUserDto.setProfileImagePath(user.getAttachment().getAttachmentPath());
            resUserDtoList.add(resUserDto);
        }
        return resUserDtoList;
    }

    @Override
    public void saveUser(ReqUserDto reqUserDto) throws Exception
    {
        Date date  = new SimpleDateFormat("yyyy-MM-dd").parse(reqUserDto.getDateOfBirth());
        Location location = iLocationService.getLocationByName(reqUserDto.getLocationName());

        var user = new User();

//           var user = modelMapper.map(reqUserDto, User.class);
            BeanUtils.copyProperties(reqUserDto, user);
            user.setRole(Role.ROLE_USER);
            user.setDateOfBirth(date);
            user.setLocation(location);
            userRepo.save(user);
        System.out.println(user);
    }

    @Override
    public ResUserDto getUser(String id)
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        var resUserDto = new ResUserDto();
        resUserDto = modelMapper.map(user, ResUserDto.class);
        return resUserDto;
    }

    @Override
    public void updateUser(String id, ReqUserDto reqUserDto)
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        modelMapper.map(reqUserDto, User.class);
        userRepo.save(user);
    }

    @Override
    public void deleteUser(String id)
    {
        userRepo.deleteById(Long.parseLong(id));
    }
}
