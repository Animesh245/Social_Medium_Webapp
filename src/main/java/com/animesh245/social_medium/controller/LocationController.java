package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.request.ReqLocationDto;
import com.animesh245.social_medium.dto.response.ResLocationDto;
import com.animesh245.social_medium.service.definition.ILocationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController
{
    private final ILocationService ILocationService;

    public LocationController(ILocationService ILocationService) {
        this.ILocationService = ILocationService;
    }

    @GetMapping("/")
    public ModelAndView getLocationList()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<ResLocationDto> resLocationDtoList = ILocationService.getLocations();
        modelAndView.addObject("resLocationDtoList", resLocationDtoList);
        modelAndView.setViewName("admin/location/list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addLocation()
    {
        ReqLocationDto reqLocationDto = new ReqLocationDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reqLocationDto", reqLocationDto);
        modelAndView.setViewName("admin/location/create");
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView saveLocation(@ModelAttribute ReqLocationDto reqLocationDto)
    {
        ILocationService.saveLocation(reqLocationDto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/locations/" );
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getSingleLocation(@PathVariable String id) throws Exception
    {
        ResLocationDto resLocationDto = ILocationService.getLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resLocationDto", resLocationDto);
        modelAndView.setViewName("admin/location/show");
        return modelAndView;
    }

    //Update not required

//    @PostMapping("/edit/{id}")
//    public ModelAndView saveUpdates(@ModelAttribute("resLocation") ResLocationDto resLocationDto)
//    {
//        ReqLocationDto reqLocationDto = new ReqLocationDto();
//        BeanUtils.copyProperties(resLocationDto, reqLocationDto);
//
//        locationService.updateLocation(resLocationDto.getId().toString(), reqLocationDto);
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/location/list");
//        return modelAndView;
//    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteLocation(@PathVariable String id) throws Exception
    {
        ILocationService.deleteLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/locations/");
        return modelAndView;
    }

}
