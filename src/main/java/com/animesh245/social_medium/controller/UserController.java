package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.service.definition.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    //UserService Dependency Injection
    private final IUserService IUserService;

    public UserController(IUserService IUserService1)
    {
        this.IUserService = IUserService1;
    }

    @GetMapping(value = "/")
    public String getUserList()
    {
        ModelAndView mv = new ModelAndView();
        List<User> userList = IUserService.getUsers();
        mv.addObject("userList", userList);
        mv.setViewName("userList");
        return "gets all users";
    }

    @PostMapping(value = "/")
    public String addUser(User user)
    {

        IUserService.saveUser(user);
        return "adds a user";
    }

    @PutMapping(value = "/")
    public String updateUser(User user)
    {
        IUserService.updateUser(user);
        return "user updated";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws Exception {
        IUserService.deleteUser(id);
        return "user deleted";
    }

}
