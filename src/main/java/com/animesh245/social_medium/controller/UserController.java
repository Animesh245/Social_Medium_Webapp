package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.service.definition.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController
{
    //UserService Dependency Injection
    private final IUserService IUserService;

    public UserController(IUserService IUserService1)
    {
        this.IUserService = IUserService1;
    }

    @GetMapping(value = "/")
    public ModelAndView getUserList()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<ResUserDto> resUserDtoList = IUserService.getUsers();
        modelAndView.addObject("resUserDtoList", resUserDtoList);
        modelAndView.setViewName("user/List");
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getUser(@PathVariable("id") String id)
    {
        ModelAndView modelAndView = new ModelAndView();
        var resUserDto = IUserService.getUser(id);
        modelAndView.addObject("resUserDto", resUserDto);
        modelAndView.setViewName("user/List");
        return modelAndView;
    }

    @PostMapping(value = "/")
    public ModelAndView addUser(ReqUserDto reqUserDto)
    {
        ModelAndView modelAndView = new ModelAndView();
        IUserService.saveUser(reqUserDto);
        modelAndView.setViewName("redirect:/users/");
        return modelAndView;
    }

    @PostMapping(value = "/{id}")
    public ModelAndView updateUser(@PathVariable("id") String id, @RequestBody ReqUserDto reqUserDto)
    {
        ModelAndView modelAndView = new ModelAndView();
        IUserService.updateUser(id, reqUserDto);
        modelAndView.setViewName("redirect:/users/");
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView deleteUser(@PathVariable("id") String  id) throws Exception
    {
        var modelAndView = new ModelAndView("redirect:/users/");
        IUserService.deleteUser(id);
        return modelAndView;
    }

}
