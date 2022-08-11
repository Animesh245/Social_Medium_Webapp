package com.animesh245.social_medium.service.implementaion;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.exception.NotFoundException;
import com.animesh245.social_medium.repository.UserRepo;
import com.animesh245.social_medium.service.definition.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService
{


    private final UserRepo userRepo;

    public UserService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public List<ResUserDto> getUsers()
    {

        var userList = userRepo.findAll();
        var resUserDto = new ResUserDto();
        var resUserDtoList = new ArrayList<ResUserDto>();

        for (User user: userList)
        {
            BeanUtils.copyProperties(user, resUserDto);
            resUserDtoList.add(resUserDto);
        }

        return resUserDtoList;
    }

    @Override
    public void saveUser(ReqUserDto reqUserDto)
    {
        var user = new User();
        BeanUtils.copyProperties(reqUserDto, user);
     userRepo.save(user);
    }

    @Override
    public ResUserDto getUser(String id)
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        var resUserDto = new ResUserDto();
        BeanUtils.copyProperties(user, resUserDto);
        return resUserDto;
    }

    @Override
    public void updateUser(String id, ReqUserDto reqUserDto)
    {
        var user = userRepo.findById(Long.parseLong(id)).orElseThrow(() -> new NotFoundException("User not found"));
        BeanUtils.copyProperties(reqUserDto, user);
        userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(Long.parseLong(id));
    }
}
