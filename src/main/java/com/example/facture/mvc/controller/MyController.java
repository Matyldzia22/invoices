package com.example.facture.mvc.controller;

import com.example.facture.FactureApplication;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
import com.example.facture.jpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceItemService invoiceItemService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "index";
    }

    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public String displayInvoice(@PathVariable("id") Long id, ModelMap model) {

        List<InvoiceItem> listOfInvoiceItems = invoiceService.getInvoiceItems(invoiceService.getInvoiceById(id));
        model.addAttribute("listOfInvoiceItems", listOfInvoiceItems);
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));


        return "invoiceByNumber";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(ModelMap model) {

        return "test";
    }

    @RequestMapping(value = "/priceGroups", method = RequestMethod.GET)
    public String priceGroups(ModelMap model) {
        model.addAttribute("priceGroups", priceGroupService.getAllPriceGroups());
        return "priceGroups";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(ModelMap model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(ModelMap model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @RequestMapping(value = "/invoiceItems", method = RequestMethod.GET)
    public String invoiceItems(ModelMap model) {
        model.addAttribute("invoiceItems", invoiceItemService.getAllInvoiceItems());
        return "invoiceItems";
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

    @GetMapping(value = "/addCustomer")
    public String addCustomer(Model model) {
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
    public String addCustomer(@ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        }
        customerService.saveCustomer(customerDTO);
        return "redirect:/customers";
    }

    @GetMapping(value = "/addInvoiceItem")
    public String addInvoiceItem(Model model) {
        List<Product> listOfProducts = productService.getAllProductss();
        List<Invoice> listOfInvoices = invoiceService.getAllInvoicess();
        model.addAttribute("invoiceItem", new InvoiceItemDTO());
        model.addAttribute("listOfProducts", listOfProducts);
        model.addAttribute("listOfInvoices", listOfInvoices);


        return "addInvoiceItem";
    }

    @RequestMapping(value = "/addInvoiceItem", method = RequestMethod.POST)
    public String addInvoiceItem(@ModelAttribute("invoiceItem") @Valid InvoiceItemDTO invoiceItemDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addInvoiceItem";
        }
        invoiceItemService.saveInvoiceItem(invoiceItemDTO);
        return "redirect:/invoiceItems";
    }

    @GetMapping(value = "/addAddress")
    public String addAddress(Model model) {
        List<Customer> listOfCustomers = customerService.getAllCustomerss();
        model.addAttribute("address", new AddressDTO());
        model.addAttribute("listOfCustomers", listOfCustomers);
        return "addAddress";
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("address") @Valid AddressDTO addressDTO, BindingResult bindingResult, String idCustomer) throws IOException {
        //if (bindingResult.hasErrors()) {
        //  return "addAddress";
        //}


        addressService.saveAddress(addressDTO);
        return "redirect:/address";
    }

    @GetMapping(value = "/addInvoice")
    public String addInvoice(Model model) {

        List<InvoiceItem> inv = invoiceItemService.getAllInvoiceItems();
        List<Product> pr = productService.getAllProductss();
        List<Invoice> in = invoiceService.getAllInvoicess();

        List<Customer> listOfCustomers = customerService.getAllCustomerss();
        List<Address> listOfAddresses = addressService.getAllAddressess();
        model.addAttribute("invoice", new InvoiceDTO());
        model.addAttribute("listOfCustomers", listOfCustomers);
        model.addAttribute("listOfAddresses", listOfAddresses);
        model.addAttribute("idInvoiceItem", invoiceItemService.getInvId()+1);
        model.addAttribute("idProduct", productService.getProdId()+1);
        model.addAttribute("idInvoice", invoiceService.getInId()+1);


        //model.addAttribute("idInvoiceItem", idInvoiceItem);
        model.addAttribute("invoiceItem", new InvoiceItemDTO());
        model.addAttribute("product2", new ProductDTO());


        return "addInvoice";
    }

    @RequestMapping(value = "/addInvoice", method = RequestMethod.POST)
    public String addInvoice(@ModelAttribute("invoice") @Valid InvoiceDTO invoiceDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addInvoice";
        }
        invoiceService.saveInvoice(invoiceDTO);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(value = "/addProduct")
    public String addProduct(Model model) {

        model.addAttribute("product", new ProductDTO());
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") @Valid ProductDTO productDTO, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "addProduct";
        }
        productService.saveProduct(productDTO);
        return "redirect:/products";
    }


}



