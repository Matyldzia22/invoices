package com.example.facture.mvc.controller;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


public class MyController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PriceGroupService priceGroupService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TaxBracketService taxBracketService;

    @Autowired
    private TypeOfCustomerService typeOfCustomerService;


    //main page
    @Controller
    public class HelloController {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String printHello(ModelMap model) {
            model.addAttribute("message", "Hello Spring MVC Framework!");
            return "index";
        }
    }

}



