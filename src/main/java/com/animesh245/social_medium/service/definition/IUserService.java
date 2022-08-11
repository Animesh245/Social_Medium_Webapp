package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;

import java.util.List;

public interface IUserService
{
    List<ResUserDto> getUsers();

    void saveUser(ReqUserDto reqUserDto);

    ResUserDto getUser(String id);

    void updateUser(String id , ReqUserDto reqUserDto);

    void deleteUser(String id) throws Exception;
}
