package com.example.facture.mvc.controller;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }


}



