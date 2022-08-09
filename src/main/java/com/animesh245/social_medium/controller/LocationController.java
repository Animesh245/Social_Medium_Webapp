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

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/list")
    public ModelAndView getLocationList()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<ResLocation> resLocationList = locationService.getLocations();
        modelAndView.addObject("resLocationList", resLocationList);
        modelAndView.setViewName("admin/location/list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addLocation()
    {
        ReqLocation reqLocation = new ReqLocation();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reqLocation", reqLocation);
        modelAndView.setViewName("admin/location/create");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveLocation(@ModelAttribute("reqLocation") ReqLocation reqLocation)
    {
        locationService.saveLocation(reqLocation);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/location/list" );
        return modelAndView;
    }

    @GetMapping("/get/{id}")
    public ModelAndView getSingleLocation(@PathVariable String id) throws Exception {
        ResLocation resLocation = locationService.getLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resLocation", resLocation);
        modelAndView.setViewName("admin/location/show");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView saveUpdates(@ModelAttribute("resLocation") ResLocation resLocation)
    {
        ReqLocation reqLocation = new ReqLocation();
        BeanUtils.copyProperties(resLocation,reqLocation);

        locationService.updateLocation(resLocation.getId().toString(),reqLocation);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/location/list");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteLocation(@PathVariable String id) throws Exception {
        locationService.deleteLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/location/list");
        return modelAndView;
    }

}
