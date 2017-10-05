package com.example.facture.mvc.controller;

import com.example.facture.FactureApplication;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.PriceGroup;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MyController extends SpringBootServletInitializer {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PriceGroupService priceGroupService;

    @Autowired
    private TypeOfCustomerService typeOfCustomerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TaxBracketService taxBracketService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "index";
    }

    @RequestMapping(value = "/priceGroups", method = RequestMethod.GET)
    public String priceGroups(ModelMap model) {
        model.addAttribute("priceGroups", priceGroupService.getAllPriceGroups());
        return "priceGroups";
    }

    @RequestMapping(value = "/taxBrackets", method = RequestMethod.GET)
    public String taxBrackets(ModelMap model) {
        model.addAttribute("taxBrackets", taxBracketService.getAllTaxBrackets());
        return "taxBrackets";
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public String hello(ModelMap model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        return "hello";
    }

    @RequestMapping(value = "/typeOfCustomers", method = RequestMethod.GET)
    public String typeOfCustomers(ModelMap model) {
        model.addAttribute("typeOfCustomers", typeOfCustomerService.getAllTypeOfCustomers());
        return "typeOfCustomers";
    }


    @GetMapping(value = "/add")
    public String addPriceGroup(Model model) {

        model.addAttribute("priceGroup", new PriceGroupDTO());
        return "addNewPriceGroup";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPriceGropu(@ModelAttribute("priceGroup") @Valid PriceGroupDTO priceGroupDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addNewPriceGroup";
        }
        priceGroupService.savePriceGroup(priceGroupDTO);
        return "redirect:/priceGroups";
    }

    @GetMapping(value = "/addTax")
    public String addTaxBracket(Model model) {

        model.addAttribute("taxBracket", new TaxBracketDTO());
        return "addNewTaxBracket";
    }

    @RequestMapping(value = "/addTax", method = RequestMethod.POST)
    public String addTaxBracket(@ModelAttribute("taxBracket") @Valid TaxBracketDTO taxBracketDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addNewTaxBracket";
        }
        taxBracketService.saveTaxBracket(taxBracketDTO);
        return "redirect:/taxBrackets";
    }

    @GetMapping(value = "/addType")
    public String addTypeOfCustomer(Model model) {

        model.addAttribute("typeOfCustomer", new TypeOfCustomerDTO());
        return "addTypeOfCustomer";
    }

    @RequestMapping(value = "/addType", method = RequestMethod.POST)
    public String addTypeOfCustomer(@ModelAttribute("typeOfCustomer") @Valid TypeOfCustomerDTO typeOfCustomerDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addTypeOfCustomer";
        }
        typeOfCustomerService.saveTypeOfCustomer(typeOfCustomerDTO);
        return "redirect:/typeOfCustomers";
    }

    @GetMapping(value="/addCustomer")
    public String addCustomer(Model model){
        List<PriceGroup> listOfGroups = priceGroupService.getAllPriceGroupss();
        List<TypeOfCustomer> listOfTypes = typeOfCustomerService.getAllTypeOfCustomerss();
        List<TaxBracket> listOfBrackets = taxBracketService.getAllTaxBracketss();
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("listOfGroups", listOfGroups);
        model.addAttribute("listOfTypes", listOfTypes);
        model.addAttribute("listOfBrackets", listOfBrackets);


        return "addCustomer";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer")   @Valid CustomerDTO customerDTO, BindingResult bindingResult, @PathVariable("name") String name) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        }
        customerService.saveCustomer(customerDTO);
        return "redirect:/customers";
    }



}



