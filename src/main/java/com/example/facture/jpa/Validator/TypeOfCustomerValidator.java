package com.example.facture.jpa.Validator;

import com.example.facture.jpa.dto.PriceGroupDTO;
import com.example.facture.jpa.dto.TypeOfCustomerDTO;
import com.example.facture.jpa.model.PriceGroup;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.service.TypeOfCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class TypeOfCustomerValidator implements Validator {


    private TypeOfCustomerService typeOfCustomerService;

    @Autowired
    public TypeOfCustomerValidator(TypeOfCustomerService typeOfCustomerService){
        this.typeOfCustomerService = typeOfCustomerService;
    }

    @Override
    public boolean supports(Class clazz) {
        return TypeOfCustomer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TypeOfCustomerDTO typeOfCustomerDTO = (TypeOfCustomerDTO) target;
        List<TypeOfCustomerDTO> typesList = typeOfCustomerService.getAllTypeOfCustomers();
        for (TypeOfCustomerDTO types : typesList) {
            if (types.getName().equals(typeOfCustomerDTO.getName())) {
                errors.rejectValue("name", "", "Name of type already exist");
            }

        }

    }
}
