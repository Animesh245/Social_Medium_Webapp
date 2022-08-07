package com.animesh245.social_medium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
    @GetMapping(value = "/")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","It's working");
        mv.setViewName("index");
        return mv;
    }
}
