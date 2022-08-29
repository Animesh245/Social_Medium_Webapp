package com.animesh245.social_medium.controller;

import com.animesh245.social_medium.dto.request.RequestLocationDto;
import com.animesh245.social_medium.dto.response.ResponseLocationDto;
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
        List<ResponseLocationDto> responseLocationDtoList = ILocationService.getLocations();
        modelAndView.addObject("resLocationDtoList", responseLocationDtoList);
        modelAndView.setViewName("admin/location/list");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addLocation()
    {
        RequestLocationDto requestLocationDto = new RequestLocationDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("reqLocationDto", requestLocationDto);
        modelAndView.setViewName("admin/location/create");
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView saveLocation(@ModelAttribute RequestLocationDto requestLocationDto)
    {
        ILocationService.saveLocation(requestLocationDto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/locations/" );
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getSingleLocation(@PathVariable String id) throws Exception
    {
        ResponseLocationDto responseLocationDto = ILocationService.getLocation(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resLocationDto", responseLocationDto);
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
