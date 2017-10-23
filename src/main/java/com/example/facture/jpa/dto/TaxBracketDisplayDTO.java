package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import lombok.Data;

import java.util.List;


public @Data
class TaxBracketDisplayDTO {

    private Long id;
    private int number;
    private List<Customer> customers;

}