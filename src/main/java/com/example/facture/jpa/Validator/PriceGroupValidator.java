package com.example.facture.jpa.Validator;

import com.example.facture.jpa.dto.PriceGroupDTO;
import com.example.facture.jpa.model.PriceGroup;
import com.example.facture.jpa.service.PriceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


@Component
public class PriceGroupValidator implements Validator {


    private PriceGroupService priceGroupService;

    @Autowired
    public PriceGroupValidator(PriceGroupService priceGroupService) {
        this.priceGroupService = priceGroupService;
    }

    @Override
    public boolean supports(Class clazz) {
        return PriceGroup.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PriceGroupDTO priceGroupDTO = (PriceGroupDTO) target;
        List<PriceGroupDTO> priceGrouptList = priceGroupService.getAllPriceGroups();
        for (PriceGroupDTO priceGroups : priceGrouptList) {
            if (priceGroups.getName().equals(priceGroupDTO.getName())) {
                errors.rejectValue("name", "", "Name of price group already exist");
            }

        }

    }
}
