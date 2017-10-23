package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Customer;
import lombok.Data;

import java.util.List;


public @Data
class TypeOfCustomerDisplayDTO {

    private Long id;
    private String name;
    private List<Customer> customers;

    @Override
    public String toString() {
        return String.format("TypeOfCustomerDTO[%d_%s]", id, name);
    }

}