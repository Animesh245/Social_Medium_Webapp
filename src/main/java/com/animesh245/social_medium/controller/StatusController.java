package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.request.RequestStatusDto;
import com.animesh245.social_medium.dto.response.ResLocationDto;
import com.animesh245.social_medium.dto.response.ResponseStatusDto;
import com.animesh245.social_medium.enums.Privacy;
import com.animesh245.social_medium.service.definition.ILocationService;
import com.animesh245.social_medium.service.definition.IStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/statuses")
public class StatusController
{
    private final IStatusService iStatusService;
    private final ILocationService iLocationService;

    public StatusController(IStatusService iStatusService, ILocationService iLocationService)
    {
        this.iStatusService = iStatusService;
        this.iLocationService = iLocationService;
    }

    @GetMapping("/")
    public ModelAndView getAllStatus()
    {
        List<ResponseStatusDto> responseStatusDtoList = iStatusService.getStatuses();

        var mv = new ModelAndView();
        mv.addObject("responseStatusDtoList",responseStatusDtoList);
        mv.setViewName("admin/status/list");
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView getStatus(@PathVariable("id") String id)
    {
        var responseStatusDto = iStatusService.getStatus(id);
        var mv = new ModelAndView();
        mv.addObject("responseStatusDto", responseStatusDto);
        mv.setViewName("admin/status/show");
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newStatus()
    {
        var resLocationDtoList = iLocationService.getLocations();
        var locationDtoList = new ArrayList<>();
        for (ResLocationDto resLocationDto: resLocationDtoList)
        {
            locationDtoList.add(resLocationDto.getLocationName());
        }

        var mv = new ModelAndView();
        mv.addObject("requestStatusDto", new RequestStatusDto());
        mv.addObject("locationDtoList",locationDtoList);
        mv.addObject("privacyList", Arrays.asList(Privacy.values()));
        mv.setViewName("admin/status/create");
        return mv;
    }

    @PostMapping(value = "/")
    public ModelAndView saveStatus(@ModelAttribute RequestStatusDto requestStatusDto) throws Exception
    {
        iStatusService.saveStatus(requestStatusDto);
        var mv = new ModelAndView();
        mv.setViewName("redirect:/statuses/");
        return mv;
    }

    @PostMapping("/{id}")
    public ModelAndView updateStatus(@PathVariable("id") String id, @ModelAttribute RequestStatusDto requestStatusDto) throws Exception
    {
        iStatusService.updateStatus(id, requestStatusDto);
        var mv = new ModelAndView();
        mv.setViewName("redirect:/statues/");
        return mv;
    }
}
