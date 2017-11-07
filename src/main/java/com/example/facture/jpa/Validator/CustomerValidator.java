package com.example.facture.jpa.Validator;

import com.example.facture.jpa.dto.CustomerDTO;
import com.example.facture.jpa.model.Customer;
import com.example.facture.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class CustomerValidator implements Validator {


    private CustomerService customerService;

    @Autowired
    public CustomerValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean supports(Class clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) target;
        List<CustomerDTO> customerList = customerService.getAllCustomers();
        for (CustomerDTO customerr : customerList) {
            if (customerr.getNip().equals(customerDTO.getNip())) {
                errors.rejectValue("nip", "", "Nip already exist");
            } else if (customerr.getName().equals(customerDTO.getName())) {
                errors.rejectValue("name", "", "Name already exist");
            }
        }

    }
}
