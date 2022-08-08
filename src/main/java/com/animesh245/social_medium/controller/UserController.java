package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.entity.User;
import com.animesh245.social_medium.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    //UserService Dependency Injection
    private final UserService userService;

    public UserController(UserService userService1)
    {
        this.userService = userService1;
    }

    @GetMapping(value = "/")
    public String getUserList()
    {
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.getUsers();
        mv.addObject("userList", userList);
        mv.setViewName("userList");
        return "gets all users";
    }

    @PostMapping(value = "/")
    public String addUser(User user)
    {

        userService.saveUser(user);
        return "adds a user";
    }

    @PutMapping(value = "/")
    public String updateUser(User user)
    {
        userService.updateUser(user);
        return "user updated";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws Exception {
        userService.deleteUser(id);
        return "user deleted";
    }

}
