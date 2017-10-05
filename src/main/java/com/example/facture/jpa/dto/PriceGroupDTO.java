package com.example.facture.jpa.dto;

import com.example.facture.jpa.model.Invoice;
import com.example.facture.jpa.model.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PriceGroupDTO {

    private Long id;
    private int discount;
    private Invoice invoice;
    private String name;
    private List<Customer> customers;


}