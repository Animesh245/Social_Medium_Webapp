package com.animesh245.social_medium.service;

import com.animesh245.social_medium.entity.User;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    void saveUser(User user);

    User getUser(Long id) throws Exception;

    void updateUser(User user);

    void deleteUser(Long id) throws Exception;
}
