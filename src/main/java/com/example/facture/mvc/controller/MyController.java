package com.example.facture.mvc.controller;


import com.example.facture.jpa.Validator.PriceGroupValidator;
import com.example.facture.jpa.Validator.ProductValidator;
import com.example.facture.jpa.Validator.TaxBracketValidator;
import com.example.facture.jpa.Validator.TypeOfCustomerValidator;
import com.example.facture.jpa.dto.*;
import com.example.facture.jpa.model.*;
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


@Controller
public class MyController extends SpringBootServletInitializer {



    private AddressService addressService;
    private PriceGroupService priceGroupService;
    private TypeOfCustomerService typeOfCustomerService;
    private CustomerService customerService;
    private TaxBracketService taxBracketService;
    private ProductService productService;
    private ProductValidator productValidator;
    private PriceGroupValidator priceGroupValidator;
    private TypeOfCustomerValidator typeOfCustomerValidator;
    private TaxBracketValidator taxBracketValidator;

    @Autowired
    public MyController (AddressService addressService, PriceGroupService priceGroupService, TypeOfCustomerService typeOfCustomerService, CustomerService customerService, TaxBracketService taxBracketService, ProductService productService, ProductValidator productValidator, PriceGroupValidator priceGroupValidator, TypeOfCustomerValidator typeOfCustomerValidator, TaxBracketValidator taxBracketValidator){
        this.addressService = addressService;
        this.priceGroupService = priceGroupService;
        this.typeOfCustomerService = typeOfCustomerService;
        this.customerService = customerService;
        this.taxBracketService = taxBracketService;
        this.productService = productService;
        this.productValidator = productValidator;
        this.priceGroupValidator = priceGroupValidator;
        this.typeOfCustomerValidator = typeOfCustomerValidator;
        this.taxBracketValidator = taxBracketValidator;
    }


    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String displayProduct(@PathVariable("id") Long id,  ProductDTO productDTO, ModelMap model) {


        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("netto", productService.getNettoPrice(id));

        productService.updateProductAuto(productDTO);

        return "productById";

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
        List<ProductDTO> productss = productService.getAllProducts();
        model.addAttribute("products", productService.getAllProducts());

        return "products";
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
        priceGroupValidator.validate(priceGroupDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addNewPriceGroup";
        }
        priceGroupService.savePriceGroup(priceGroupDTO);
        return "redirect:/priceGroups";
    }

    @GetMapping(value = "/delete/product/{id}")
    public String deleteProduct(@ModelAttribute("product")  ProductDTO productDTO, @PathVariable("id") Long id) throws IOException {

        productService.deleteProduct(productDTO);
        return "redirect:/products";
    }

    @GetMapping(value = "/addTax")
    public String addTaxBracket(Model model) {

        model.addAttribute("taxBracket", new TaxBracketDTO());
        return "addNewTaxBracket";
    }

    @RequestMapping(value = "/addTax", method = RequestMethod.POST)
    public String addTaxBracket(@ModelAttribute("taxBracket") @Valid TaxBracketDTO taxBracketDTO, BindingResult bindingResult) throws IOException {
        taxBracketValidator.validate(taxBracketDTO, bindingResult);
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
        typeOfCustomerValidator.validate(typeOfCustomerDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addTypeOfCustomer";
        }
        typeOfCustomerService.saveTypeOfCustomer(typeOfCustomerDTO);
        return "redirect:/typeOfCustomers";
    }


    @GetMapping("/{name}/address/add")
    public String displayAddAddressCustomer(@PathVariable("name") String name, Model model) {

        CustomerDTO cust = customerService.getCustomerByName(name);
        long id = cust.getId();

        model.addAttribute("address", new AddressDTO());

        model.addAttribute("idCustomer", id);

        return "addAddress";
    }

    @RequestMapping(value = "/{name}/address/add", method = RequestMethod.POST)

    public String postAddCustomerAddress(@ModelAttribute("address") @Valid AddressDTO addressDTO,
                                         BindingResult bindingResult, @PathVariable("name") String name, Model model) {
        if (bindingResult.hasErrors()) {
            CustomerDTO cust = customerService.getCustomerByName(name);
            long id = cust.getId();
            model.addAttribute("idCustomer", id);
            return "addAddress";
        }
        addressService.saveAddress(addressDTO);
        return String.format("redirect:/customer/name/%s", name);
    }


    @GetMapping(value = "/addAddress")
    public String addAddress(Model model) {
        List<Customer> listOfCustomers = customerService.getAllCustomerss();
        model.addAttribute("address", new AddressDTO());
        model.addAttribute("listOfCustomers", listOfCustomers);
        return "addAddress";
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("address") @Valid AddressDTO addressDTO, BindingResult bindingResult, String customerId) throws IOException {
        //if (bindingResult.hasErrors()) {
        //  return "addAddress";
        //}


        addressService.saveAddress(addressDTO);
        return "redirect:/address";
    }


    @GetMapping(value = "/addProduct")
    public String addProduct(Model model) {

        model.addAttribute("product", new ProductDTO());

        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") @Valid ProductDTO productDTO, BindingResult bindingResult) throws IOException {
        productValidator.validate(productDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addProduct";
        }

        productService.saveProduct(productDTO);
        return "redirect:/products";
    }


}



