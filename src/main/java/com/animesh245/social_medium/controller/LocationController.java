package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.requestDto.ReqLocation;
import com.animesh245.social_medium.dto.responseDto.ResLocation;
import com.animesh245.social_medium.entity.Location;
import com.animesh245.social_medium.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/location")
public class LocationController
{
    private final LocationService locationService;

    public LocationController(LocationService locationService1) {
        this.locationService = locationService1;
    }

    @GetMapping("/list")
    public ModelAndView getLocationList()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<Location> locationList = locationService.getLocations();
        modelAndView.addObject("locationList", locationList);
//        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addLocation()
    {
        ReqLocation reqLocation = new ReqLocation();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reqLocation", reqLocation);
//        modelAndView.setViewName("addLocation");
        return modelAndView;
    }

    @PostMapping("/save")
    public String saveLocation(@ModelAttribute("reqLocation") ReqLocation reqLocation)
    {
        locationService.saveLocation(reqLocation);

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect");
        return "added successfully";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateLocation(@PathVariable String id) throws Exception {
        ResLocation resLocation = locationService.getLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resLocation", resLocation);
//        modelAndView.setViewName("redirect");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView saveUpdates(@ModelAttribute("reqLocation") ReqLocation reqLocation)
    {
        locationService.updateLocation(reqLocation.getId().toString(),reqLocation);

        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect");
        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public ModelAndView getSingleLocation(@PathVariable String id) throws Exception {
        ResLocation resLocation = locationService.getLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resLocation", resLocation);
//        modelAndView.setViewName("");
        return modelAndView;
    }

}
