package com.animesh245.social_medium.service.definition;

import com.animesh245.social_medium.dto.request.RequestUserDto;
import com.animesh245.social_medium.dto.response.ResponseUserDto;
import com.animesh245.social_medium.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface IUserService extends UserDetailsService
{
    List<ResponseUserDto> getUsers() throws Exception;

    void saveUser(RequestUserDto requestUserDto) throws Exception;


    ResponseUserDto getUser(String id) throws Exception;

    void updateUser(String id , RequestUserDto requestUserDto) throws Exception;

    void deleteUser(String id) throws Exception;

    void deactivateUser(String id);

    User dtoToEntity(RequestUserDto requestUserDto) throws Exception;

    ResponseUserDto entityToDto(User user) throws Exception;
}
