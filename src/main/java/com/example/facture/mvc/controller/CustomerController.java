package com.example.facture.mvc.controller;


import com.example.facture.jpa.Validator.CustomerValidator;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;
import java.util.List;


@Controller
public class CustomerController extends SpringBootServletInitializer {


    private PriceGroupService priceGroupService;
    private TypeOfCustomerService typeOfCustomerService;
    private CustomerService customerService;
    private CustomerValidator customerValidator;

    @Autowired
    public CustomerController(PriceGroupService priceGroupService, TypeOfCustomerService typeOfCustomerService, CustomerService customerService, CustomerValidator customerValidator) {
        this.priceGroupService = priceGroupService;
        this.typeOfCustomerService = typeOfCustomerService;
        this.customerService = customerService;
        this.customerValidator = customerValidator;
    }


    @RequestMapping(value = "/customer/name/{name}", method = RequestMethod.GET)
    public String displayCustomerByName(@PathVariable("name") String name, ModelMap model) {

        List<Address> addresses = customerService.getAddresses(customerService.getCustomerByNamee(name));
        List<Invoice> invoices = customerService.getInvoices(customerService.getCustomerByNamee(name));

        model.addAttribute("addresses", addresses);
        model.addAttribute("customer", customerService.getCustomerByName(name));
        model.addAttribute("invoices", invoices);

        return "customerByName";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(ModelMap model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping(value = "/addCustomer")
    public String addCustomer(Model model) {
        List<PriceGroup> listOfGroups = priceGroupService.getAllPriceGroupss();
        List<TypeOfCustomer> listOfTypes = typeOfCustomerService.getAllTypeOfCustomerss();
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("listOfGroups", listOfGroups);
        model.addAttribute("listOfTypes", listOfTypes);


        return "addCustomer";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult bindingResult, Model model) throws IOException {
        customerValidator.validate(customerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<PriceGroup> listOfGroups = priceGroupService.getAllPriceGroupss();
            List<TypeOfCustomer> listOfTypes = typeOfCustomerService.getAllTypeOfCustomerss();
            model.addAttribute("listOfGroups", listOfGroups);
            model.addAttribute("listOfTypes", listOfTypes);

            return "addCustomer";
        }
        customerService.saveCustomer(customerDTO);
        return "redirect:/customers";
    }


}