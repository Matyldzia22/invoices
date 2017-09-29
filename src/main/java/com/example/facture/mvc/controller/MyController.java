package com.example.facture.mvc.controller;

import com.example.facture.FactureApplication;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController extends SpringBootServletInitializer {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PriceGroupService priceGroupService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("priceGroups", priceGroupService.getAllPriceGroups());
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }




}



