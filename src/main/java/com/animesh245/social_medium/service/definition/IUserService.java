package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface IUserService extends UserDetailsService
{
    List<ResUserDto> getUsers() throws Exception;

    void saveUser(ReqUserDto reqUserDto) throws Exception;


    ResUserDto getUser(String id) throws Exception;

    void updateUser(String id , ReqUserDto reqUserDto) throws Exception;

    void deleteUser(String id) throws Exception;

    void deactivateUser(String id);

    User dtoToEntity(ReqUserDto reqUserDto) throws Exception;

    ResUserDto entityToDto(User user) throws Exception;
}
