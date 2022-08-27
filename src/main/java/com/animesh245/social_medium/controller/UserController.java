package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.request.ReqUserDto;
import com.animesh245.social_medium.dto.response.ResLocationDto;
import com.animesh245.social_medium.dto.response.ResUserDto;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController
{
    //UserService Dependency Injection
    private final IUserService iUserService;
    private final ILocationService iLocationService;

    public UserController(IUserService iUserService, ILocationService iLocationService)
    {
        this.iUserService = iUserService;
        this.iLocationService = iLocationService;
    }

    @GetMapping(value = "/")
    public ModelAndView getUserList()
    {
        var mv = new ModelAndView();
        List<ResUserDto> resUserDtoList = iUserService.getUsers();
        mv.addObject("resUserDtoList", resUserDtoList);
        mv.setViewName("admin/user/list");
        return mv;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getUser(@PathVariable("id") String id)
    {
        var mv = new ModelAndView();
        var resUserDto = iUserService.getUser(id);

        var resLocationDtoList = iLocationService.getLocations();
        var locationDtoList = new ArrayList<>();
        for (ResLocationDto resLocationDto: resLocationDtoList)
        {
            locationDtoList.add(resLocationDto.getLocationName());
        }
        mv.addObject("locationDtoList",locationDtoList);
        mv.addObject("resUserDto", resUserDto);
        mv.addObject("reqUserDto", new ReqUserDto());
        mv.setViewName("admin/user/show");
        return mv;
    }

    @GetMapping(value = "/create")
    public ModelAndView newUser()
    {
        var mv = new ModelAndView();
        var reqUserDto = new ReqUserDto();
        var resLocationDtoList = iLocationService.getLocations();
        var locationDtoList = new ArrayList<>();
        for (ResLocationDto resLocationDto: resLocationDtoList)
        {
            locationDtoList.add(resLocationDto.getLocationName());
        }
        mv.addObject("locationDtoList",locationDtoList);
        mv.addObject("reqUserDto", reqUserDto);
        mv.setViewName("auth/register");
        return mv;
    }

    @PostMapping(value = "/")
    public ModelAndView saveUser(@ModelAttribute("reqUserDto") ReqUserDto reqUserDto) throws Exception
    {
        var mv = new ModelAndView();
        iUserService.saveUser(reqUserDto);
        mv.setViewName("redirect:/users/");
        return mv;
    }

    @PostMapping(value = "/{id}")
    public ModelAndView updateUser(@PathVariable("id") String id, @ModelAttribute("reqUserDto") ReqUserDto reqUserDto) throws Exception
    {
        var mv = new ModelAndView();
        iUserService.updateUser(id, reqUserDto);
        mv.setViewName("redirect:/users/");
        return mv;
    }

    @GetMapping(value = "/deactivate/{id}")
    public ModelAndView deactivateUser(@PathVariable("id") String  id)
    {
        var mv = new ModelAndView("redirect:/users/");
        iUserService.deactivateUser(id);
        return mv;
    }
}
