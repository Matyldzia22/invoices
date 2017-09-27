/*
package com.example.facture.mvc.controller;

import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/invoice")
@Controller

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

    //invoice page
    @GetMapping("/{number}")
    public String displayInvoice(Model model, @PathVariable("number") String number ){
        InvoiceDisplayDTO invoice = invoiceService.getInvoiceDisplayByNumber(number);




    }
}
*/


