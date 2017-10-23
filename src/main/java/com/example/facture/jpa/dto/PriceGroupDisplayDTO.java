package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Customer;
import lombok.Data;

import java.util.List;


public @Data
class PriceGroupDisplayDTO {

    private Long id;
    private int discount;
    private Invoice invoice;
    private String name;
    private List<Customer> customers;

}