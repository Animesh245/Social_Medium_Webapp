package com.animesh245.social_medium.service;

import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo1)
    {
        this.userRepo = userRepo1;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public void saveUser(User user) 
    {
     userRepo.save(user);   
    }

    @Override
    public User getUser(Long id) throws Exception {
        return userRepo.findById(id).orElseThrow();
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        userRepo.deleteById(id);
    }
}
