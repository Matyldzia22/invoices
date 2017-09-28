package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Customer;
import lombok.Data;

import java.util.List;


public @Data
class PriceGroupDTO {

    private Long id;
    private int discount;
    private Invoice invoice;
    private List<Customer> customers;


}