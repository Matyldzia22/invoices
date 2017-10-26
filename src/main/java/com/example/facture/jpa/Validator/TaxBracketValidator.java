package com.example.facture.jpa.Validator;

import com.example.facture.jpa.dto.TaxBracketDTO;
import com.example.facture.jpa.dto.TypeOfCustomerDTO;
import com.example.facture.jpa.model.TaxBracket;
import com.example.facture.jpa.model.TypeOfCustomer;
import com.example.facture.jpa.service.TaxBracketService;
import com.example.facture.jpa.service.TypeOfCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class TaxBracketValidator implements Validator {


    private TaxBracketService taxBracketService;

    @Autowired
    public TaxBracketValidator(TaxBracketService taxBracketService){
        this.taxBracketService = taxBracketService;
    }

    @Override
    public boolean supports(Class clazz) {
        return TaxBracket.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TaxBracketDTO taxBracketDTO = (TaxBracketDTO) target;
        List<TaxBracketDTO> taxbrackets = taxBracketService.getAllTaxBrackets();
        for (TaxBracketDTO taxbracketss : taxbrackets) {
            if ((taxbracketss.getNumber())==((taxBracketDTO.getNumber()))) {
                errors.rejectValue("number", "", "Number already exist");
            }

        }

    }
}
