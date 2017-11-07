package com.example.facture.mvc.controller;


import com.example.facture.jpa.Validator.InvoiceValidator;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@Controller
public class InvoiceController extends SpringBootServletInitializer {


    private InvoiceService invoiceService;
    private AddressService addressService;
    private CustomerService customerService;
    private ProductService productService;
    private InvoiceItemService invoiceItemService;
    private InvoiceValidator invoiceValidator;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, AddressService addressService, CustomerService customerService, ProductService productService, InvoiceItemService invoiceItemService, InvoiceValidator invoiceValidator) {
        this.invoiceService = invoiceService;
        this.addressService = addressService;
        this.customerService = customerService;
        this.productService = productService;
        this.invoiceItemService = invoiceItemService;
        this.invoiceValidator = invoiceValidator;

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());

        return "index";
    }


    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public String displayInvoice(@PathVariable("id") Long id, InvoiceDTO invoiceDTO, ModelMap model) {

        List<InvoiceItem> listOfInvoiceItems = invoiceService.getInvoiceItems(invoiceService.getInvoiceById(id));

        model.addAttribute("listOfInvoiceItems", listOfInvoiceItems);
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));


        if (listOfInvoiceItems.size() > 0) {

            model.addAttribute("sum", invoiceService.getSum(id));

            invoiceService.updateInvoiceFrom(invoiceDTO);
        }
        return "invoiceById";

    }

    @GetMapping(value = "/delete/invoice/{id}")
    public String deleteInvoice(@ModelAttribute("invoice") InvoiceDTO invoiceDTO, @PathVariable("id") Long id) throws IOException {

        invoiceService.deleteInvoice(invoiceDTO);
        return "redirect:/";
    }

    @GetMapping(value = "/update/invoice/{id}")
    public String updateInvoice(@ModelAttribute("invoice") InvoiceDTO invoiceDTO, @PathVariable("id") Long id) throws IOException {

        if (invoiceService.getInvoiceById(id).getConfirmDate() == null) {

            invoiceService.updateFrom(invoiceDTO);
            return "redirect:/";
        } else {
            return String.format("redirect:/invoice/%d", id);
        }
    }

    @RequestMapping(value = "/invoice/number/{numberr}", method = RequestMethod.GET)
    public String displayInvoiceByNumber(@PathVariable("numberr") String numberr, InvoiceDTO invoiceDTO, ModelMap model) {

        List<InvoiceItem> listOfInvoiceItems = invoiceService.getInvoiceItems(invoiceService.getInvoiceByNumberrr(numberr));
        model.addAttribute("listOfInvoiceItems", listOfInvoiceItems);
        model.addAttribute("invoice", invoiceService.getInvoiceByNumber(numberr));

        if (listOfInvoiceItems.size() > 0) {
            model.addAttribute("sum", invoiceService.getSuma(numberr));


            invoiceService.updateInvoiceFromNumber(invoiceDTO);
        }
        return "invoiceByNumber";
    }


    @GetMapping("/{name}/invoice/add")
    public String displayAddInvoiceCustomer(@PathVariable("name") String name, Model model) {
        if (customerService.getCustomerByName(name).getAddresses().size() != 0) {
            CustomerDTO cust = customerService.getCustomerByName(name);
            long id = cust.getId();
            List<Address> addresses = customerService.getAddresses(customerService.getCustomerByNamee(name));
            model.addAttribute("invoice", new InvoiceDTO());
            model.addAttribute("listOfAddresses", addresses);
            model.addAttribute("idCustomer", id);

            return "addCustomerInvoice";
        } else {
            return "redirect:/customers";
        }
    }


    @RequestMapping(value = "/{name}/invoice/add", method = RequestMethod.POST)

    public String postAddCustomerInvoice(@ModelAttribute("invoice") @Valid InvoiceDTO invoiceDTO,
                                         BindingResult bindingResult, @PathVariable("name") String name, Model model) {
        if (customerService.getCustomerByName(name).getAddresses().size() != 0) {
            invoiceValidator.validate(invoiceDTO, bindingResult);

            if (bindingResult.hasErrors()) {
                CustomerDTO cust = customerService.getCustomerByName(name);
                long id = cust.getId();
                List<Address> addresses = customerService.getAddresses(customerService.getCustomerByNamee(name));
                model.addAttribute("listOfAddresses", addresses);
                model.addAttribute("idCustomer", id);

                return "addCustomerInvoice";
            }
            invoiceService.saveInvoice(invoiceDTO);
            return String.format("redirect:/customer/name/%s", name);
        }
        else{
            return "redirect:/customers";
        }
    }


    @GetMapping("/{numberr}/invoiceItems/add")
    public String displayAddInvoiceItemsInvoice(@PathVariable("numberr") String numberr, Model model) {

        if (invoiceService.getInvoiceByNumber(numberr).getConfirmDate() == null) {

            InvoiceDTO inv = invoiceService.getInvoiceByNumber(numberr);
            long id = inv.getId();

            List<Product> listOfProducts = productService.getAllProductss();
            List<Invoice> listOfInvoices = invoiceService.getAllInvoicess();
            List<Customer> listOfCustomers = customerService.getAllCustomerss();
            List<Address> listOfAddresses = addressService.getAllAddressess();
            model.addAttribute("invoiceItem", new InvoiceItemDTO());
            model.addAttribute("listOfProducts", listOfProducts);
            model.addAttribute("listOfInvoices", listOfInvoices);


            model.addAttribute("invoiceItem", new InvoiceItemDTO());
            model.addAttribute("idInvoice", id);

            return "addInvoiceItem";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{numberr}/invoiceItems/add", method = RequestMethod.POST)

    public String postAddInvoiceItemsInvoice(@ModelAttribute("invoiceItem") @Valid InvoiceItemDTO invoiceItemDTO,
                                             BindingResult bindingResult, @PathVariable("numberr") String numberr, Model model) {
        if (invoiceService.getInvoiceByNumber(numberr).getConfirmDate() == null) {
            if (bindingResult.hasErrors()) {
                InvoiceDTO invo = invoiceService.getInvoiceByNumber(numberr);
                long id = invo.getId();
                List<Product> listOfProducts = productService.getAllProductss();
                model.addAttribute("listOfProducts", listOfProducts);
                model.addAttribute("idInvoice", id);

                return "addInvoiceItem";
            }
            invoiceItemService.saveInvoiceItem(invoiceItemDTO);

            return String.format("redirect:/invoice/number/%s", numberr);
        } else {
            return "redirect:/";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @InitBinder
    public void initBinderr(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @RequestMapping(value = "/downloadPDF/{id}", method = RequestMethod.GET)
    public ModelAndView downloadPDFById(@PathVariable("id") Long id, Model model) {

        List<InvoiceItem> listOfInvoiceItems = invoiceService.getInvoiceItems(invoiceService.getInvoiceById(id));


        model.addAttribute("listOfInvoiceItems", listOfInvoiceItems);
        model.addAttribute("listInvoices", invoiceService.getInvoiceById(id));


        return new ModelAndView("pdfView", "listInvoices", invoiceService.getInvoiceById(id));

    }


}
