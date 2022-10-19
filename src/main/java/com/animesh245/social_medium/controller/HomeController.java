package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.request.RequestStatusDto;
import com.animesh245.social_medium.dto.response.ResponseLocationDto;
import com.animesh245.social_medium.dto.response.ResponseUserDto;
import com.animesh245.social_medium.enums.Privacy;
import com.animesh245.social_medium.repository.UserRepo;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IStatusService;
import com.animesh245.social_medium.service.definition.IUserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController
{
    private final UserRepo userRepo;
    private final IUserService iUserService;
    private final IStatusService iStatusService;
    private final ILocationService iLocationService;

    public HomeController(UserRepo userRepo, IUserService iUserService, IStatusService iStatusService, ILocationService iLocationService) {
        this.userRepo = userRepo;
        this.iUserService = iUserService;
        this.iStatusService = iStatusService;
        this.iLocationService = iLocationService;
    }

    @GetMapping("/")
    public ModelAndView home() throws Exception {

        var user = SecurityContextHolder.getContext().getAuthentication().getName();
        var responseStatusDtoList = iStatusService.getStatuses();
        List<ResponseUserDto> responseUserDtoList = iUserService.getUsers();
        var resLocationDtoList = iLocationService.getLocations();
        var locationDtoList = new ArrayList<>();
        for (ResponseLocationDto responseLocationDto : resLocationDtoList)
        {
            locationDtoList.add(responseLocationDto.getLocationName());
        }

        ModelAndView mv = new ModelAndView();
        var userDb = userRepo.findByEmailId(user);
        var responseUserDto = iUserService.entityToDto(userDb);
        mv.addObject("responseUserDto", responseUserDto);
        mv.addObject("responseStatusDtoList", responseStatusDtoList);
        mv.addObject("resUserDtoList", responseUserDtoList);
        mv.addObject("requestStatusDto", new RequestStatusDto());
        mv.addObject("locationDtoList",locationDtoList);
        mv.addObject("privacyList", Arrays.asList(Privacy.values()));
        mv.setViewName("main/index");
        return mv;
    }

}
