package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.entity.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface IUserService
{
    List<ResUserDto> getUsers();

    void saveUser(ReqUserDto reqUserDto, CommonsMultipartFile file) throws Exception;

    ResUserDto getUser(String id);

//    void updateUser(String id , ReqUserDto reqUserDto) throws Exception;

    void deleteUser(String id) throws Exception;

    User dtoToEntity(ReqUserDto reqUserDto, CommonsMultipartFile file) throws Exception;

    ResUserDto entityToDto(User user);
}
